package io.github.apjifengc.yaresourcepackmanager;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fi.iki.elonen.NanoHTTPD;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import io.github.apjifengc.yaresourcepackmanager.component.interfaces.*;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A resourcepack. <br/>
 * Note that other plugins MUSTN'T use this class. <br/>
 * One server can only use one resourcepack. <br/>
 * If you want to manage the resourcepack. see {@link YaResourcepackManager}.
 *
 * @author APJifengc
 * @see YaResourcepackManager
 */
public class ResourcePack extends NanoHTTPD {
    private final Logger logger = YaResourcepackManager.getInstance().getLogger();
    private final File file;

    /**
     * Create a new Resourcepack.
     *
     * @param port The port to publish the pack.
     * @param file The pack's zip file.
     */
    public ResourcePack(int port, File file) {
        super(port);
        this.file = file;
    }

    /**
     * Build the resourcepack.
     *
     * @param folder     The temp folder to put the resource in.
     * @param output     The output zip file.
     * @param components All resource components to add in the resourcepack.
     * @throws IOException Throw when a file error occurred.
     */
    public void build(File folder, File output, List<IComponent> components) throws IOException {
        String separator = File.separator;
        generatePackMCMeta(new File(folder + separator + "pack.mcmeta"));
        Map<Class<?>, List<ICollectionComponent>> map = new HashMap<>();
        for (IComponent component : components) {
            if (component instanceof IIndependentComponent) {
                ((IIndependentComponent) component).handleResource(folder);
            }
            if (component instanceof ICollectionComponent) {
                if (!map.containsKey(component.getClass())) map.put(component.getClass(), new ArrayList<>());
                map.get(component.getClass()).add((ICollectionComponent) component);
            }
        }
        for (Map.Entry<Class<?>, List<ICollectionComponent>> entry : map.entrySet()) {
            Class<?> type = entry.getKey();
            for (ICollectionComponent e : entry.getValue()) {
                ((ICollectionComponent) (type.cast(entry.getValue().get(0)))).handleResource(folder, entry.getValue());
            }
        }
        FileUtils.compressWithoutRoot(folder, output);
    }

    /**
     * Generate the pack.mcmeta file for the resourcepack.
     *
     * @param file The pack.mcmeta file.
     * @throws IOException Throw when the file creation failed.
     */
    public void generatePackMCMeta(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileConfiguration config = YaResourcepackManager.getInstance().getConfig();
        JsonObject root = new JsonObject();
        JsonObject pack = new JsonObject();
        pack.add("pack_format", new JsonPrimitive(config.getInt("resourcepack.pack_format")));
        pack.add("description", new JsonPrimitive(config.getString("resourcepack.description")));
        root.add("pack", pack);
        FileOutputStream outputStream = new FileOutputStream(file);
        for (byte singleByte : root.toString().getBytes(StandardCharsets.UTF_8)) outputStream.write(singleByte);
        outputStream.close();
    }

    public void startService() throws IOException {
        logger.info("Resourcepack service starting...");
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        logger.info("Resourcepack service started.");
    }

    public void stopService() {
        logger.info("Resourcepack service stopping...");
        stop();
        logger.info("Resourcepack service stopped.");
    }

    @Override
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        try {
            FileInputStream file = new FileInputStream(this.file);
            return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, mimeTypes().get("zip"), file, file.available());
        } catch (IOException e) {
            e.printStackTrace();
            return newFixedLengthResponse(NanoHTTPD.Response.Status.NOT_FOUND, mimeTypes().get("txt"), "404");
        }
    }
}
package io.github.apjifengc.yaresourcepackmanager.pack;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import io.github.apjifengc.yaresourcepackmanager.component.IComponent;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PackBuilder {
    public static void build(File folder, File output, List<IComponent> components) throws IOException {
        String separator = File.separator;
        generatePackMCMeta(new File(folder + separator + "pack.mcmeta"));
        for (IComponent component : components) {
            switch (component.getType()) {
                case MULTIPLE_FILE:
                    InputStream stream = component.getFile();
                    File file = new File(folder + separator + component.getFilePath());
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileStream = new FileOutputStream(file);
                    while (stream.available() > 0) {
                        fileStream.write(stream.read());
                    }
                    fileStream.close();
                    break;
                case SINGLE_JSON_FILE:
                    // TODO might create another interface for this
            }
        }
        FileUtils.compressWithoutRoot(folder, output);
    }

    public static void generatePackMCMeta(File file) throws IOException {
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
}
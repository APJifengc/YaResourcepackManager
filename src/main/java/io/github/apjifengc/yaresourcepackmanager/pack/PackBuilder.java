package io.github.apjifengc.yaresourcepackmanager.pack;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import io.github.apjifengc.yaresourcepackmanager.component.Model;
import io.github.apjifengc.yaresourcepackmanager.component.interfaces.*;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackBuilder {
    public static void build(File folder, File output, List<IComponent> components) throws IOException {
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

    public static void generatePackMCMeta(File file) throws IOException {
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
}
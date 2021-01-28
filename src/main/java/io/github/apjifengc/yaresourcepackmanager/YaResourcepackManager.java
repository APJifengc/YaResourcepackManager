package io.github.apjifengc.yaresourcepackmanager;

import com.rabbitown.yalib.command.SimpleCommandRemote;
import io.github.apjifengc.yaresourcepackmanager.command.DebugCommand;
import io.github.apjifengc.yaresourcepackmanager.command.MainCommand;
import io.github.apjifengc.yaresourcepackmanager.component.IComponent;
import io.github.apjifengc.yaresourcepackmanager.component.Model;
import io.github.apjifengc.yaresourcepackmanager.component.Texture;
import io.github.apjifengc.yaresourcepackmanager.component.TextureAnimation;
import io.github.apjifengc.yaresourcepackmanager.pack.PackBuilder;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;
import io.github.apjifengc.yaresourcepackmanager.util.Pair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class YaResourcepackManager extends JavaPlugin {
    private static YaResourcepackManager instance;

    public YaResourcepackManager() {
        instance = this;
    }

    public static YaResourcepackManager getInstance() {
        return instance;
    }

    public List<IComponent> registries = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getLogger().info("Start loading component...");
        // Test
        registry(new Model(new ByteArrayInputStream("this is model!".getBytes(StandardCharsets.UTF_8)), "custom/test_model"));
        registry(new Model(new ByteArrayInputStream("There's two!!".getBytes(StandardCharsets.UTF_8)), "custom/test_model2"));
        registry(new Texture(new ByteArrayInputStream(":)".getBytes(StandardCharsets.UTF_8)), "custom/smile"));
        registry(new Texture(new ByteArrayInputStream(":) movable smile!!!".getBytes(StandardCharsets.UTF_8)), "custom/smile_ani"));
        registry(new TextureAnimation("custom/smilea_ani", 5, Arrays.asList(
                new Pair<>(0,null),
                new Pair<>(1,null),
                new Pair<>(0,null)
        )));
        new MainCommand().register();
        new DebugCommand().register();
        new BukkitRunnable() {
            @Override
            public void run() {
                startPack();
            }
        }.runTaskLaterAsynchronously(this, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void startPack() {
        getLogger().info("Deleting old resourcepack...");
        File folder = new File(getDataFolder() + File.separator + "resourcepack" + File.separator);
        File file = new File(getDataFolder() + File.separator + "packed_resourcepack.zip");
        try {
            if (folder.exists()) FileUtils.deleteFile(folder);
            if (file.exists()) FileUtils.deleteFile(file);
        } catch (IOException e) {
            getLogger().warning("Cannot delete the files previously generated. Please check if you're using it.");
            e.printStackTrace();
            return;
        }
        getLogger().info("Start packing resourcepack...");
        try {
            PackBuilder.build(folder, file, registries);
        } catch (IOException e) {
            getLogger().warning("Pack failed. Please report this at https://github.com/Yallage/YaResourcepackManager .");
            e.printStackTrace();
            return;
        }
        getLogger().info("Pack complete. File: " + file);
    }

    public void registry(IComponent component) {
        registries.add(component);
        getLogger().info("Load component " + component);
    }
}

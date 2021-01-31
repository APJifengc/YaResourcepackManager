package io.github.apjifengc.yaresourcepackmanager;

import com.rabbitown.yalib.YaLibCentral;
import com.rabbitown.yalib.module.locale.I18NPlugin;
import com.rabbitown.yalib.module.locale.Locale;
import com.rabbitown.yalib.module.locale.YLocale;
import com.rabbitown.yalib.util.Commands;
import io.github.apjifengc.yaresourcepackmanager.command.DebugCommand;
import io.github.apjifengc.yaresourcepackmanager.command.MainCommand;
import io.github.apjifengc.yaresourcepackmanager.component.*;
import io.github.apjifengc.yaresourcepackmanager.component.interfaces.IComponent;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;
import io.github.apjifengc.yaresourcepackmanager.util.Pair;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>YaResourcepackManager</h1>
 * API for managing texture packs on the server
 *
 * @author APJifengc
 */
public final class YaResourcepackManager extends JavaPlugin implements Listener, I18NPlugin {
    private static YaResourcepackManager instance;

    public YaResourcepackManager() {
        instance = this;
    }

    public static YaResourcepackManager getInstance() {
        return instance;
    }

    public List<IComponent> registries = new ArrayList<>();

    private File resourcePack;

    private ResourcePack pack;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Commands.Companion.newPluginCommand("yrm", this);
        YaLibCentral.INSTANCE.registerPlugin(this);
        saveDefaultConfig();
        getLogger().info("Start loading component...");
        // Test
        registry(new Model(new ByteArrayInputStream("this is model!".getBytes(StandardCharsets.UTF_8)), "custom/test_model"));
        registry(new Model(new ByteArrayInputStream("There's two!!".getBytes(StandardCharsets.UTF_8)), "custom/test_model2"));
        registry(new Texture(new ByteArrayInputStream(":)".getBytes(StandardCharsets.UTF_8)), "custom/smile"));
        registry(new AnimatedTexture(new ByteArrayInputStream(":) movable smile!!!".getBytes(StandardCharsets.UTF_8)), "custom/smile_ani", 5, Arrays.asList(
                new Pair<>(0,null),
                new Pair<>(1,5),
                new Pair<>(0,null)
        )));
        registry(new FontCharacter(new ByteArrayInputStream("font1".getBytes(StandardCharsets.UTF_8)), "default", "font/font1", 16, 32, "\ue001"));
        registry(new FontCharacter(new ByteArrayInputStream("font2".getBytes(StandardCharsets.UTF_8)), "default", "font/font2", 16, 32, "\ue002"));
        registry(new FontCharacter(new ByteArrayInputStream("font3".getBytes(StandardCharsets.UTF_8)), "default", "font/font3", 16, 32, "\ue003"));
        registry(new FontCharacter(new ByteArrayInputStream("font4".getBytes(StandardCharsets.UTF_8)), "test", "font/font4", 16, 32, "\ue004"));
        System.out.println(new SoundEvent.Sound("hi", 1.0, 1.0, null, false, 1.0, false, SoundEvent.Sound.SoundType.SOUND).getJson());

        new MainCommand().register();
        new DebugCommand().register();
        new BukkitRunnable() {
            @Override
            public void run() {
                restartService();
            }
        }.runTaskLaterAsynchronously(this, 1);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        pack.stopService();
    }

    /**
     * Restart the main resourcepack service.
     */
    public void restartService() {
        if (pack != null) pack.stopService();
        pack = new ResourcePack(getConfig().getInt("publish.port", 25566), resourcePack);
        getLogger().info("Deleting old resourcepack...");
        File folder = new File(getDataFolder() + File.separator + "resourcepack" + File.separator);
        resourcePack = new File(getDataFolder() + File.separator + "packed_resourcepack.zip");
        try {
            if (folder.exists()) FileUtils.deleteFile(folder);
            if (resourcePack.exists()) FileUtils.deleteFile(resourcePack);
        } catch (IOException e) {
            getLogger().warning("Cannot delete the files previously generated. Please check if you're using it.");
            e.printStackTrace();
            return;
        }
        getLogger().info("Start packing resourcepack...");
        try {
            pack.build(folder, resourcePack, registries);
        } catch (IOException e) {
            getLogger().warning("Pack failed. Please report this at https://github.com/Yallage/YaResourcepackManager .");
            e.printStackTrace();
            return;
        }
        getLogger().info("Pack complete. File: " + resourcePack);
        if (pack != null) pack.stopService();
        try {
            pack = new ResourcePack(getConfig().getInt("publish.port", 25566), resourcePack);
            pack.startService();
        } catch (IOException e) {
            getLogger().warning("Failed to start the server. Please check if the port is used.");
            e.printStackTrace();
            return;
        }
        getLogger().info("Pack published!");
        reloadAllPlayerResourcepack();
    }

    /**
     * Register a resourcepack component. <br/>
     *
     * @see IComponent
     * @param component The component to register.
     */
    public void registry(IComponent component) {
        registries.add(component);
        getLogger().info("Load component " + component);
    }

    /**
     * Reload all player's resourcepack.
     */
    public void reloadAllPlayerResourcepack() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            reloadPlayerResourcepack(player);
        }
    }

    /**
     * Reload one player's resourcepack.
     *
     * @param player The player.
     */
    public void reloadPlayerResourcepack(Player player) {
        try {
            player.setResourcePack(getResourcepackURL(), FileUtils.getFileSHA1(resourcePack));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the resourcepack's URL.
     *
     * @return The URL of the resourcepack.
     */
    public String getResourcepackURL() {
        return "http://" + getConfig().getString("publish.resource_pack_ip") + "/pack.zip";
    }

    @EventHandler
    void onResourcepackLoad(PlayerResourcePackStatusEvent event) {
        PlayerResourcePackStatusEvent.Status status = event.getStatus();
        Player player = event.getPlayer();
        if (getConfig().getBoolean("force-load-pack", false)) {
            switch (status) {
                case DECLINED:
                    player.kickPlayer(YLocale.getMessage(player, "force.resourcepack-denied"));
                    break;
                case FAILED_DOWNLOAD:
                    player.kickPlayer(YLocale.getMessage(player, "force.download-failed"));
                    break;
            }
        } else {
            TextComponent button = new TextComponent(YLocale.getMessage(player, "normal.install-resourcepack.text"));
            button.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(YLocale.getMessage(player, "normal.install-resourcepack.hover-text"))));
            button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/yrm load"));
            switch (status) {
                case DECLINED:
                    player.spigot().sendMessage(
                            new TextComponent(YLocale.getMessage(player, "normal.resourcepack-denied"))
                    );
                    break;
                case FAILED_DOWNLOAD:
                    player.spigot().sendMessage(
                            new TextComponent(YLocale.getMessage(player, "normal.download-failed")),
                            button
                    );
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        reloadPlayerResourcepack(event.getPlayer());
    }

    @Override
    public Locale getLocale() {
        return new Locale(this, "zh_CN");
    }
}

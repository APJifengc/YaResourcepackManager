package io.github.apjifengc.yaresourcepackmanager.command;

import com.rabbitown.yalib.command.SimpleCommandRemote;
import com.rabbitown.yalib.command.annotation.Access;
import com.rabbitown.yalib.command.annotation.Action;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class MainCommand extends SimpleCommandRemote {
    public MainCommand() {
        super("yrm", YaResourcepackManager.getInstance());
    }

    @Access(permission = "yrm.reload")
    @Action(action = "reload")
    public void reload() {
        YaResourcepackManager.getInstance().startPack();
    }
}

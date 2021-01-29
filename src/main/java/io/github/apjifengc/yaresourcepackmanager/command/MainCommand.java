package io.github.apjifengc.yaresourcepackmanager.command;

import com.rabbitown.yalib.command.CommandSenderType;
import com.rabbitown.yalib.command.SimpleCommandRemote;
import com.rabbitown.yalib.command.annotation.Access;
import com.rabbitown.yalib.command.annotation.Action;
import com.sun.org.glassfish.gmbal.ParameterNames;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand extends SimpleCommandRemote {
    public MainCommand() {
        super("yrm", YaResourcepackManager.getInstance());
    }

    @Access(permission = "yrm.reload")
    @Action(action = "reload")
    public void reload() {
        YaResourcepackManager.getInstance().startPack();
    }

    @Access(permission = "yrm.load", sender = CommandSenderType.PLAYER)
    @Action(action = "load")
    public void load(CommandSender sender) {
        YaResourcepackManager.getInstance().reloadPlayerResourcepack((Player) sender);
    }
}

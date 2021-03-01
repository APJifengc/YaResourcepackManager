package io.github.apjifengc.yaresourcepackmanager.command;

import com.rabbitown.yalib.module.command.CommandSenderType;
import com.rabbitown.yalib.module.command.SimpleCommandRemote;
import com.rabbitown.yalib.module.command.annotation.Access;
import com.rabbitown.yalib.module.command.annotation.Action;
import com.rabbitown.yalib.module.command.annotation.Parameter;
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
        YaResourcepackManager.getInstance().restartService();
    }

    @Access(permission = "yrm.load", sender = CommandSenderType.PLAYER)
    @Action(action = "load")
    @Parameter(params = {"sender"})
    public void load(CommandSender sender) {
        YaResourcepackManager.getInstance().reloadPlayerResourcepack((Player) sender);
    }
}

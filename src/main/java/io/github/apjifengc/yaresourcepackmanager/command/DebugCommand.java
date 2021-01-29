package io.github.apjifengc.yaresourcepackmanager.command;

import com.rabbitown.yalib.command.CommandSenderType;
import com.rabbitown.yalib.command.SimpleCommandRemote;
import com.rabbitown.yalib.command.annotation.Access;
import com.rabbitown.yalib.command.annotation.Action;
import com.rabbitown.yalib.command.annotation.Path;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

@Path(path = "debug")
public class DebugCommand extends SimpleCommandRemote {
    public DebugCommand() {
        super("yrm", YaResourcepackManager.getInstance());
    }

    @Access(permission = "yrm.debug", sender = CommandSenderType.PLAYER)
    @Action(action = "loadResourcePack {url}")
    public void loadResourcePack(Player sender, String url) {
        sender.setResourcePack(url);
    }

    @Access(permission = "yrm.debug", sender = CommandSenderType.PLAYER)
    @Action(action = "loadResourcePack {url} {sha1}")
    public void loadResourcePack(Player sender, String url, String sha1) {
        sender.setResourcePack(url, sha1.getBytes(StandardCharsets.UTF_8));
    }
}

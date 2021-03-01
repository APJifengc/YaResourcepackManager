package io.github.apjifengc.yaresourcepackmanager.component.model.enums;

import lombok.Getter;

import java.net.http.WebSocket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Specifies whether a face does not need to be rendered when there is a block touching it in the specified position.
 * It also determines the side of the block to use the light level from for lighting the face, and if unset, defaults
 * to the side. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 */
public enum CullFace {
    DOWN("down"),
    UP("up"),
    NORTH("north"),
    SOUTH("south"),
    WEST("west"),
    EAST("east");
    @Getter
    private final String minecraftName;

    CullFace(String minecraftName) {
        this.minecraftName = minecraftName;
    }

    private static final Map<String, CullFace> map = Arrays.stream(values()).
            collect(toMap(CullFace::getMinecraftName, e -> e));
    public static CullFace fromMinecraftName(String minecraftName) {
        return map.get(minecraftName);
    }
}

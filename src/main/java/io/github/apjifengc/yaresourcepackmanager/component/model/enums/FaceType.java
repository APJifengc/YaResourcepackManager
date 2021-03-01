package io.github.apjifengc.yaresourcepackmanager.component.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Contains the properties of the specified face. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 */
public enum FaceType {
    DOWN("down"),
    UP("up"),
    NORTH("north"),
    SOUTH("south"),
    WEST("west"),
    EAST("east");
    @Getter
    private final String minecraftName;

    FaceType(String minecraftName) {
        this.minecraftName = minecraftName;
    }

    private static final Map<String, FaceType> map = Arrays.stream(values()).
            collect(toMap(FaceType::getMinecraftName, e -> e));
    public static FaceType fromMinecraftName(String minecraftName) {
        return map.get(minecraftName);
    }
}

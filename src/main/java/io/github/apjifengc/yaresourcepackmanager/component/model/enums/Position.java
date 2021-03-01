package io.github.apjifengc.yaresourcepackmanager.component.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Place where an item model is displayed. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 */
public enum Position {
    THIRD_PERSON_RIGHT_HAND("thirdperson_righthand"),
    THIRD_PERSON_LEFT_HAND("thirdperson_lefthand"),
    FIRST_PERSON_RIGHT_HAND("firstperson_righthand"),
    FIRST_PERSON_LEFT_HAND("firstperson_lefthand"),
    GUI("gui"),
    HEAD("head"),
    GROUND("ground"),
    /**
     * <code>Fixed</code> refers to item frames
     */
    FIXED("fixed");
    @Getter
    private final String minecraftName;

    Position(String minecraftName) {
        this.minecraftName = minecraftName;
    }

    private static final Map<String, Position> map = Arrays.stream(values()).
            collect(toMap(Position::getMinecraftName, e -> e));
    public static Position fromMinecraftName(String minecraftName) {
        return map.get(minecraftName);
    }
}

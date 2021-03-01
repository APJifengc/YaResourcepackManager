package io.github.apjifengc.yaresourcepackmanager.component.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Can be <code>"front"</code> or <code>"side"</code>. If set to <code>"side"</code>, the model is rendered like a
 * block. If set to <code>"front"</code>, model is shaded like a flat item.<br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 */
public enum GuiLight {
    /**
     * The model is shaded like a flat item.
     */
    FRONT("front"),
    /**
     * The model is rendered like a block.
     */
    SIDE("side");
    @Getter
    private final String minecraftName;

    GuiLight(String minecraftName) {
        this.minecraftName = minecraftName;
    }

    private static final Map<String, GuiLight> map = Arrays.stream(values()).
            collect(toMap(GuiLight::getMinecraftName, e -> e));
    public static GuiLight fromMinecraftName(String minecraftName) {
        return map.get(minecraftName);
    }
}

package io.github.apjifengc.yaresourcepackmanager.component.model.misc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Axis;
import org.bukkit.util.Vector;

/**
 * Defines the rotation of an element. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 */
@Data
public class Rotation {
    private Vector origin;
    private Axis axis;
    private Float angle;
    private Boolean rescale;

    /**
     * Create a rotation of an element.
     *
     * @param origin Sets the center of the rotation.
     * @param axis   Specifies the direction of rotation.
     * @param angle  Specifies the angle of rotation. Can be 45 through -45 degrees in 22.5 degree increments.
     */
    public Rotation(Vector origin, Axis axis, Float angle) {
        this(origin, axis, angle, null);
    }

    /**
     * Create a rotation of an element.
     *
     * @param origin  Sets the center of the rotation.
     * @param axis    Specifies the direction of rotation.
     * @param angle   Specifies the angle of rotation. Can be 45 through -45 degrees in 22.5 degree increments.
     * @param rescale Specifies whether or not to scale the faces across the whole block. Can be true or false.
     *                Defaults to false.
     */
    public Rotation(Vector origin, Axis axis, Float angle, Boolean rescale) {
        this.origin = origin;
        this.axis = axis;
        this.angle = angle;
        this.rescale = rescale;
    }
}

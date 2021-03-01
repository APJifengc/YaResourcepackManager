package io.github.apjifengc.yaresourcepackmanager.component.model.misc;

import io.github.apjifengc.yaresourcepackmanager.component.model.enums.FaceType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.util.Vector;

import java.util.Map;

/**
 * Contains an element of the model. It can have only cubic forms. If both <code>"parent"</code> and <code>"elements"
 * </code> are set, the <code>"elements"</code> tag overrides the <code>"elements"</code> tag from the previous model.
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 */
@Data
public class Element {
    private Vector from;
    private Vector to;
    private Rotation rotation;
    private Boolean shade;
    private Map<FaceType, Face> faces;

    /**
     * Create a element in Model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param from Start point of a cube according to the scheme <code>[x, y, z]</code>. Values must be between -16 and 32.
     * @param to Stop point of a cube according to the scheme <code>[x, y, z]</code>. Values must be between -16 and 32.
     * @param rotation Defines the rotation of an element. <br/>
     *                 See {@link Rotation}.
     * @param shade Defines if shadows are rendered (<code>true</code> - default), not (<code>false</code>).
     * @param faces Holds all the faces of the cube. If a face is left out, it does not render.
     * @see Rotation
     */
    public Element(Vector from, Vector to, Rotation rotation, boolean shade, Map<FaceType, Face> faces) {
        this.from = from;
        this.to = to;
        this.rotation = rotation;
        this.shade = shade;
        this.faces = faces;
    }
}

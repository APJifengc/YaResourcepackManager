package io.github.apjifengc.yaresourcepackmanager.component.model.misc;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.apjifengc.yaresourcepackmanager.component.model.enums.Position;
import io.github.apjifengc.yaresourcepackmanager.util.JsonUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.util.Vector;

import static io.github.apjifengc.yaresourcepackmanager.util.JsonUtil.asVector;

/**
 * Holds its rotation, translation and scale for the specified situation. Note that translations are applied to
 * the model before rotations. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 */
@Data
public class Display {
    private Vector rotation;
    private Vector translation;
    private Vector scale;

    /**
     * Construct an empty Display.
     */
    public Display() {
        this.rotation = null;
        this.scale = null;
        this.translation = null;
    }

    /**
     * Construct a new display setting. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param rotation    Specifies the rotation of the model according to the scheme <code>[x, y, z]</code>.
     * @param translation Specifies the position of the model according to the scheme <code>[x, y, z]</code>.
     *                    If the value is greater than 80, it is displayed as 80. If the value is less than -80,
     *                    it is displayed as -80.
     * @param scale       Specifies the scale of the model according to the scheme <code>[x, y, z]</code>. If
     *                    the value is greater than 4, it is displayed as 4.
     */
    public Display(Vector rotation, Vector translation, Vector scale) {
        this.rotation = rotation;
        this.translation = translation;
        this.scale = scale;
    }

    /**
     * Get the Json element for the Display.
     *
     * @return The Json element.
     */
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.add("rotation", JsonUtil.fromVector(this.rotation));
        object.add("translation", JsonUtil.fromVector(this.translation));
        object.add("scale", JsonUtil.fromVector(this.scale));
        return object;
    }

    /**
     * Get the Display from a Json element.
     *
     * @param element The Json element.
     * @return The Display.
     */
    public static Display fromJson(JsonObject element) {
        return new Display(
                asVector(element.get("rotation")),
                asVector(element.get("translation")),
                asVector(element.get("scale"))
        );
    }

}

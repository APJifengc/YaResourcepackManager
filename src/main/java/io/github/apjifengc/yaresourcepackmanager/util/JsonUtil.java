package io.github.apjifengc.yaresourcepackmanager.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.bukkit.util.Vector;

public class JsonUtil {
    /**
     * Create a Vector from a Json element.
     *
     * @param element The Json element.
     * @return The Vector.
     */
    public static Vector asVector(JsonElement element) {
        JsonArray array = (JsonArray) element;
        return new Vector(
                array.get(0).getAsInt(),
                array.get(1).getAsInt(),
                array.get(2).getAsInt()
        );
    }

    /**
     * Create a Json array from a Vector.
     *
     * @param vector The Vector.
     * @return The Json array.
     */
    public static JsonArray fromVector(Vector vector) {
        JsonArray array = new JsonArray();
        array.add(vector.getX());
        array.add(vector.getY());
        array.add(vector.getZ());
        return array;
    }
}

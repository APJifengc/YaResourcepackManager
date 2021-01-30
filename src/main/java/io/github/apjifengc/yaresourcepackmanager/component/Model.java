package io.github.apjifengc.yaresourcepackmanager.component;

import java.io.InputStream;

/**
 * A model in the resourcepack.
 *
 * @author APJifengc
 */
public class Model extends SimpleIndependentComponent {
    public static final Model CUBE_ALL = new Model("block/cube_all");
    public static final Model CUBE = new Model("block/cube");
    public static final Model CUBE_BOTTOM_TOP = new Model("block/cube_bottom_top");
    public static final Model CUBE_COLUMN = new Model("block/cube_column");
    public static final Model CUBE_COLUMN_HORIZONTAL = new Model("block/cube_column_horizontal");
    public static final Model CUBE_DIRECTIONAL = new Model("block/cube_directional");
    public static final Model CUBE_MIRRORED = new Model("block/cube_mirrored");
    public static final Model CUBE_MIRRORED_ALL = new Model("block/cube_mirrored_all");
    public static final Model ITEM = new Model("item/handheld");

    /**
     * Create a new model.
     *
     * @param path The path in the resourcepack.
     */
    public Model(String path) {
        super(path,null);
    }

    /**
     * Create a new texture.
     *
     * @param fileInputStream The model file's input stream.
     * @param path The path in the resourcepack.
     */
    public Model(InputStream fileInputStream, String path) {
        super(path, fileInputStream);
    }

    @Override
    public String getExtension() {
        return ".json";
    }

    @Override
    public String getBasePath() {
        return "assets/minecraft/models/";
    }

    @Override
    public String toString() {
        return "Model{" +
                "path='" + path + '\'' +
                '}';
    }
}

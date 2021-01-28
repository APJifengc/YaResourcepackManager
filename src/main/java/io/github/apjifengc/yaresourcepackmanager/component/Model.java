package io.github.apjifengc.yaresourcepackmanager.component;

import com.google.gson.JsonObject;

import java.io.InputStream;

public class Model implements IComponent{
    public static final Model CUBE_ALL = new Model("block/cube_all");
    public static final Model CUBE = new Model("block/cube");
    public static final Model CUBE_BOTTOM_TOP = new Model("block/cube_bottom_top");
    public static final Model CUBE_COLUMN = new Model("block/cube_column");
    public static final Model CUBE_COLUMN_HORIZONTAL = new Model("block/cube_column_horizontal");
    public static final Model CUBE_DIRECTIONAL = new Model("block/cube_directional");
    public static final Model CUBE_MIRRORED = new Model("block/cube_mirrored");
    public static final Model CUBE_MIRRORED_ALL = new Model("block/cube_mirrored_all");
    public static final Model ITEM = new Model("item/handheld");

    public final InputStream fileInputStream;
    public final String path;
    public Model(String path) {
        this.path = path;
        this.fileInputStream = null;
    }
    public Model(InputStream fileInputStream, String path) {
        this.path = path;
        this.fileInputStream = fileInputStream;
    }

    @Override
    public String getFilePath() {
        return "assets/minecraft/models/" + path + ".json";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MULTIPLE_FILE;
    }

    @Override
    public InputStream getFile() {
        return fileInputStream;
    }

    @Override
    public JsonObject getJson() {
        return null;
    }

    @Override
    public String toString() {
        return "Model{" +
                "path='" + path + '\'' +
                '}';
    }
}

package io.github.apjifengc.yaresourcepackmanager.component;

import com.google.gson.JsonObject;

import java.io.InputStream;

public class Texture implements IComponent{
    public final String path;
    private final InputStream inputStream;

    public Texture(InputStream inputStream, String path) {
        this.path = path;
        this.inputStream = inputStream;
    }

    @Override
    public String getFilePath() {
        return "assets/minecraft/textures/" + path + ".png";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MULTIPLE_FILE;
    }

    @Override
    public InputStream getFile() {
        return inputStream;
    }

    @Override
    public JsonObject getJson() {
        return null;
    }

    @Override
    public String toString() {
        return "Texture{" +
                "path='" + path + '\'' +
                '}';
    }
}

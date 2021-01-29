package io.github.apjifengc.yaresourcepackmanager.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.apjifengc.yaresourcepackmanager.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TextureAnimation implements IComponent {
    private final JsonElement jsonElement;
    public final String path;

    public TextureAnimation(String path, Boolean interpolate, Integer width, Integer height, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        this.path = path;
        JsonObject animation = new JsonObject(), root = new JsonObject();
        JsonArray frame = new JsonArray();
        if (width != null) animation.add("interpolate", new JsonPrimitive(interpolate));
        if (width != null) animation.add("width", new JsonPrimitive(width));
        if (width != null) animation.add("height", new JsonPrimitive(height));
        animation.add("frametime", new JsonPrimitive(frameTime));
        for (Pair<Integer, Integer> pair : frames) {
            if (pair.getValue() == null) {
                frame.add(pair.getKey());
            } else {
                JsonObject singleFrame = new JsonObject();
                singleFrame.add("index", new JsonPrimitive(pair.getKey()));
                singleFrame.add("time", new JsonPrimitive(pair.getValue()));
                frame.add(singleFrame);
            }
        }
        animation.add("frames", frame);
        root.add("animation", animation);
        jsonElement = root;
    }

    public TextureAnimation(String path, Boolean interpolate, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        this(path, interpolate, null, null, frameTime, frames);
    }

    public TextureAnimation(String path, Boolean interpolate, List<Pair<Integer, Integer>> frames) {
        this(path, interpolate, null, null, 1, frames);
    }

    public TextureAnimation(String path, Integer width, Integer height, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        this(path, null, width, height, frameTime, frames);
    }

    public TextureAnimation(String path, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        this(path, null, null, null, frameTime, frames);
    }

    public TextureAnimation(String path, List<Pair<Integer, Integer>> frames) {
        this(path, null, null, null, 1, frames);
    }

    @Override
    public String getFilePath() {
        return "assets/minecraft/textures/" + path + ".png.mcmeta";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.MULTIPLE_FILE;
    }

    @Override
    public InputStream getFile() {
        return new ByteArrayInputStream(jsonElement.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public JsonObject getJson() {
        return null;
    }

    @Override
    public String toString() {
        return "TextureAnimation{" +
                "path='" + path + '\'' +
                '}';
    }
}

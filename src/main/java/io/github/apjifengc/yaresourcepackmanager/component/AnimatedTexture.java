package io.github.apjifengc.yaresourcepackmanager.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;
import io.github.apjifengc.yaresourcepackmanager.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AnimatedTexture extends Texture {
    private final JsonElement jsonElement;
    public final Boolean interpolate;
    public final Integer width;
    public final Integer height;
    public final Integer frameTime;
    public final List<Pair<Integer, Integer>> frames;

    public AnimatedTexture(InputStream inputStream, String path, Boolean interpolate, Integer width, Integer height, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        super(inputStream, path);
        this.interpolate = interpolate;
        this.width = width;
        this.height = height;
        this.frameTime = frameTime;
        this.frames = frames;
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

    public AnimatedTexture(InputStream inputStream, String path, Boolean interpolate, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        this(inputStream, path, interpolate, null, null, frameTime, frames);
    }

    public AnimatedTexture(InputStream inputStream, String path, Boolean interpolate, List<Pair<Integer, Integer>> frames) {
        this(inputStream, path, interpolate, null, null, 1, frames);
    }

    public AnimatedTexture(InputStream inputStream, String path, Integer width, Integer height, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        this(inputStream, path, null, width, height, frameTime, frames);
    }

    public AnimatedTexture(InputStream inputStream, String path, Integer frameTime, List<Pair<Integer, Integer>> frames) {
        this(inputStream, path, null, null, null, frameTime, frames);
    }

    public AnimatedTexture(InputStream inputStream, String path, List<Pair<Integer, Integer>> frames) {
        this(inputStream, path, null, null, null, 1, frames);
    }

    @Override
    public void handleResource(File resourcePack) throws IOException {
        super.handleResource(resourcePack);
        File file = new File(resourcePack + File.separator + getBasePath() + path + ".png.mcmeta");
        ByteArrayInputStream input = new ByteArrayInputStream(jsonElement.toString().getBytes(StandardCharsets.UTF_8));
        FileUtils.writeFile(input, file);
    }

    @Override
    public String toString() {
        return "TextureAnimation{" +
                "path='" + path + '\'' +
                '}';
    }
}

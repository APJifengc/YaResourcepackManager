package io.github.apjifengc.yaresourcepackmanager.component.texture;

import io.github.apjifengc.yaresourcepackmanager.component.SimpleIndependentComponent;

import java.io.InputStream;

/**
 * A texture in the resourcepack.
 *
 * @author APJifengc
 */
public class Texture extends SimpleIndependentComponent {

    /**
     * Create a new texture.
     *
     * @param path The path in the resourcepack.
     */
    public Texture(String path) {
        this(null, path);
    }

    /**
     * Create a new texture.
     *
     * @param inputStream The texture file's input stream.
     * @param path        The path in the resourcepack.
     */
    public Texture(InputStream inputStream, String path) {
        super(path, inputStream);
    }

    /**
     * Create a texture component from an string. <br/>
     * If the string starts with <code>#</code>, then it will return a {@link VariableTexture}. Else if will
     * return a normal {@link Texture}. <br/>
     * This is only used for placeholder, it can't be registered.
     *
     * @param string The string.
     * @return The texture.
     */
    public static Texture fromString(String string) {
        if (string.charAt(0) == '#') return new VariableTexture(string.substring(1));
        else return new Texture(string);
    }

    @Override
    public String getBasePath() {
        return "assets/minecraft/textures/";
    }

    @Override
    public String getExtension() {
        return ".png";
    }

    @Override
    public String toString() {
        return "Texture{" +
                "path='" + getPath() + '\'' +
                '}';
    }
}

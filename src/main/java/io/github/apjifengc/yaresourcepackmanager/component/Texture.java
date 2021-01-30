package io.github.apjifengc.yaresourcepackmanager.component;

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
     * @param inputStream The texture file's input stream.
     * @param path The path in the resourcepack.
     */
    public Texture(InputStream inputStream, String path) {
        super(path, inputStream);
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
                "path='" + path + '\'' +
                '}';
    }
}

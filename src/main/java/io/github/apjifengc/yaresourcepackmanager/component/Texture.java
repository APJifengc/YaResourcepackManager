package io.github.apjifengc.yaresourcepackmanager.component;

import java.io.InputStream;

public class Texture extends SimpleIndependentComponent {

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
                "path='" + getPath() + '\'' +
                '}';
    }
}

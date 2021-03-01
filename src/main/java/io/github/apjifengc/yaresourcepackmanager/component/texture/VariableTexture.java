package io.github.apjifengc.yaresourcepackmanager.component.texture;

import io.github.apjifengc.yaresourcepackmanager.component.SimpleIndependentComponent;
import lombok.Getter;

import java.io.InputStream;

/**
 * A variable texture holder for the models.
 *
 * @author APJifengc
 */
public class VariableTexture extends Texture {
    @Getter private final String tag;
    public VariableTexture(String tag) {
        super(null);
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "VariableTexture{" +
                "tag='" + getTag() + '\'' +
                '}';
    }
}

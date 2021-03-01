package io.github.apjifengc.yaresourcepackmanager.component;

import java.io.InputStream;

/**
 * A block state file in the resourcepack.
 *
 * @author APJifengc
 */
public class BlockState extends SimpleIndependentComponent {
    /**
     * Create a new block state file.
     *
     * @param fileInputStream The block state file file's input stream.
     * @param path            The path in the resourcepack.
     */
    public BlockState(InputStream fileInputStream, String path) {
        super(path, fileInputStream);
    }

    @Override
    public String getExtension() {
        return ".json";
    }

    @Override
    public String getBasePath() {
        return "assets/minecraft/blockstates/";
    }

    @Override
    public String toString() {
        return "Model{" +
                "path='" + getPath() + '\'' +
                '}';
    }
}

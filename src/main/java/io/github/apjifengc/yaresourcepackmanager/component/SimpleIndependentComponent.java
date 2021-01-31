package io.github.apjifengc.yaresourcepackmanager.component;

import io.github.apjifengc.yaresourcepackmanager.component.interfaces.IIndependentComponent;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple implement of the IIndependentComponent interface. <br/>
 * The path is split into three parts: Base path, path, and extension. <br/>
 * eg. The texture "{@code assets/minecraft/textures/custom/hi.png}" can split in this: <br/>
 * "{@code assets/minecraft/textures/}" is the base path. <br/>
 * "{@code custom/hi}" is the path. <br/>
 * "{@code .png}" is the extension. <br/>
 *
 * @author APJifengc
 */
public abstract class SimpleIndependentComponent implements IIndependentComponent {

    /**
     * @see SimpleIndependentComponent
     */
    public final String path;
    private final InputStream inputStream;

    /**
     * Create a simple component.
     *
     * @param inputStream The file's input stream.
     * @param path        The path in the resourcepack.
     */
    public SimpleIndependentComponent(String path, InputStream inputStream) {
        this.path = path;
        this.inputStream = inputStream;
    }

    @Override
    public void handleResource(File resourcePack) throws IOException {
        File file = new File(resourcePack + File.separator + getBasePath() + path + getExtension());
        FileUtils.writeFile(inputStream, file);
    }

    /**
     * Get the file's extension.
     *
     * @see SimpleIndependentComponent
     */
    abstract public String getExtension();

    /**
     * Get the component's base path in the resourcepack.
     *
     * @see SimpleIndependentComponent
     */
    abstract public String getBasePath();
}

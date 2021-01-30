package io.github.apjifengc.yaresourcepackmanager.component;

import io.github.apjifengc.yaresourcepackmanager.component.interfaces.IIndependentComponent;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class SimpleIndependentComponent implements IIndependentComponent {
    public final String path;
    private final InputStream inputStream;

    public SimpleIndependentComponent(String path, InputStream inputStream) {
        this.path = path;
        this.inputStream = inputStream;
    }

    @Override
    public void handleResource(File resourcePack) throws IOException {
        File file = new File(resourcePack + File.separator + getBasePath() + path + getExtension());
        FileUtils.writeFile(inputStream, file);
    }

    public String getPath() {
        return path;
    }

    abstract public String getExtension();
    abstract public String getBasePath();
}

package io.github.apjifengc.yaresourcepackmanager.component.interfaces;

import java.io.File;
import java.io.IOException;

public interface IIndependentComponent extends IComponent {
    void handleResource(File resourcePack) throws IOException;
}

package io.github.apjifengc.yaresourcepackmanager.component.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ICollectionComponent extends IComponent {
    void handleResource(File resourcePack, List<ICollectionComponent> list) throws IOException;
}

package io.github.apjifengc.yaresourcepackmanager.component.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This is a collection component. <br/>
 * This will be generated only after all of this type's components was built.
 *
 * @author APJifengc
 */
public interface ICollectionComponent extends IComponent {
    /**
     * Handle the kind of resources.
     *
     * @param resourcePack The resourcepack folder.
     * @param list The list of all of this type's components.
     * @throws IOException Throw when a file error occurred.
     */
    void handleResource(File resourcePack, List<ICollectionComponent> list) throws IOException;
}

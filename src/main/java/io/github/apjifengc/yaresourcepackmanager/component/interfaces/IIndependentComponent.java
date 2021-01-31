package io.github.apjifengc.yaresourcepackmanager.component.interfaces;

import java.io.File;
import java.io.IOException;

/**
 * This is a independent component. <br/>
 * Every component will be generated once when the component has built.
 *
 * @author APJifengc
 */
public interface IIndependentComponent extends IComponent {
    /**
     * Handle the component.
     *
     * @param resourcePack The resourcepack folder.
     * @throws IOException Throw when a file error occurred.
     */
    void handleResource(File resourcePack) throws IOException;
}

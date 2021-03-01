package io.github.apjifengc.yaresourcepackmanager.component.interfaces;

/**
 * A component for the resourcepack. <br/>
 * A resourcepack can be created by many components. <br/>
 * There are two types of component, {@link IIndependentComponent} and {@link ICollectionComponent}.
 *
 * @author APJifengc
 */
public interface IComponent {
    /**
     * Return if the component is registrable.
     *
     * @return Is registrable.
     */
    boolean isRegistrable();
}

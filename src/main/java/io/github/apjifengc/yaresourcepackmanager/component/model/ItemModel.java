package io.github.apjifengc.yaresourcepackmanager.component.model;

import io.github.apjifengc.yaresourcepackmanager.component.model.enums.GuiLight;
import io.github.apjifengc.yaresourcepackmanager.component.model.enums.Position;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Display;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Element;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.ItemPredicate;
import io.github.apjifengc.yaresourcepackmanager.component.texture.Texture;
import io.github.apjifengc.yaresourcepackmanager.component.texture.VariableTexture;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A item model in the resourcepack. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 */
@SuppressWarnings("unused")
public class ItemModel extends Model {
    @Getter
    private final List<ItemPredicate> overrides;
    @Setter
    @Getter
    private GuiLight guiLight;

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param parent    Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                  <code>"elements"</code> tag from the previous model.
     * @param guiLight  See {@link GuiLight}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Model parent, GuiLight guiLight, List<ItemPredicate> overrides) {
        this(path, parent, new HashMap<>(), new HashMap<>(), new ArrayList<>(), guiLight, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param parent    Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                  <code>"elements"</code> tag from the previous model.
     * @param display   Holds the different places where item models are displayed. <br/>
     *                  See {@link Display}.
     * @param guiLight  See {@link GuiLight}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, GuiLight guiLight, List<ItemPredicate> overrides) {
        this(path, parent, display, new HashMap<>(), guiLight, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param textures  Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param guiLight  See {@link GuiLight}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Map<String, Texture> textures, GuiLight guiLight, List<ItemPredicate> overrides) {
        this(path, new HashMap<>(), textures, guiLight, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param display   Holds the different places where item models are displayed. <br/>
     *                  See {@link Display}.
     * @param textures  Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param guiLight  See {@link GuiLight}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Map<Position, Display> display, Map<String, Texture> textures,
                     GuiLight guiLight, List<ItemPredicate> overrides) {
        this(path, null, display, textures, guiLight, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param parent    Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                  <code>"elements"</code> tag from the previous model.
     * @param display   Holds the different places where item models are displayed. <br/>
     *                  See {@link Display}.
     * @param textures  Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param guiLight  See {@link GuiLight}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                     GuiLight guiLight, List<ItemPredicate> overrides) {
        this(path, parent, display, textures, new ArrayList<>(), guiLight, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param parent    Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Model parent, List<ItemPredicate> overrides) {
        this(path, parent, new HashMap<>(), new HashMap<>(), new ArrayList<>(), overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param parent    Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                  <code>"elements"</code> tag from the previous model.
     * @param display   Holds the different places where item models are displayed. <br/>
     *                  See {@link Display}.
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, List<ItemPredicate> overrides) {
        this(path, parent, display, new HashMap<>(), new ArrayList<>(), overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param textures  Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Map<String, Texture> textures, List<ItemPredicate> overrides) {
        this(path, new HashMap<>(), textures, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param display   Holds the different places where item models are displayed. <br/>
     *                  See {@link Display}.
     * @param textures  Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Map<Position, Display> display, Map<String, Texture> textures, List<ItemPredicate> overrides) {
        this(path, null, display, textures, (GuiLight) null, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param parent    Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                  <code>"elements"</code> tag from the previous model.
     * @param display   Holds the different places where item models are displayed. <br/>
     *                  See {@link Display}.
     * @param textures  Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param elements  Contains all the elements of the model. They can have only cubic forms. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                     List<Element> elements, List<ItemPredicate> overrides) {
        this(path, parent, display, textures, elements, null, overrides);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path      The path in the resourcepack.
     * @param parent    Loads a different model from the given path, in form of namespaced ID. If both <code>
     *                  "parent"</code> and <code>"elements"</code> are set, the <code>"elements"</code> tag
     *                  overrides the <code>"elements"</code> tag from the previous model.
     * @param display   Holds the different places where item models are displayed. <br/>
     *                  See {@link Display}.
     * @param textures  Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                  VariableTexture texture variable}
     * @param elements  Contains all the elements of the model. They can have only cubic forms. If both <code>"parent"
     *                  </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                  <code>"elements"</code> tag from the previous model.
     * @param guiLight  See {@link GuiLight}
     * @param overrides Determines cases in which a different model should be used based on item tags. All cases
     *                  are evaluated in order from top to bottom and last predicate that matches overrides.
     *                  However, overrides are ignored if it has been already overridden once, for example
     *                  this avoids recursion on overriding to the same model.
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                     List<Element> elements, GuiLight guiLight, List<ItemPredicate> overrides) {
        super(path, parent, display, textures, elements);
        this.guiLight = guiLight;
        this.overrides = overrides;
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param parent   Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                 </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                 <code>"elements"</code> tag from the previous model.
     * @param guiLight See {@link GuiLight}
     */
    public ItemModel(String path, Model parent, GuiLight guiLight) {
        this(path, parent, new HashMap<>(), new HashMap<>(), new ArrayList<>(), guiLight);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param parent   Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                 </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                 <code>"elements"</code> tag from the previous model.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param guiLight See {@link GuiLight}
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, GuiLight guiLight) {
        this(path, parent, display, new HashMap<>(), guiLight);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param guiLight See {@link GuiLight}
     */
    public ItemModel(String path, Map<String, Texture> textures, GuiLight guiLight) {
        this(path, new HashMap<>(), textures, guiLight);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param guiLight See {@link GuiLight}
     */
    public ItemModel(String path, Map<Position, Display> display, Map<String, Texture> textures,
                     GuiLight guiLight) {
        this(path, null, display, textures, guiLight);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param parent   Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                 </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                 <code>"elements"</code> tag from the previous model.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param guiLight See {@link GuiLight}
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                     GuiLight guiLight) {
        this(path, parent, display, textures, new ArrayList<>(), guiLight);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path   The path in the resourcepack.
     * @param parent Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *               </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *               <code>"elements"</code> tag from the previous model.
     */
    public ItemModel(String path, Model parent) {
        this(path, parent, new HashMap<>(), new HashMap<>(), new ArrayList<>());
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path    The path in the resourcepack.
     * @param parent  Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                <code>"elements"</code> tag from the previous model.
     * @param display Holds the different places where item models are displayed. <br/>
     *                See {@link Display}.
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display) {
        this(path, parent, display, new HashMap<>());
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     */
    public ItemModel(String path, Map<String, Texture> textures) {
        this(path, new HashMap<>(), textures);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     */
    public ItemModel(String path, Map<Position, Display> display, Map<String, Texture> textures) {
        this(path, null, display, textures);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param parent   Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                 </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                 <code>"elements"</code> tag from the previous model.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures) {
        this(path, parent, display, textures, new ArrayList<>());
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param parent   Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                 </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                 <code>"elements"</code> tag from the previous model.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param elements Contains all the elements of the model. They can have only cubic forms. If both <code>"parent"
     *                 </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                 <code>"elements"</code> tag from the previous model.
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                     List<Element> elements) {
        this(path, parent, display, textures, elements, (GuiLight) null);
    }

    /**
     * Create a new item model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param parent   Loads a different model from the given path, in form of namespaced ID. If both <code>
     *                 "parent"</code> and <code>"elements"</code> are set, the <code>"elements"</code> tag
     *                 overrides the <code>"elements"</code> tag from the previous model.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     * @param elements Contains all the elements of the model. They can have only cubic forms. If both <code>"parent"
     *                 </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                 <code>"elements"</code> tag from the previous model.
     * @param guiLight See {@link GuiLight}
     */
    public ItemModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                     List<Element> elements, GuiLight guiLight) {
        super(path, parent, display, textures, elements);
        this.guiLight = guiLight;
        this.overrides = null;
    }
}

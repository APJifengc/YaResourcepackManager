package io.github.apjifengc.yaresourcepackmanager.component.model;

import io.github.apjifengc.yaresourcepackmanager.component.model.enums.Position;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Display;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Element;
import io.github.apjifengc.yaresourcepackmanager.component.texture.Texture;
import io.github.apjifengc.yaresourcepackmanager.component.texture.VariableTexture;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A block model in the resourcepack. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 */
public class BlockModel extends Model {
    @Getter
    @Setter
    private Boolean ambientOcclusion;

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path             The path in the resourcepack.
     * @param parent           Loads a different model from the given path, in form of namespaced ID. If both <code>
     *                         "parent" </code> and <code>"elements"</code> are set, the <code>"elements"</code>
     *                         tag overrides the <code>"elements"</code> tag from the previous model.
     * @param ambientOcclusion Whether to use <a href="http://en.wikipedia.org/wiki/Ambient_occlusion">ambient
     *                         occlusion</a> (true - default), or not (false).
     */
    public BlockModel(String path, Model parent, Boolean ambientOcclusion) {
        this(path, parent, new HashMap<>(), new HashMap<>(), new ArrayList<>(), ambientOcclusion);
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path             The path in the resourcepack.
     * @param parent           Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                         </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                         <code>"elements"</code> tag from the previous model.
     * @param display          Holds the different places where item models are displayed. <br/>
     *                         See {@link Display}.
     * @param ambientOcclusion Whether to use <a href="http://en.wikipedia.org/wiki/Ambient_occlusion">ambient
     *                         occlusion</a> (true - default), or not (false).
     */
    public BlockModel(String path, Model parent, Map<Position, Display> display, Boolean ambientOcclusion) {
        this(path, parent, display, new HashMap<>(), ambientOcclusion);
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path             The path in the resourcepack.
     * @param textures         Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                         VariableTexture texture variable}
     * @param ambientOcclusion Whether to use <a href="http://en.wikipedia.org/wiki/Ambient_occlusion">ambient
     *                         occlusion</a> (true - default), or not (false).
     */
    public BlockModel(String path, Map<String, Texture> textures, Boolean ambientOcclusion) {
        this(path, new HashMap<>(), textures, ambientOcclusion);
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path             The path in the resourcepack.
     * @param display          Holds the different places where item models are displayed. <br/>
     *                         See {@link Display}.
     * @param textures         Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                         VariableTexture texture variable}
     * @param ambientOcclusion Whether to use <a href="http://en.wikipedia.org/wiki/Ambient_occlusion">ambient
     *                         occlusion</a> (true - default), or not (false).
     */
    public BlockModel(String path, Map<Position, Display> display, Map<String, Texture> textures,
                      Boolean ambientOcclusion) {
        this(path, null, display, textures, ambientOcclusion);
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path             The path in the resourcepack.
     * @param parent           Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                         </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                         <code>"elements"</code> tag from the previous model.
     * @param display          Holds the different places where item models are displayed. <br/>
     *                         See {@link Display}.
     * @param textures         Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                         VariableTexture texture variable}
     * @param ambientOcclusion Whether to use <a href="http://en.wikipedia.org/wiki/Ambient_occlusion">ambient
     *                         occlusion</a> (true - default), or not (false).
     */
    public BlockModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                      Boolean ambientOcclusion) {
        this(path, parent, display, textures, new ArrayList<>(), ambientOcclusion);
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path   The path in the resourcepack.
     * @param parent Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *               </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *               <code>"elements"</code> tag from the previous model.
     */
    public BlockModel(String path, Model parent) {
        this(path, parent, new HashMap<>(), new HashMap<>(), new ArrayList<>());
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path    The path in the resourcepack.
     * @param parent  Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                <code>"elements"</code> tag from the previous model.
     * @param display Holds the different places where item models are displayed. <br/>
     *                See {@link Display}.
     */
    public BlockModel(String path, Model parent, Map<Position, Display> display) {
        this(path, parent, display, new HashMap<>());
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     */
    public BlockModel(String path, Map<String, Texture> textures) {
        this(path, new HashMap<>(), textures);
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     */
    public BlockModel(String path, Map<Position, Display> display, Map<String, Texture> textures) {
        this(path, null, display, textures);
    }

    /**
     * Create a new block model. <br/>
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
    public BlockModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures) {
        this(path, parent, display, textures, new ArrayList<>());
    }

    /**
     * Create a new block model. <br/>
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
    public BlockModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                      List<Element> elements) {
        this(path, parent, display, textures, elements, true);
    }

    /**
     * Create a new block model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path             The path in the resourcepack.
     * @param parent           Loads a different model from the given path, in form of namespaced ID. If both <code>
     *                         "parent"</code> and <code>"elements"</code> are set, the <code>"elements"</code> tag
     *                         overrides the <code>"elements"</code> tag from the previous model.
     * @param display          Holds the different places where item models are displayed. <br/>
     *                         See {@link Display}.
     * @param textures         Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                         VariableTexture texture variable}
     * @param elements         Contains all the elements of the model. They can have only cubic forms. If both <code>
     *                         "parent"
     * @param ambientOcclusion Whether to use <a href="http://en.wikipedia.org/wiki/Ambient_occlusion">ambient
     *                         occlusion</a> (true - default), or not (false).
     */
    public BlockModel(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                      List<Element> elements, Boolean ambientOcclusion) {
        super(path, parent, display, textures, elements);
        this.ambientOcclusion = ambientOcclusion;
    }
}

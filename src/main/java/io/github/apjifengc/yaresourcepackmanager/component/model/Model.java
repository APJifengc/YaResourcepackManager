package io.github.apjifengc.yaresourcepackmanager.component.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import io.github.apjifengc.yaresourcepackmanager.component.SimpleIndependentComponent;
import io.github.apjifengc.yaresourcepackmanager.component.model.enums.CullFace;
import io.github.apjifengc.yaresourcepackmanager.component.model.enums.FaceType;
import io.github.apjifengc.yaresourcepackmanager.component.model.enums.Position;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Display;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Element;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Face;
import io.github.apjifengc.yaresourcepackmanager.component.model.misc.Rotation;
import io.github.apjifengc.yaresourcepackmanager.component.texture.Texture;
import io.github.apjifengc.yaresourcepackmanager.component.texture.VariableTexture;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager.*;
import static io.github.apjifengc.yaresourcepackmanager.util.JsonUtil.*;

/**
 * A model in the resourcepack. <br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 */
public class Model extends SimpleIndependentComponent {
    public static final Model CUBE_ALL = new Model("block/cube_all");
    public static final Model CUBE = new Model("block/cube");
    public static final Model CUBE_BOTTOM_TOP = new Model("block/cube_bottom_top");
    public static final Model CUBE_COLUMN = new Model("block/cube_column");
    public static final Model CUBE_COLUMN_HORIZONTAL = new Model("block/cube_column_horizontal");
    public static final Model CUBE_DIRECTIONAL = new Model("block/cube_directional");
    public static final Model CUBE_MIRRORED = new Model("block/cube_mirrored");
    public static final Model CUBE_MIRRORED_ALL = new Model("block/cube_mirrored_all");
    public static final Model ITEM = new Model("item/handheld");
    @Getter
    private final Map<Position, Display> display;
    @Getter
    private final Map<String, Texture> textures;
    @Getter
    private final List<Element> elements;
    @Getter
    @Setter
    private Model parent;


    /**
     * Create a new model. <br/>
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
    public Model(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures,
                 List<Element> elements) {
        this(null, path, parent, display, textures, elements);
    }

    /**
     * Create a new model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path   The path in the resourcepack.
     * @param parent Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *               </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *               <code>"elements"</code> tag from the previous model.
     */
    public Model(String path, Model parent) {
        this(null, path, parent, new HashMap<>(), new HashMap<>(), new ArrayList<>());
    }

    /**
     * Create a new model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path    The path in the resourcepack.
     * @param parent  Loads a different model from the given path, in form of namespaced ID. If both <code>"parent"
     *                </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag overrides the
     *                <code>"elements"</code> tag from the previous model.
     * @param display Holds the different places where item models are displayed. <br/>
     *                See {@link Display}.
     */
    public Model(String path, Model parent, Map<Position, Display> display) {
        this(path, parent, display, new HashMap<>());
    }

    /**
     * Create a new model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     */
    public Model(String path, Map<String, Texture> textures) {
        this(path, new HashMap<>(), textures);
    }

    /**
     * Create a new model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param path     The path in the resourcepack.
     * @param display  Holds the different places where item models are displayed. <br/>
     *                 See {@link Display}.
     * @param textures Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                 VariableTexture texture variable}
     */
    public Model(String path, Map<Position, Display> display, Map<String, Texture> textures) {
        this(path, null, display, textures);
    }

    /**
     * Create a new model. <br/>
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
    public Model(String path, Model parent, Map<Position, Display> display, Map<String, Texture> textures) {
        this(null, path, parent, display, textures, new ArrayList<>());
    }

    /**
     * Create a new model. <br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
     *
     * @param fileInputStream The model file's input stream.
     * @param path            The path in the resourcepack.
     * @param parent          Loads a different model from the given path, in form of namespaced ID. If both <code>
     *                        "parent"</code> and <code>"elements"</code> are set, the <code>"elements"</code> tag
     *                        overrides the <code>"elements"</code> tag from the previous model.
     * @param display         Holds the different places where item models are displayed. <br/>
     *                        See {@link Display}.
     * @param textures        Holds the textures of the model, in form of {@link Texture} or can be another {@link
     *                        VariableTexture texture variable}
     * @param elements        Contains all the elements of the model. They can have only cubic forms. If both <code>
     *                        "parent" </code> and <code>"elements"</code> are set, the <code>"elements"</code> tag
     *                        overrides the <code>"elements"</code> tag from the previous model.
     */
    private Model(InputStream fileInputStream, String path, Model parent, Map<Position, Display> display,
                  Map<String, Texture> textures, List<Element> elements) {
        super(path, fileInputStream);
        this.parent = parent;
        this.display = display;
        this.textures = textures;
        this.elements = elements;
    }

    /**
     * Create a new model.
     *
     * @param path The path in the resourcepack.
     */
    public Model(String path) {
        this(null, path, null, new HashMap<>(), new HashMap<>(),
                new ArrayList<>());
    }

    /**
     * Create a new model.
     *
     * @param file The model file.
     * @param path The path in the resourcepack.
     */
    public Model(File file, String path) throws IOException {
        this(new FileInputStream(file), path, null, new HashMap<>(), new HashMap<>(), new ArrayList<>());
        initFromJson((JsonObject) parser.parse(new FileReader(file)));
    }

    private void initFromJson(JsonObject jsonObject) {
        if (jsonObject.has("parent")) parent = new Model(jsonObject.get("parent").getAsString());
        if (jsonObject.has("display")) {
            for (Map.Entry<String, JsonElement> entry : jsonObject.getAsJsonObject("display").entrySet()) {
                Position position = Position.fromMinecraftName(entry.getKey());
                JsonObject key = entry.getValue().getAsJsonObject();
                Display display = new Display(
                        asVector(key.get("rotation")),
                        asVector(key.get("translation")),
                        asVector(key.get("scale"))
                );
                this.display.put(position, display);
            }
        }
        if (jsonObject.has("textures")) {
            for (Map.Entry<String, JsonElement> entry : jsonObject.getAsJsonObject("textures").entrySet()) {
                this.textures.put(
                        entry.getKey(),
                        Texture.fromString(entry.getValue().getAsString())
                );
            }
        }
        if (jsonObject.has("elements")) {
            JsonArray elements = jsonObject.getAsJsonArray("elements");
            for (JsonElement object : elements) {
                JsonObject element = object.getAsJsonObject();
                Vector from = asVector(element.get("from"));
                Vector to = asVector(element.get("from"));
                Rotation rotation = null;
                if (element.has("rotation")) {
                    JsonObject rotationJson = element.getAsJsonObject("rotation");
                    Vector origin = asVector(rotationJson.get("origin"));
                    Axis axis = Axis.valueOf(rotationJson.get("axis").toString().toUpperCase());
                    Float angle = rotationJson.get("angle").getAsFloat();
                    if (rotationJson.has("rescale")) {
                        rotation = new Rotation(origin, axis, angle, rotationJson.get("rescale").getAsBoolean());
                    } else {
                        rotation = new Rotation(origin, axis, angle);
                    }
                }
                Boolean shade = null;
                if (element.has("shade")) shade = element.get("shade").getAsBoolean();
                Map<FaceType, Face> facesMap = new HashMap<>();
                JsonObject faces = element.getAsJsonObject("faces");
                for (Map.Entry<String, JsonElement> entry : faces.entrySet()) {
                    JsonObject faceElement = entry.getValue().getAsJsonObject();
                    FaceType faceType = FaceType.fromMinecraftName(entry.getKey());
                    Integer x1 = null, y1 = null, x2 = null, y2 = null;
                    if (faceElement.has("uv")) {
                        JsonArray uv = faceElement.getAsJsonArray("uv");
                        x1 = uv.get(0).getAsInt();
                        y1 = uv.get(1).getAsInt();
                        x2 = uv.get(2).getAsInt();
                        y2 = uv.get(3).getAsInt();
                    }
                    Texture texture = Texture.fromString(faceElement.get("texture").getAsString());
                    CullFace cullFace = null;
                    if (faceElement.has("cullface"))
                        cullFace = CullFace.fromMinecraftName(faceElement.get("cullface").getAsString());
                    Integer textureRotation = null;
                    if (faceElement.has("rotation"))
                        textureRotation = faceElement.get("rotation").getAsInt();
                    Integer tintIndex = null;
                    if (faceElement.has("tintindex"))
                        tintIndex = faceElement.get("tintindex").getAsInt();
                    Face face = new Face(x1, y1, x2, y2, texture, cullFace, textureRotation, tintIndex);
                    facesMap.put(faceType, face);
                }
                this.elements.add(new Element(from, to, rotation, shade, facesMap));
            }
        }
    }

    private JsonObject toJson() {
        JsonObject root = new JsonObject();
        if (parent != null) root.add("parent", new JsonPrimitive(parent.getPath()));
        if (parent != null) root.add("parent", new JsonPrimitive(parent.getPath()));
        return root;
    }

    @Override
    public void handleResource(File resourcePack) throws IOException {
        if (super.getInputStream() == null) {
            super.setInputStream(new ByteArrayInputStream(toJson().toString().getBytes(StandardCharsets.UTF_8)));
        }
        super.handleResource(resourcePack);
    }

    @Override
    public boolean isRegistrable() {
        return true;
    }

    @Override
    public String getExtension() {
        return ".json";
    }

    @Override
    public String getBasePath() {
        return "assets/minecraft/models/";
    }

    @Override
    public String toString() {
        return "Model{" +
                "path='" + getPath() + '\'' +
                '}';
    }
}

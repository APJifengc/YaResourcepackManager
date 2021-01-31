package io.github.apjifengc.yaresourcepackmanager.component;

import com.google.gson.*;
import io.github.apjifengc.yaresourcepackmanager.component.interfaces.ICollectionComponent;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A custom font character in the resourcepack.
 *
 * @author APJifengc
 */
public class FontCharacter extends SimpleIndependentComponent implements ICollectionComponent {
    public final String fontName;
    public final String file;
    public final int ascent;
    public final int height;
    public final String[] chars;

    /**
     * Create a new font character.
     *
     * @param input    The character texture file.
     * @param fontName The font name.
     * @param file     The texture file name in the resource pack.
     * @param ascent   The ascent of the character. <br/>
     *                 This means how many pixels the character will be moved upward.
     * @param height   The height of the character. (Pixels)
     * @param chars    The characters in the texture file. <br/>
     *                 You can add more than one character in one texture file.<br/>
     *                 eg. If your texture file has 5x3 characters, the chars should be:<br/>
     *                 {@code "\\uXXXX\\uXXXX\\uXXXX\\uXXXX\\uXXXX",} <br/>
     *                 {@code "\\uXXXX\\uXXXX\\uXXXX\\uXXXX\\uXXXX",} <br/>
     *                 {@code "\\uXXXX\\uXXXX\\uXXXX\\uXXXX\\uXXXX"} <br/>
     */
    public FontCharacter(InputStream input, String fontName, String file, int ascent, int height, String[] chars) {
        super(file, input);
        this.fontName = fontName;
        this.file = file;
        this.ascent = ascent;
        this.height = height;
        this.chars = chars;
    }

    /**
     * Create a new font character.
     *
     * @param input    The character texture file.
     * @param fontName The font name.
     * @param file     The texture file name in the resource pack.
     * @param ascent   The ascent of the character. <br/>
     *                 This means how many pixels the character will be moved upward.
     * @param height   The height of the character. (Pixels)
     * @param chars    The character in the texture file. <br/>
     *                 If you want to create more than one characters in one file, you can use {@link #FontCharacter(InputStream, String, String, int, int, String[])}
     */
    public FontCharacter(InputStream input, String fontName, String file, int ascent, int height, String chars) {
        super(file, input);
        this.fontName = fontName;
        this.file = file;
        this.ascent = ascent;
        this.height = height;
        this.chars = new String[]{chars};
    }

    /**
     * Get the JSON snippet for the font file.
     *
     * @return The JSON element.
     */
    public JsonElement getJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("ascent", new JsonPrimitive(ascent));
        jsonObject.add("height", new JsonPrimitive(height));
        jsonObject.add("file", new JsonPrimitive(file + ".png"));
        JsonArray chars = new JsonArray();
        for (String c : this.chars) {
            chars.add(c);
        }
        jsonObject.add("chars", chars);
        return jsonObject;
    }

    @Override
    public void handleResource(File resourcePack, List<ICollectionComponent> list) throws IOException {
        Map<String, JsonArray> fonts = new HashMap<>();
        // Put all characters into JSON array
        for (ICollectionComponent component : list) {
            FontCharacter fontCharacter = (FontCharacter) component;
            if (!fonts.containsKey(fontCharacter.fontName)) fonts.put(fontCharacter.fontName, new JsonArray());
            fonts.get(fontCharacter.fontName).add(fontCharacter.getJson());
        }
        // Output file
        for (Map.Entry<String, JsonArray> entry : fonts.entrySet()) {
            JsonObject root = new JsonObject();
            root.add("providers", entry.getValue());
            FileUtils.writeFile(new ByteArrayInputStream(root.toString().getBytes(StandardCharsets.UTF_8)),
                    new File(resourcePack + File.separator + "assets/minecraft/font/" + entry.getKey() + ".json"));
        }
    }

    @Override
    public String getExtension() {
        return ".png";
    }

    @Override
    public String getBasePath() {
        return "assets/minecraft/textures/";
    }

    @Override
    public String toString() {
        return "FontCharacter{" +
                "fontName='" + fontName + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}

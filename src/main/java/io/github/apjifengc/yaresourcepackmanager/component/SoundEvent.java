package io.github.apjifengc.yaresourcepackmanager.component;

import com.google.gson.*;
import io.github.apjifengc.yaresourcepackmanager.YaResourcepackManager;
import io.github.apjifengc.yaresourcepackmanager.component.interfaces.*;
import io.github.apjifengc.yaresourcepackmanager.util.FileUtils;
import org.bukkit.SoundCategory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A custom sound event in the resourcepack.
 *
 * @author APJifengc
 */
public class SoundEvent extends SimpleIndependentComponent implements ICollectionComponent {
    public final String id;
    public final Boolean replace;
    public final String subtitle;
    public final List<Sound> sounds;
    public final SoundCategory category;
    public final static Map<SoundCategory, String> map = new HashMap<>();

    static {
        map.put(SoundCategory.AMBIENT, "ambient");
        map.put(SoundCategory.MASTER, "master");
        map.put(SoundCategory.MUSIC, "music");
        map.put(SoundCategory.RECORDS, "record");
        map.put(SoundCategory.WEATHER, "weather");
        map.put(SoundCategory.BLOCKS, "block");
        map.put(SoundCategory.HOSTILE, "hostile");
        map.put(SoundCategory.NEUTRAL, "neutral");
        map.put(SoundCategory.PLAYERS, "player");
        map.put(SoundCategory.VOICE, "voice");
    }

    /**
     * Create a new sound event.<br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Sounds.json">MC Wiki</a>. <br/>
     *
     * @param path        The path in the resourcepack.
     * @param inputStream The sound file's input stream.
     * @param subtitle    Translated as the subtitle of the sound if Show Subtitles is enabled ingame. Optional.
     * @param sounds      The sound files this sound event uses. One of the listed sounds is randomly selected to
     * @param id          The sound event's ID.
     * @param category    The sound's category.
     */
    public SoundEvent(String id, String path, InputStream inputStream, String subtitle, List<Sound> sounds,
                      SoundCategory category) {
        this(id, path, inputStream, null, subtitle, sounds, category);
    }

    /**
     * Create a new sound event.<br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Sounds.json">MC Wiki</a>. <br/>
     *
     * @param path        The path in the resourcepack.
     * @param inputStream The sound file's input stream.
     * @param replace     True if the sounds listed in sounds should replace the sounds listed in the default
     *                    sounds.json for this sound event. False if the sounds listed should be added to the list
     *                    of default sounds. Optional. If undefined, defaults to "false".
     * @param sounds      The sound files this sound event uses. One of the listed sounds is randomly selected to
     * @param id          The sound event's ID.
     * @param category    The sound's category.
     */
    public SoundEvent(String id, String path, InputStream inputStream, Boolean replace, List<Sound> sounds,
                      SoundCategory category) {
        this(id, path, inputStream, replace, null, sounds, category);
    }

    /**
     * Create a new sound event.<br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Sounds.json">MC Wiki</a>. <br/>
     *
     * @param path        The path in the resourcepack.
     * @param inputStream The sound file's input stream.
     * @param sounds      The sound files this sound event uses. One of the listed sounds is randomly selected to
     * @param id          The sound event's ID.
     * @param category    The sound's category.
     */
    public SoundEvent(String id, String path, InputStream inputStream, List<Sound> sounds, SoundCategory category) {
        this(id, path, inputStream, null, null, sounds, category);
    }

    /**
     * Create a new sound event.<br/>
     * Note that the docs is from <a href="https://minecraft.gamepedia.com/Sounds.json">MC Wiki</a>. <br/>
     *
     * @param id          The sound event's ID.
     * @param inputStream The sound file's input stream.
     * @param path        The path in the resourcepack.
     * @param replace     True if the sounds listed in sounds should replace the sounds listed in the default
     *                    `sounds.json for this sound event. False if the sounds listed should be added to the list
     *                    of default sounds. Optional. If undefined, defaults to "false".
     * @param subtitle    Translated as the subtitle of the sound if Show Subtitles is enabled ingame. Optional.
     * @param sounds      The sound files this sound event uses. One of the listed sounds is randomly selected to
     * @param category    The sound's category.
     */
    public SoundEvent(String id, String path, InputStream inputStream, Boolean replace, String subtitle,
                      List<Sound> sounds, SoundCategory category) {
        super(path, inputStream);
        this.id = id;
        this.replace = replace;
        this.subtitle = subtitle;
        this.sounds = sounds;
        this.category = category;
    }


    @Override
    public String getExtension() {
        return ".ogg";
    }

    @Override
    public String getBasePath() {
        return "assets/minecraft/sounds/";
    }

    @Override
    public void handleResource(File resourcePack, List<ICollectionComponent> list) throws IOException {
        JsonObject root = new JsonObject();
        for (ICollectionComponent component : list) {
            SoundEvent soundEvent = (SoundEvent) component;
            JsonObject object = new JsonObject();
            object.add("category", new JsonPrimitive(map.get(soundEvent.category)));
            if (soundEvent.replace != null) object.add("replace", new JsonPrimitive(soundEvent.replace));
            if (soundEvent.subtitle != null) object.add("subtitle", new JsonPrimitive(soundEvent.subtitle));
            JsonArray sounds = new JsonArray();
            soundEvent.sounds.forEach((s) -> sounds.add(s.getJson()));
            object.add("sounds", sounds);
            root.add(soundEvent.id, object);
        }
        FileUtils.writeFile(new ByteArrayInputStream(root.toString().getBytes(StandardCharsets.UTF_8)),
                new File(resourcePack + File.separator + "assets/minecraft/sounds.json"));
    }

    /**
     * A sound object for the sound events.
     *
     * @see SoundEvent#sounds
     */
    public static class Sound {
        public final String name;
        public final Double volume;
        public final Double pitch;
        public final Double weight;
        public final Boolean stream;
        public final Double attenuationDistance;
        public final Boolean preload;
        public final SoundType type;

        /**
         * Create a new sound object.<br/>
         * Note that the docs is from <a href="https://minecraft.gamepedia.com/Sounds.json">MC Wiki</a>. <br/>
         *
         * @param name   The path to this sound file from the "minecraft/sounds" folder (excluding the .ogg file
         *               extension). Uses forward slashes instead of backslashes. May instead be the name of
         *               another sound event (according to value of "type").
         * @param volume The volume for playing this sound. Value is a decimal between 0.0 and 1.0. If
         *               undefined, defaults to 1.0.
         * @param pitch  Plays the pitch at the specified value. If undefined, defaults to 1.0, but higher
         *               and lower values can be chosen.
         * @param type   "sound" causes the value of "name" to be interpreted as the name of a file, while "event"
         *               causes the value of "name" to be interpreted as the name of an already defined event.
         *               If undefined, defaults to "sound".
         * @see SoundEvent#sounds
         */
        public Sound(String name, Double volume, Double pitch, SoundType type) {
            this(name, volume, pitch, null, null, null, null, type);
        }

        /**
         * Create a new sound object.<br/>
         * Note that the docs is from <a href="https://minecraft.gamepedia.com/Sounds.json">MC Wiki</a>. <br/>
         *
         * @param name                The path to this sound file from the "minecraft/sounds" folder (excluding the .ogg file
         *                            extension). Uses forward slashes instead of backslashes. May instead be the name of
         *                            another sound event (according to value of "type").
         * @param volume              The volume for playing this sound. Value is a decimal between 0.0 and 1.0. If
         *                            undefined, defaults to 1.0.
         * @param pitch               Plays the pitch at the specified value. If undefined, defaults to 1.0, but higher
         *                            and lower values can be chosen.
         * @param weight              The chance that this sound is selected to play when this sound event is triggered.
         *                            Defaults to 1. An example: putting 2 in for the value would be like placing in the
         *                            name twice. Only accepts integers.
         * @param stream              true/false. True if this sound should be streamed from its file. It is recommended
         *                            that this is set to "true" for sounds that have a duration longer than a few seconds
         *                            to avoid lag. Used for all sounds in the "music" and "record" categories (except Note
         *                            Block sounds), as (almost) all the sounds that belong to those categories are over a
         *                            minute long. Optional. If undefined, defaults to "false". Setting this to false allows
         *                            many more instances of the sound to be ran at the same time while setting it to true
         *                            only allows 4 instances (of that type) to be ran at the same time.
         * @param attenuationDistance Modify sound reduction rate based on distance. Used by portals, beacons,
         *                            and conduits. Defaults to 16.
         * @param preload             True if this sound should be loaded when loading the pack instead of when the sound
         *                            is played. Used by the underwater ambience. Defaults to "false".
         * @param type                "sound" causes the value of "name" to be interpreted as the name of a file, while "event"
         *                            causes the value of "name" to be interpreted as the name of an already defined event.
         *                            If undefined, defaults to "sound".
         * @see SoundEvent#sounds
         */
        public Sound(String name, Double volume, Double pitch, Double weight, Boolean stream,
                     Double attenuationDistance, Boolean preload, SoundType type) {
            this.name = name;
            this.volume = volume;
            this.pitch = pitch;
            this.weight = weight;
            this.stream = stream;
            this.attenuationDistance = attenuationDistance;
            this.preload = preload;
            this.type = type;
        }

        /**
         * Get the json object for the sound.
         *
         * @return The Json object.
         */
        public JsonElement getJson() {
            if (type == null) return new JsonPrimitive(name);
            JsonObject object = (JsonObject) YaResourcepackManager.gson.toJsonTree(this);
            if (attenuationDistance != null) {
                object.remove("attenuationDistance");
                object.add("attenuation_distance", new JsonPrimitive(attenuationDistance));
            }
            if (type != null) {
                object.remove("type");
                object.add("type", new JsonPrimitive(type.toString().toLowerCase()));
            }
            return object;
        }

        /**
         * <p>Create a new sound object.</p>
         * <p>Note that the docs is from <a href="https://minecraft.gamepedia.com/Sounds.json">MC Wiki</a>. </p>
         *
         * @param name The path to a sound file from the "minecraft/sounds" folder. Uses forward slashes.
         * @see SoundEvent#sounds
         */
        public Sound(String name) {
            this(name, null, null, null, null, null, null, null);
        }

        /**
         * The sound type.
         */
        public static enum SoundType {
            SOUND, EVENT;
        }
    }

    @Override
    public String toString() {
        return "SoundEvent{" +
                "id='" + id + '\'' +
                '}';
    }
}

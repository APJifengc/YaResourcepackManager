package io.github.apjifengc.yaresourcepackmanager.component.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * A single case tag. Some items support additional predicates for model overrides.<br/>
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 */
public enum ItemPredicateCase {
    /**
     * Used on compasses to determine the current angle, expressed in a decimal value of less than one.
     */
    ANGLE("angle"),
    /**
     * Used on shields to determine if currently blocking. If <code>1</code>, the player is blocking.
     */
    BLOCKING("blocking"),
    /**
     * Used on Elytra to determine if broken. If <code>1</code>, the Elytra is broken.
     */
    BROKEN("broken"),
    /**
     * Used on fishing rods to determine if the fishing rod has been cast. If <code>1</code>, the fishing rod has
     * been cast.
     */
    CAST("cast"),
    /**
     * Used on ender pearls and chorus fruit to determine the remaining cooldown, expressed in a decimal value 
     * between 0 and <code>1</code>.
     */
    COOLDOWN("cooldown"),
    /**
     * Used on items with durability to determine the amount of damage, expressed in a decimal value between 0 
     * and <code>1</code>.
     */
    DAMAGE("damage"),
    /**
     * Used on items with durability to determine if it is damaged. If <code>1</code>, the item is damaged. Note
     * that if an item has the unbreakable tag, this may be 0 while the item has a non-zero "damage" tag.
     */
    DAMAGED("damaged"),
    /**
     * Determines the model used by left handed players. It affects the item they see in inventories, along with
     * the item players see them holding or wearing.
     */
    LEFT_HANDED("lefthanded"),
    /**
     * Determines the amount a bow or crossbow has been pulled, expressed in a decimal value of less than one.
     */
    PULL("pull"),
    /**
     * Used on bows and crossbows to determine if the bow is being pulled. If <code>1</code>, the bow is currently
     * being pulled.
     */
    PULLING("pulling"),
    /**
     * Used on crossbows to determine if they are charged with any projectile. If <code>1</code>, the crossbow is charged.
     */
    CHARGED("charged"),
    /**
     * Used on crossbows. If <code>1</code>, the crossbow is charged with a firework rocket.
     */
    FIREWORK("firework"),
    /**
     * Used on the trident to determine if the trident is ready to be thrown by the player. If <code>1</code>, the
     * trident is ready for fire.
     */
    THROWING("throwing"),
    /**
     * Used on clocks to determine the current time, expressed in a decimal value of less than one.
     */
    TIME("time"),
    /**
     * Used on any item and is compared to the <code>tag.CustomModelData</code> NBT, expressed in an integer value.
     * The number is still internally converted to float, causing a <a href="https://en.wikipedia.org/wiki/Single
     * -precision_floating-point_format#Precision_limitations_on_integer_values">precision loss for some numbers
     * above 16 million</a>. If the value read from the item data is greater than or equal to the value used for
     * the predicate, the predicate is positive.
     */
    CUSTOM_MODEL_DATA("custom_model_data");
    @Getter
    private final String minecraftName;

    ItemPredicateCase(String minecraftName) {
        this.minecraftName = minecraftName;
    }

    private static final Map<String, ItemPredicateCase> map = Arrays.stream(values()).
            collect(toMap(ItemPredicateCase::getMinecraftName, e -> e));
    public static ItemPredicateCase fromMinecraftName(String minecraftName) {
        return map.get(minecraftName);
    }
}

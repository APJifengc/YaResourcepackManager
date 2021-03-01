package io.github.apjifengc.yaresourcepackmanager.component.model.misc;

import io.github.apjifengc.yaresourcepackmanager.component.model.ItemModel;
import io.github.apjifengc.yaresourcepackmanager.component.model.Model;
import io.github.apjifengc.yaresourcepackmanager.component.model.enums.ItemPredicateCase;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Note that the docs is from <a href="https://minecraft.gamepedia.com/Model">MC Wiki</a>. <br/>
 *
 * @author APJifengc
 * @see ItemModel#overrides
 */
@Data
public class ItemPredicate {
    private final Map<ItemPredicateCase, Float> predicate;
    private Model model;

    /**
     * @param model The path to the model to use if the case is met, in form of namespaced ID.
     */
    public ItemPredicate(Model model) {
        this(new HashMap<>(), model);
    }

    /**
     * @param predicate Holds the cases.
     * @param model     The path to the model to use if the case is met, in form of namespaced ID.
     */
    public ItemPredicate(Map<ItemPredicateCase, Float> predicate, Model model) {
        this.predicate = predicate;
        this.model = model;
    }
}

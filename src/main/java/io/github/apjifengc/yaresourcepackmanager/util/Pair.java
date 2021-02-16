package io.github.apjifengc.yaresourcepackmanager.util;

import java.io.Serializable;

/**
 * A convenience class to represent name-value pairs.
 */
public class Pair<K, V> implements Serializable {

    private static final long serialVersionUID = 4604027162840289949L;
    
    /**
     * Key of this <code>Pair</code>.
     */
    private K key;

    /**
     * Gets the key for this pair.
     *
     * @return key for this pair
     */
    public K getKey() {
        return key;
    }

    /**
     * Value of this this <code>Pair</code>.
     */
    private V value;

    /**
     * Gets the value for this pair.
     *
     * @return value for this pair
     */
    public V getValue() {
        return value;
    }

    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}


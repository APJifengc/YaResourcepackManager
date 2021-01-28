package io.github.apjifengc.yaresourcepackmanager.util;

import java.io.Serializable;

public class Pair<K,V> implements Serializable{
    private final K key;

    public K getKey() { return key; }

    private final V value;

    public V getValue() { return value; }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
 }


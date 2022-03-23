package com.jumbo.store.finder.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Pair Model (Key, Value)
 * The comparator is only by key.
 *
 * @param <K> Key
 * @param <V> Value
 */
@Data
@AllArgsConstructor
public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {

    private K key;
    private V value;

    @Override
    public int compareTo(Pair<K, V> o) {
        return getKey().compareTo(o.getKey());
    }
}
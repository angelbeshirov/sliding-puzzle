package com.fmi.ai;

import java.util.Objects;

/**
 * Immutable generic pair, primarily used for the coordinates of the 0 in the board.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 * @author angel.beshirov
 */
public class Pair<K, V> {
    private final K key;
    private final V value;

    /**
     * Parametrized constructor which sets key and value of this pair.
     *
     * @param key   the key
     * @param value the value
     */
    public Pair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key.
     *
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key) &&
                Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

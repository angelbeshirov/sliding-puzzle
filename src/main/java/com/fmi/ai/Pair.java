package com.fmi.ai;

import java.util.Objects;

public class Pair<K, V> {
    private final K x;
    private final V y;

    public Pair(final K x, final V y) {
        this.x = x;
        this.y = y;
    }

    public K getX() {
        return x;
    }

    public V getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(x, pair.x) &&
                Objects.equals(y, pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

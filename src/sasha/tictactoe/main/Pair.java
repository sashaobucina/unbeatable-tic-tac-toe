package sasha.tictactoe.main;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public class Pair<K, V> {

    public final K key;
    public final V value;

    public Pair(@NotNull K key, @NotNull V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{ " + key + ", " + value + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pair)) {
            return false;
        }

        Pair<K,V> other_ = (Pair<K,V>) other;
        return (Objects.equals(other_.key, this.key) && Objects.equals(other_.value, this.value));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }
}

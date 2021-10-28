package com.company;

import java.util.Objects;

public class MyCustomEntry<K, V> {

    K key;
    V value;
    MyCustomEntry<K, V> next;

    public MyCustomEntry(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCustomEntry<?, ?> that = (MyCustomEntry<?, ?>) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(next, that.next);
    }


    public int getHashIndex() {
        int hashIndex = (int) getKey() % 16;
        return hashIndex;
    }
}

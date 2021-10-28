package com.company;

public interface MyCustomMap<K, V> {
    int size();

    boolean isEmpty();

    void put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    boolean remove(K key, V value);

    V remove(K key);


}

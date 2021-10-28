package com.company;

import java.util.Arrays;

public class MyCustomHashMap<K, V> implements MyCustomMap<K, V> {

    private int size;
    static final int DEFAULT_SIZE = 16;

    MyCustomEntry[] entryArray;

    public MyCustomHashMap() {
        entryArray = new MyCustomEntry[DEFAULT_SIZE];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void put(Object key, Object value) {
        MyCustomEntry myCustomEntry = new MyCustomEntry(key, value);
        for (int i = 0; i < entryArray.length; i++) {
            if (i == myCustomEntry.getHashIndex()) {
                if (entryArray[i] == null) {
                    entryArray[i] = myCustomEntry;
                    size++;
                } else {
                    MyCustomEntry current = entryArray[i];
                    boolean exist = true;
                    while (current.next != null) {
                        if (current.key == key) {
                            current.value = value;
                            exist = false;
                            break;
                        }
                        current = current.next;
                    }
                    if (current.key == key) {
                        current.value = value;
                        exist = false;
                    }
                    if (exist) {
                        current.next = myCustomEntry;
                        size++;
                    }
                }
                break;
            }
        }
    }

    @Override
    public Object get(Object key) {
        V currentValue = null;
        for (int i = 0; i < entryArray.length; i++) {
            if (i == key.hashCode() % 16) {
                if (entryArray[i] != null) {
                    MyCustomEntry current = entryArray[i];
                    while (current != null) {
                        if (current.key == key) {
                            currentValue = (V) current.value;
                            break;
                        }
                        current = current.next;
                    }
                }
            }
        }
        return currentValue;
    }


    @Override
    public boolean containsKey(Object key) {
        boolean contains = false;
        for (int i = 0; i < entryArray.length; i++) {
            if (i == key.hashCode() % 16) {
                if (entryArray[i] != null) {
                    MyCustomEntry current = entryArray[i];
                    while (current.next != null) {
                        if (current.key == key) {
                            contains = true;
                            break;
                        }
                        current = current.next;
                    }
                    if (current.key == key) {
                        contains = true;
                    }
                }
            }
        }
        return contains;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < entryArray.length; i++) {
            if (entryArray[i] != null) {
                MyCustomEntry current = entryArray[i];
                while (current != null) {
                    if (current.value == value) {
                        return true;
                    }
                    current = current.next;
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public Object remove(Object key) {
        for (int i = 0; i < entryArray.length; i++) {
            if (i == key.hashCode() % 16) {
                MyCustomEntry current = entryArray[i];
                while (current != null) {
                    if (current.key == key) {
                        current.key = current.next.key;
                        current.value = current.next.value;
                    }
                    current = current.next;
                    if (current.next == null) {
                        if (current.key == key) {
                            current = null;
                        }
                    }
                }
                size--;
                break;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCustomHashMap<?, ?> that = (MyCustomHashMap<?, ?>) o;
        return size == that.size && Arrays.equals(entryArray, that.entryArray);
    }

    @Override
    public String toString() {
        return "MyCustomHashMap{" +
                "entryArray=" + Arrays.toString(entryArray) +
                '}';
    }
}

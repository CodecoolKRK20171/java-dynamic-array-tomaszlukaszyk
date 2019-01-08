package com.codecool.dynamicArrayDojo;

import java.util.LinkedList;

public class HashTable<K, V> {

    private final  int INITIAL_LENGTH = 16;

    private int size;
    private LinkedList<KeyValue<K,V>>[] data;

    public HashTable() {
        initEmptyList(INITIAL_LENGTH);
    }

    private void initEmptyList(int length) {
        this.size = 0;
        this.data = new LinkedList[length];

        for (int i=0; i<this.data.length; i++) {
            this.data[i] = new LinkedList<>();
        }
    }

    public int size() {
        return this.size;
    }

    public void add(K key, V value) {
        int position = getHash(key);
        LinkedList<KeyValue<K,V>> currentList = this.data[position];

        for (KeyValue<K,V> kv: currentList) {
            if (kv.getKey().equals(key)) throw new IllegalArgumentException("Key already exists");
        }

        currentList.add(new KeyValue<>(key, value));
        this.size++;
        resizeIfNeeded();
    }

    public V get(K key) {
        int position = getHash(key);
        LinkedList<KeyValue<K,V>> currentList = this.data[position];

        if (currentList != null) {
            for (KeyValue<K, V> kv: currentList) {
                if (kv.getKey().equals(key)) return kv.getValue();
            }
        }

        throw new IllegalArgumentException("Key does not exist");
    }

    public void remove(K key) {
        int position = getHash(key);
        LinkedList<KeyValue<K,V>> currentList = this.data[position];

        if (currentList != null) {
            for (KeyValue<K, V> kv: currentList) {
                if (kv.getKey().equals(key)) {
                    currentList.remove(kv);
                    this.size--;
                    return;
                }
            }
        }

        throw new IllegalArgumentException("Key does not exist");
    }

    public void clearAll() {
        initEmptyList(INITIAL_LENGTH);
    }

    private void resizeIfNeeded() {
        if (!isTooBig() && !isTooSmall()) return;

        LinkedList<KeyValue<K,V>>[] old = this.data;
        if (isTooSmall()) {
            initEmptyList(this.data.length * 2);
        } else {
            initEmptyList(this.data.length / 2);
        }
        addAll(old);
    }

    private boolean isTooSmall() {
        return this.size > this.data.length * 2;
    }

    private boolean isTooBig() {
        return this.size < this.data.length / 2;
    }

    private void addAll(LinkedList<KeyValue<K,V>>[] old) {
        for (LinkedList<KeyValue<K,V>> list: old) {
            if (list.isEmpty()) continue;

            for (KeyValue<K, V> element: list) {
                add(element.getKey(), element.getValue());
            }
        }
    }

    private int getHash(K key) {
        return key.hashCode() % this.data.length;
    }
}

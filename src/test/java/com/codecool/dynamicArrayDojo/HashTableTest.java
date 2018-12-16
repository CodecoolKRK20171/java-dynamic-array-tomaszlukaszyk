package com.codecool.dynamicArrayDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<String, Integer> hashTable;

    @BeforeEach
    void init() {
        hashTable = new HashTable<>();
    }

    @Test
    void testAddAndGet() {
        hashTable.add("one", 1);

        assertEquals(1, hashTable.size());
        assertEquals(new Integer(1), hashTable.get("one"));
        assertEquals(1, hashTable.size());
    }

    @Test
    void testAddThrowsExceptionOnDuplicateKey() {
        hashTable.add("one", 1);

        assertThrows(IllegalArgumentException.class, () ->hashTable.add("one", 2));
    }

    @Test
    void testGetThrowsExceptionForWrongKey() {
        hashTable.add("one", 1);
        hashTable.add("two", 2);

        assertThrows(IllegalArgumentException.class, () ->hashTable.get("three"));
    }

    @Test
    void testGetThrowsExceptionOnEmpty() {
        assertThrows(IllegalArgumentException.class, () -> hashTable.get("one"));
    }

    @Test
    void testRemove() {
        hashTable.add("one", 1);
        hashTable.add("two", 2);
        hashTable.remove("two");

        assertEquals(1, hashTable.size());
        assertEquals(new Integer(1), hashTable.get("one"));
        assertThrows(IllegalArgumentException.class, () -> hashTable.get("two"));
    }
}
package com.codecool.dynamicArrayDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;

    @BeforeEach
    void init() {
        Integer[] data = {1, 2, 3, 4};
        tree = new BinarySearchTree<>(data);
    }

    @Test
    void testContainsReturnsTrue() {
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
    }

    @Test
    void testContainsReturnsFalse() {
        assertFalse(tree.contains(0));
    }

    @Test
    void testAdd() {
        tree.add(5);
        tree.add(0);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(0));
    }

    @Test
    void testAddThrowsExceptionsForWrongArgument() {
        assertThrows(IllegalArgumentException.class, () -> tree.add(1));
    }

    @Test
    void testRemove() {
        tree.remove(3);

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertFalse(tree.contains(3));
        assertTrue(tree.contains(4));
    }

    @Test
    void testRemoveThrowsExceptionForWrongArgument() {
        assertThrows(IllegalArgumentException.class, () -> tree.remove(5));
    }

    @Test
    void testAddThrowsExceptionForDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> tree.add(1));
    }

}
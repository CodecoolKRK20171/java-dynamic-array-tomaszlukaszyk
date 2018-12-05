package com.codecool.dynamicArrayDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    void init() {
        list = new SinglyLinkedList<>();
    }

    @Test
    void testGetThrowsExceptionForIncorrectIndexes() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    }

    @Test
    void testAddOneElement() {
        Integer element = 1;
        list.add(element);

        assertEquals(element, list.get(0));
        assertEquals(element, list.head());
        assertEquals(element, list.last());
        assertEquals(1, list.length());
    }

    @Test
    void testAddMultipleElements() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);

        for (int i=0; i<array.length; i++) {
            assertEquals(array[i], list.get(i));
        }
        assertEquals(array[0], list.head());
        assertEquals(array[array.length-1], list.last());
        assertEquals(4, list.length());
    }

    @Test
    void testRemoveMiddleElement() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);
        list.remove(3);
        Integer[] expected = {1, 8, 3};

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
        assertEquals(expected[0], list.head());
        assertEquals(expected[expected.length-1], list.last());
        assertEquals(3, list.length());
    }

    @Test
    void testRemoveFirstElement() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);
        list.remove(1);
        Integer[] expected = {3, 8, 3};

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
        assertEquals(expected[0], list.head());
        assertEquals(expected[expected.length-1], list.last());
        assertEquals(3, list.length());
    }

    @Test
    void testRemoveLastElement() {
        Integer[] array = {1, 3, 8, 4};
        fillFromArray(array);
        list.remove(4);
        Integer[] expected = {1, 3, 8};

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
        assertEquals(expected[0], list.head());
        assertEquals(expected[expected.length-1], list.last());
        assertEquals(3, list.length());
    }

    @Test
    void testRemoveOnlyElement() {
        list.add(4);
        list.remove(4);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(NoSuchElementException.class, () -> list.head());
        assertThrows(NoSuchElementException.class, () -> list.last());
        assertEquals(0, list.length());
    }

    @Test
    void testRemoveMultipleElements() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);
        list.remove(3);
        list.remove(8);
        Integer[] expected = {1, 3};

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
        assertEquals(expected[0], list.head());
        assertEquals(expected[expected.length-1], list.last());
        assertEquals(2, list.length());
    }

    @Test
    void testInsertInMiddle() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);
        list.insert(1, 4);
        Integer[] expected = {1, 4, 3, 8, 3};

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
        assertEquals(expected[0], list.head());
        assertEquals(expected[expected.length-1], list.last());
        assertEquals(5, list.length());
    }

    @Test
    void testInsertAsFirst() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);
        list.insert(0, 4);
        Integer[] expected = {4, 1, 3, 8, 3};

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
        assertEquals(expected[0], list.head());
        assertEquals(expected[expected.length-1], list.last());
        assertEquals(5, list.length());
    }

    @Test
    void testInsertAsLast() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);
        list.insert(4, 4);
        Integer[] expected = {1, 3, 8, 3, 4};

        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
        assertEquals(expected[0], list.head());
        assertEquals(expected[expected.length-1], list.last());
        assertEquals(5, list.length());
    }

    @Test
    void testInsertToEmptyList() {
        Integer element = 1;
        list.insert(0, element);

        assertEquals(element, list.get(0));
        assertEquals(element, list.head());
        assertEquals(element, list.last());
        assertEquals(1, list.length());
    }

    @Test
    void testContainsReturnsTrue() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);

        assertTrue(list.contains(1));
        assertTrue(list.contains(3));
        assertTrue(list.contains(8));
    }

    @Test
    void testContainsReturnsFalse() {
        Integer[] array = {1, 3, 8, 3};
        fillFromArray(array);

        assertFalse(list.contains(4));
    }

    @Test
    void testContainsReturnsFalseForEmptyList() {
        assertFalse(list.contains(1));
    }

    @Test
    void testIndexOfForCorrectValues() {
        Integer[] array = {1, 3, 8, 4};
        fillFromArray(array);

        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(3));
        assertEquals(2, list.indexOf(8));
        assertEquals(3, list.indexOf(4));
    }

    @Test
    void testIndexOfForIncorrectValue() {
        Integer[] array = {1, 3, 8, 4};
        fillFromArray(array);

        assertEquals(-1, list.indexOf(5));
    }

    @Test
    void testIndexOfForEmptyList() {
        assertEquals(-1, list.indexOf(1));
    }

    @Test
    void testIterator() {
        Integer[] array = {1, 3, 8, 4};
        fillFromArray(array);

        int i = 0;
        for (Integer integer : list) {
            assertEquals(array[i++], integer);
        }
        assertEquals(3, i);
    }

    private void fillFromArray(Integer[] array) {
        for (Integer element: array) {
            list.add(element);
        }
    }

}
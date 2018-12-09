package com.codecool.dynamicArrayDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryMaxHeapTest {

    private BinaryMaxHeap<Integer> heap;

    @BeforeEach
    void init() {
        Integer[] data = {2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        heap = new BinaryMaxHeap<>(data);
    }

    @Test
    void testHeapInitialization() {
        Integer[] actual = heap.getData();
        Integer[] expected = {90, 36, 26, 25, 19, 17, 1, 7, 3, 2};

        assertEquals(expected.length, heap.size());
        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testAdd() {
        heap.add(69);
        Integer[] actual = heap.getData();
        Integer[] expected = {90, 69, 26, 25, 36, 17, 1, 7, 3, 2, 19};

        assertEquals(expected.length, heap.size());
        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testPeek() {
        assertEquals(new Integer(90), heap.peek());
    }

    @Test
    void testPeekThrowsExceptionForEmpty() {
        BinaryMaxHeap<Integer> newHeap = new BinaryMaxHeap<>(Integer.class);

        assertThrows(IllegalStateException.class, newHeap::peek);
    }

    @Test
    void testPoll() {
        Integer[] expected = {90, 36, 26, 25, 19, 17, 7, 3, 2, 1};

        for (Integer element: expected) {
            assertEquals(element, heap.poll());
        }
    }

    @Test
    void testPollThrowsExceptionForEmpty() {
        BinaryMaxHeap<Integer> newHeap = new BinaryMaxHeap<>(Integer.class);

        assertThrows(IllegalStateException.class, newHeap::poll);
    }
}
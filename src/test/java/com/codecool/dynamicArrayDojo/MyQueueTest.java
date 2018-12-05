package com.codecool.dynamicArrayDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    private MyQueue<Integer> queue;

    @BeforeEach
    void init() {
        queue = new MyQueue<>();
    }

    @Test
    void testSizeForEmpty() {
        assertEquals(0, queue.size());
    }

    @Test
    void testSizeForFilled() {
        Integer[] data = {1, 3, 8, 3, 4};
        fillFromArray(data);

        assertEquals(5, queue.size());
    }

    @Test
    void testPeek() {
        Integer element = 1;
        queue.enqueue(element);

        assertEquals(element, queue.peek());
    }

    @Test
    void testEnqueueAndDequeueSingleElement() {
        Integer element = 1;
        queue.enqueue(element);

        assertEquals(1, queue.size());
        assertEquals(element, queue.dequeue());
    }

    @Test
    void testEnqueueAndDequeueMultipleElements() {
        Integer[] data = {1, 3, 8, 3};
        fillFromArray(data);

        assertEquals(4, queue.size());
        assertEquals(data[0], queue.peek());
        assertEquals(data[data.length-1], queue.last());
        for (Integer element: data) {
            assertEquals(element, queue.dequeue());
        }
        assertEquals(0, queue.size());
    }

    @Test
    void testDequeueThrowsExceptionWithEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    void testLastThrowsExceptionWithEmptyQueue() {
        assertTrue(queue.isEmpty());
        assertThrows(NoSuchElementException.class, () -> queue.last());
    }

    private void fillFromArray(Integer[] array) {
        for (Integer element: array) {
            queue.enqueue(element);
        }
    }

}
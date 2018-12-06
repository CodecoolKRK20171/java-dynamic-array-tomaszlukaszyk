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

    @Test
    void testEnqueueWithPriorityInMiddle() {
        Integer[] data = {2, 1, 1, 0, 0, 0};
        fillFromArrayWithPriority(data);
        queue.enqueue(3, 1);
        Integer[] expected = {2, 1, 1, 3, 0, 0, 0};

        assertEquals(7, queue.size());
        assertEquals(expected[0], queue.peek());
        assertEquals(expected[expected.length-1], queue.last());
        for (Integer element: expected) {
            assertEquals(element, queue.dequeue());
        }
    }

    @Test
    void testEnqueueWithPriorityAsFirst() {
        Integer[] data = {2, 1, 1, 0, 0, 0};
        fillFromArrayWithPriority(data);
        queue.enqueue(3, 3);
        Integer[] expected = {3, 2, 1, 1, 0, 0, 0};

        assertEquals(7, queue.size());
        assertEquals(expected[0], queue.peek());
        assertEquals(expected[expected.length-1], queue.last());
        for (Integer element: expected) {
            assertEquals(element, queue.dequeue());
        }
    }

    @Test
    void testEnqueueWithPriorityAsLast() {
        Integer[] data = {2, 1, 1, 0, 0, 0};
        fillFromArrayWithPriority(data);
        queue.enqueue(3, 0);
        Integer[] expected = {2, 1, 1, 0, 0, 0, 3};

        assertEquals(7, queue.size());
        assertEquals(expected[0], queue.peek());
        assertEquals(expected[expected.length-1], queue.last());
        for (Integer element: expected) {
            assertEquals(element, queue.dequeue());
        }
    }

    private void fillFromArray(Integer[] array) {
        for (Integer element: array) {
            queue.enqueue(element);
        }
    }

    private void fillFromArrayWithPriority(Integer[] array) {
        for (Integer element: array) {
            queue.enqueue(element, element);
        }
    }

}
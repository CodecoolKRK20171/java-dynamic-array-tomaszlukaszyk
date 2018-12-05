package com.codecool.dynamicArrayDojo;

import com.codecool.dynamicArrayDojo.exceptions.StackOverflow;
import com.codecool.dynamicArrayDojo.exceptions.StackUnderflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    private final int INITIAL_SIZE = 10;

    private MyStack<Integer> stack;

    @BeforeEach
    void init() {
        stack = new MyStack<>(INITIAL_SIZE);
    }

    @Test
    void testSizeIsRight() {
        assertEquals(INITIAL_SIZE, stack.size());
    }

    @Test
    void testPushAndPopSingleElement() {
        Integer data = 3;
        stack.push(data);

        assertEquals(9, stack.remainingSpace());
        assertEquals(data, stack.pop());
        assertEquals(10, stack.remainingSpace());
    }

    @Test
    void testPushAndPopMultipleElements() {
        Integer[] data = {1, 3, 8, 3};
        fillFromArray(data);

        assertEquals(6, stack.remainingSpace());
        for (int i=data.length-1; i >=0; i--) {
            assertEquals(data[i], stack.pop());
        }
    }

    @Test
    void testPeek() {
        Integer[] data = {1, 3, 8, 3};
        fillFromArray(data);

        assertEquals(data[data.length-1], stack.peek());
        assertEquals(6, stack.remainingSpace());
    }

    @Test
    void testRemainingSpaceIsRightForFullStack() {
        Integer[] data = {1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10};
        fillFromArray(data);

        assertEquals(0, stack.remainingSpace());
    }

    @Test
    void testPushThrowsExceptionOnFullStack() {
        Integer[] data = {1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10};
        fillFromArray(data);

        assertThrows(StackOverflow.class, () -> stack.push(11)); // doesn't go up to 11 :(
    }

    @Test
    void testPopThrowsExceptionOnEmptyStack() {
        assertThrows(StackUnderflow.class, () -> stack.pop());
    }

    private void fillFromArray(Integer[] array) {
        for (Integer element: array) {
            stack.push(element);
        }
    }
}
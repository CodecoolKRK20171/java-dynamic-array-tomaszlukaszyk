package com.codecool.dynamicArrayDojo;

import com.codecool.dynamicArrayDojo.exceptions.StackOverflow;
import com.codecool.dynamicArrayDojo.exceptions.StackUnderflow;

public class MyStack<T> {

    private T[] array;
    private int cursor;

    public MyStack(int size) {
        this.array = (T[]) new Object[size];
        this.cursor = -1;
    }

    public void push(T data) {
        ++this.cursor;
        if (this.cursor >= this.array.length) throw new StackOverflow("Stack is full, you can't add more data!");

        this.array[this.cursor] = data;
    }

    public T pop() {
        if (this.cursor < 0) throw  new StackUnderflow("Stack is empty, you can't retrieve any data!");

        return this.array[this.cursor--];
    }

    public T peek() {
        if (this.cursor < 0) return null;

        return this.array[this.cursor];
    }

    public int size() {
        return this.array.length;
    }

    public int remainingSpace() {
        return this.array.length - (this.cursor + 1);
    }
}

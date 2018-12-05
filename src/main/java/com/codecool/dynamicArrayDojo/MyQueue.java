package com.codecool.dynamicArrayDojo;

import java.util.NoSuchElementException;

public class MyQueue<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public T last() {
        if (this.last == null) throw new NoSuchElementException();

        return this.last.getData();
    }

    public int size() {
        return this.size;
    }

    public void enqueue(T data) {
        this.size++;
        Node<T> newNode = new Node<>(data);
        newNode.setNext(null);

        if (this.first == null) {
            this.first = this.last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    public T peek() {
        if (this.size <= 0) return null;

        return this.first.getData();
    }

    public T dequeue() {
        if (this.size <= 0) throw new NoSuchElementException();

        this.size--;
        Node<T> out = this.first;
        this.first = this.first.getNext();
        out.setNext(null);
        return out.getData();
    }
}

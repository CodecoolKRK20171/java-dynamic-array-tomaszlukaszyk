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

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(T data) {
        this.size++;
        Node<T> newNode = new Node<>(data);

        if (this.first == null) {
            this.first = this.last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    public void enqueue(T data, int priority) {
        this.size++;
        Node<T> newNode = new Node<>(data);
        newNode.setPriority(priority);

        if (this.first == null) {
            this.first = this.last = newNode;
        } else {
            Node<T> previous = getLastWithPriority(priority);

            if (previous == null) {
                newNode.setNext(this.first);
                this.first = newNode;
                return;
            }

            newNode.setNext(previous.getNext());
            previous.setNext(newNode);

            if (previous == this.last) this.last = newNode;
        }
    }

    private Node<T> getLastWithPriority(int priority) {
        Node<T> current = this.first;
        while (current.getNext() != null && current.getNext().getPriority() >= priority) {
            current = current.getNext();
        }

        if (current.getPriority() < priority) return null;
        return current;
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

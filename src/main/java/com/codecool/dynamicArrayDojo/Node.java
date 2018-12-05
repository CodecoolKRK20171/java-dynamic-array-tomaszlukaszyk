package com.codecool.dynamicArrayDojo;

public class Node<T> {

    private T data;
    private Node<T> next;
    private int priority;

    public Node(T data) {
        this.data = data;
        this.priority = 0;
        this.next = null;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}

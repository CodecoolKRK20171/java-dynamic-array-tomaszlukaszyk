package com.codecool.dynamicArrayDojo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> last;
    private int length;

    public SinglyLinkedList() {
        head = null;
        last = null;
        length = 0;
    }

    public T head() {
        if (this.head == null) throw new NoSuchElementException();
        return this.head.getData();
    }

    public T last() {
        if (this.last == null) throw new NoSuchElementException();
        return this.last.getData();
    }

    public int length() {
        return this.length;
    }

    public T get(int index) {
        if (index > this.length - 1 || index < 0) throw new IndexOutOfBoundsException();
        return getNodeByIndex(index).getData();
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> current = this.head;
        for(int i=0; i<index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public void add(T data) {
        this.length++;
        Node<T> newNode = new Node<>(data);

        if (this.head == null) {
            this.head = this.last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    public void remove(T data) {
        if (this.head == null) return;

        if (this.head.getData().equals(data)) {
            handleRemoveForHead();
            return;
        }

        handleRemoveForTail(data);
    }

    private void handleRemoveForHead() {
        Node<T> temp = this.head;
        this.head = this.head.getNext();
        temp.setNext(null);
        this.length--;

        if (this.last == temp) {
            this.last = null;
        }
    }

    private void handleRemoveForTail(T data) {
        Node<T> previous = this.head;
        Node<T> current = previous.getNext();
        while (current != null) {
            if (current.getData().equals(data)) {
                previous.setNext(current.getNext());
                current.setNext(null);
                this.length--;

                if (this.last == current) {
                    last = previous;
                }
            }
            previous = current;
            current = previous.getNext();
        }
    }

    public void insert(int index, T data) {
        if (index > this.length || index < 0) throw new IndexOutOfBoundsException();

        if (index == this.length) {
            add(data);
            return;
        }

        this.length++;
        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
            return;
        }

        Node<T> previous = getNodeByIndex(index -1);

        newNode.setNext(previous.getNext());
        previous.setNext(newNode);
    }

    public boolean contains(T data) {
        if (this.head == null) return false;

        Node<T> current = this.head;
        while (current.getNext() != null) {
            if (current.getData().equals(data)) return true;
            current = current.getNext();
        }
        return false;
    }

    public int indexOf(T data) {
        if (this.head == null) return -1;

        Node<T> current = this.head;
        for (int i=0; i<this.length; i++) {
            if (current.getData().equals(data)) return i;
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = this.head;
        while (current != null) {
            sb.append(" ");
            sb.append(current.toString());
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<T> {

        private Node<T> current;

        public SinglyLinkedListIterator() {
            this.current = SinglyLinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            T currentData = current.getData();
            current = current.getNext();
            return currentData;
        }
    }
}

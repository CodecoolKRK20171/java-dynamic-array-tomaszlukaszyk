package com.codecool.dynamicArrayDojo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BinaryMaxHeap<T extends Comparable<T>> {

    private final int INITIAL_SIZE = 10;

    private T[] data;
    private int size;

    public BinaryMaxHeap(Class<T> type) {
        this.data = (T[]) Array.newInstance(type, INITIAL_SIZE);
        this.size = 0;
    }

    public BinaryMaxHeap(T[] data) {
        this.data = data;
        this.size = data.length;
        initialReorder();
    }

    private void initialReorder() {
        int middleIndex = (int) Math.floor(this.data.length /2) - 1;

        for (int i = middleIndex; i>=0; i--) {
            shiftDown(i);
        }
    }

    public void add(T element) {
        increaseSize();
        this.data[this.size - 1] = element;
        shiftUp(this.size - 1);
    }

    public T peek() {
        if (size == 0) throw new IllegalStateException("Heap is empty");

        return this.data[0];
    }

    public T poll() {
        if (size == 0) throw new IllegalStateException("Heap is empty");

        T toReturn = this.data[0];
        swap(0, this.size -1);
        decreaseSize();
        shiftDown(0);

        return toReturn;
    }

    private void shiftDown(int index) {
        while (hasLeftChild(index)) {
            int biggerChildIndex = getBiggerChildIndex(index);

            if (this.data[biggerChildIndex].compareTo(this.data[index]) > 0) {
                swap(index, biggerChildIndex);
                index = biggerChildIndex;

            } else break;
        }
    }

    private void shiftUp(int index) {
        while (hasParent(index)) {
            int parentIndex = getParentIndex(index);

            if (this.data[parentIndex].compareTo(this.data[index]) < 0) {
                swap(index, parentIndex);
                index = parentIndex;

            } else break;
        }
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) > 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < this.size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < this.size;
    }

    private int getParentIndex(int index) {
        return (int) Math.ceil(index/2) - 1;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private int getBiggerChildIndex(int index) {
        if (!hasRightChild(index) ||
                this.data[getLeftChildIndex(index)].compareTo(this.data[getRightChildIndex(index)]) > 0) {
            return getLeftChildIndex(index);
        }
        return getRightChildIndex(index);
    }

    private void swap(int i, int j) {
        T temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
    }

    private void increaseSize() {
        this.size++;
        if (this.size > this.data.length) {
            this.data = Arrays.copyOf(this.data, this.data.length + INITIAL_SIZE);
        }
    }

    private void decreaseSize() {
        this.size --;
        if (this.size + INITIAL_SIZE < this.data.length) {
            this.data = Arrays.copyOf(this.data, this.data.length - INITIAL_SIZE);
        }
    }

    public int size() {
        return this.size;
    }

    public T[] getData() {
        return data;
    }
}

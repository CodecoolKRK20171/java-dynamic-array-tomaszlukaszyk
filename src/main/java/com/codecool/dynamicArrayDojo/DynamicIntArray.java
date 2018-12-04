package com.codecool.dynamicArrayDojo;

import java.util.Arrays;

// put your code here!
public class DynamicIntArray {

    private final int INITIAL_SIZE = 10;

    private int[] array;
    private int size;

    public DynamicIntArray() {
        this.array = new int[INITIAL_SIZE];
    }

    public DynamicIntArray(int size) {
        this.array = new int[size];
        this.size = size;
    }

    public void add(int element) {
        increaseSize();
        this.array[this.size -1] = element;
    }

    private void increaseSize() {
        this.size++;
        if (this.array.length < this.size) {
            this.array = Arrays.copyOf(array, this.size + INITIAL_SIZE);
        }
    }

    public void remove(int element) {
        int searchedIndex = findIndexOfElement(element);
        if (searchedIndex == -1) throw new ArrayIndexOutOfBoundsException();

        for (int i = searchedIndex; i<this.size-1; i++) {
            this.array[i] = this.array[i+1];
        }

        decreaseSize();
    }

    private int findIndexOfElement(int element) {
        for (int i = 0; i<this.size; i++) {
            if (this.array[i] == element) return i;
        }
        return -1;
    }

    private void decreaseSize() {
        this.size--;
        if (this.array.length > this.size + INITIAL_SIZE) {
            this.array = Arrays.copyOf(array, array.length - INITIAL_SIZE);
        }
    }

    public void insert(int index, int element) {
        if (index > this.size) {
            add(element);
            return;
        }

        increaseSize();

        for (int i = this.size; i>index; i--) {
            array[i] = array[i-1];
        }

        array[index] = element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<this.size; i++) {
            sb.append(" ");
            sb.append(array[i]);
        }
        return sb.toString();
    }
}

package com.codecool.dynamicArrayDojo.exceptions;

public class StackOverflow extends RuntimeException {

    public StackOverflow() {
    }

    public StackOverflow(String message) {
        super(message);
    }
}

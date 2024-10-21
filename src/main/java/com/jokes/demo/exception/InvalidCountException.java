package com.jokes.demo.exception;

public class InvalidCountException extends RuntimeException {
    public InvalidCountException(String message) {
        super(message);
    }
}
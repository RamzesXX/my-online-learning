package com.khanchych.udemy.javaindepth.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}

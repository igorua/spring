package com.example.spring.exception;

public class RegionDoesNotExistException extends RuntimeException {
    public RegionDoesNotExistException(String message) {
        super(message);
    }
}

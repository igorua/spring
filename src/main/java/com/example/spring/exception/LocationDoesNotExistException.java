package com.example.spring.exception;

public class LocationDoesNotExistException extends RuntimeException {
    public LocationDoesNotExistException(String message) {
        super(message);
    }
}

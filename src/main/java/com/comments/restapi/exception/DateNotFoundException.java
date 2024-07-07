package com.comments.restapi.exception;

public class DateNotFoundException extends RuntimeException {
    public DateNotFoundException(String message) {
        super(message);
    }
}
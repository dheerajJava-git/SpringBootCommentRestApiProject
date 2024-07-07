package com.comments.restapi.exception;

public class UsernameChangeException extends RuntimeException {
    public UsernameChangeException(String message) {
        super(message);
    }
}
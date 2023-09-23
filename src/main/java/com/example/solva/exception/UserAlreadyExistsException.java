package com.example.solva.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

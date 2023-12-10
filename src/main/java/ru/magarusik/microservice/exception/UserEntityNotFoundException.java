package ru.magarusik.microservice.exception;

public class UserEntityNotFoundException extends RuntimeException {
    public UserEntityNotFoundException(String message) {
        super(message);
    }
}

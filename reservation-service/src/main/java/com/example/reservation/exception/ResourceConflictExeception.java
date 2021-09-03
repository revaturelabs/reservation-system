package com.example.reservation.exception;

public class ResourceConflictExeception extends RuntimeException {
    public ResourceConflictExeception(String message) {
        super(message);
    }
}

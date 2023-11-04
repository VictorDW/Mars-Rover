package com.rover.MarsRover.infra.errors;

public class IntegrityValidationException extends RuntimeException {
    public IntegrityValidationException(String message) {
        super(message);
    }
}

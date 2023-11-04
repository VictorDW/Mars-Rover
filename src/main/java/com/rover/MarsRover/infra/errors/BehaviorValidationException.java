package com.rover.MarsRover.infra.errors;

public class BehaviorValidationException extends RuntimeException {
    public BehaviorValidationException(String message) {
        super(message);
    }
}

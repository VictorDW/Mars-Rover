package com.rover.MarsRover.infra.errors;

import org.springframework.validation.FieldError;

public record ExceptionDataResponse(
        String fieldName,
        String message
) {

    public ExceptionDataResponse(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}

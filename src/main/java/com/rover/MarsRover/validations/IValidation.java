package com.rover.MarsRover.validations;

@FunctionalInterface
public interface IValidation<T> {
    void validate(T data);
}

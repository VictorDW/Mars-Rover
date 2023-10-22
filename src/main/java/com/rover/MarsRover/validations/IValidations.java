package com.rover.MarsRover.validations;

public interface IValidations<T> {

    void validations(T data);

    default IValidations<T> runValidation(T data, IValidation<? super T> validation) {
        validation.validate(data);
        return this;
    }
}

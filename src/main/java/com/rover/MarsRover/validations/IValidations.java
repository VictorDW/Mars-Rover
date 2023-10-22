package com.rover.MarsRover.validations;

public interface IValidations<T> {

    void Validations(T data);

    default IValidations<T> runValidation(T data, IValidation<? super T> validation) {
        validation.validate(data);
        return this;
    }
}

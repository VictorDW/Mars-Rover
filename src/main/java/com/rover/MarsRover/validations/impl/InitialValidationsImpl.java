package com.rover.MarsRover.validations.impl;

import com.rover.MarsRover.infra.errors.InitialValidationsExceptions;
import com.rover.MarsRover.validations.DTO.InitialData;
import com.rover.MarsRover.validations.InitialValidations;
import org.springframework.stereotype.Component;

@Component
public class InitialValidationsImpl implements InitialValidations {

    @Override
    public void validations(InitialData components) {

        this.runValidation(components, component -> InitialValidations.isMapActive(component.map()))
                .runValidation(components, this::isRoverActive)
                .runValidation(components, this::isInitiated);
    }

    private void isRoverActive(InitialData components) {

        if(!components.rover().isActive()) {
            throw new InitialValidationsExceptions("Se debe crear un Rover inicialmente");
        }
    }

    private void isInitiated(InitialData components) {

        if (!components.isInitiated()) {
            throw new InitialValidationsExceptions("Error, No se ha inicializado el Rover");
        }
    }

}
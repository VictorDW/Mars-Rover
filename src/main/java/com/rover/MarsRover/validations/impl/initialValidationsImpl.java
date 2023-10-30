package com.rover.MarsRover.validations.impl;

import com.rover.MarsRover.validations.DTO.InitialData;
import com.rover.MarsRover.validations.InitialValidations;
import org.springframework.stereotype.Component;

@Component
public class initialValidationsImpl implements InitialValidations {
    @Override
    public void validations(InitialData components) {

        this.runValidation(components, this::isMapActive)
                .runValidation(components, this::isRoverActive);

             /*
             // Variante en una sola lambda

             .runValidation(components,
                        items -> {
                            if(!items.rover().isActive()) {
                                throw new RuntimeException("Se debe crear un Rover inicialmente");
                            }else if(!items.map().isActive()) {
                                throw new RuntimeException("Se debe crear un Map inicialmente");
                            }
                        });

              */
    }


    private void isMapActive(InitialData components) {

            if(!components.map().isActive()) {
                throw new RuntimeException("Se debe crear un Mapa inicialmente");
            }
    }

    private void isRoverActive(InitialData components) {

        if(!components.rover().isActive()) {
            throw new RuntimeException("Se debe crear un Rover inicialmente");
        }
    }

}

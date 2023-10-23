package com.rover.MarsRover.validations;

import org.springframework.stereotype.Component;

@Component
public class initialValidationsImpl implements InitialValidations {
    @Override
    public void validations(InitialData data) {

        this.runValidation(data,
                        items-> {
                            if(!items.map().isActive())
                                throw new RuntimeException("Se debe crear un Mapa inicialmente");
                        })
                .runValidation(data,
                        items-> {
                            if(!items.rover().isActive())
                                throw new RuntimeException("Se debe crear un Rover inicialmente");
                        });
    }
}

package com.rover.MarsRover.validations;

import com.rover.MarsRover.infra.errors.InitialValidationsExceptions;
import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.validations.DTO.InitialData;

public interface InitialValidations extends IValidations<InitialData> {

    static void isMapActive(Map map) {
        if(!map.isActive()) {
            throw new InitialValidationsExceptions("Se debe crear un Mapa inicialmente");
        }
    }
}

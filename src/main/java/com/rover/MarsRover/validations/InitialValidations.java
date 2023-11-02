package com.rover.MarsRover.validations;

import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.validations.DTO.InitialData;

public interface InitialValidations extends IValidations<InitialData> {

    static void mapValid(Map map) {
        if(!map.isActive()) {
            throw new RuntimeException("Se debe crear un Mapa inicialmente");
        }
    }
}

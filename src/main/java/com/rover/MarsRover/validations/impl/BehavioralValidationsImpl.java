package com.rover.MarsRover.validations.impl;

import com.rover.MarsRover.validations.BehavioralValidations;
import com.rover.MarsRover.validations.DTO.CoordinatesData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("RoverValidate")
public class BehavioralValidationsImpl implements BehavioralValidations {


    @Override
        public void validations(CoordinatesData coordinates) {

        this.runValidation(coordinates, this::isMapLimit)
                .runValidation(coordinates, this::thereIsObstacle);
    }

}

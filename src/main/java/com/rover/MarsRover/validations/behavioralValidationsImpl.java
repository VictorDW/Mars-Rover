package com.rover.MarsRover.validations;

import org.springframework.stereotype.Component;

@Component
public class behavioralValidationsImpl implements IValidations<CoordinatesData>{
    @Override
    public void validations(CoordinatesData data) {

        this.runValidation(data, this::isMapLimit);

    }

    private void isMapLimit(CoordinatesData coordinates) {

        if (!(coordinates.x1() > 0 && coordinates.x1() < coordinates.x2() &&
                coordinates.y1() > 0 && coordinates.y1() < coordinates.y2())) {

            throw new RuntimeException("La posición ingresada ("+coordinates.x1()+","+coordinates.y1()+") supera los limites del mapa");
        }
    }
}

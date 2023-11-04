package com.rover.MarsRover.validations;

import com.rover.MarsRover.infra.errors.BehaviorValidationException;
import com.rover.MarsRover.service.AbstractObstacleService;
import com.rover.MarsRover.validations.DTO.CoordinatesData;

public interface BehavioralValidations extends IValidations<CoordinatesData>{

    default void isMapLimit(CoordinatesData coordinates) {

        if (!(coordinates.x1() > 0 && coordinates.x1() < coordinates.x2() &&
                coordinates.y1() > 0 && coordinates.y1() < coordinates.y2())) {

            throw new BehaviorValidationException("La posición ("+coordinates.x1()+","+coordinates.y1()+") esta superando los limites del mapa");
        }
    }

    default void thereIsObstacle(CoordinatesData coordinates) {

        AbstractObstacleService.obstacles()
                .forEach(
                        obstacle->{
                            if (coordinates.x1().equals(obstacle.getPosition().getCoordinateX()) &&
                                    coordinates.y1().equals(obstacle.getPosition().getCoordinateY())) {

                                throw new BehaviorValidationException("Hay un obstaculo en las coordenadas (" + coordinates.x1() + "," + coordinates.y1() + ")");
                            }
                        }
                );
    }
}

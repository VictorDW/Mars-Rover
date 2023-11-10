package com.rover.MarsRover.validations;

import com.rover.MarsRover.infra.errors.BehaviorValidationException;
import com.rover.MarsRover.service.AbstractObstacleService;
import com.rover.MarsRover.validations.DTO.CoordinatesData;

public interface BehavioralValidations extends IValidations<CoordinatesData>{

    default void isMapLimit(CoordinatesData coordinates) {

        if (!(coordinates.x() > 0 && coordinates.x() < coordinates.widthMap() &&
                coordinates.y() > 0 && coordinates.y() < coordinates.heightMap())) {

            throw new BehaviorValidationException("La posición ("+coordinates.x()+","+coordinates.y()+") esta superando los limites del mapa");
        }
    }

    default void thereIsObstacle(CoordinatesData coordinates) {

        AbstractObstacleService.obstacles()
                .forEach(
                        obstacle->{
                            if (coordinates.x().equals(obstacle.getPosition().getCoordinateX()) &&
                                    coordinates.y().equals(obstacle.getPosition().getCoordinateY())) {

                                throw new BehaviorValidationException("Hay un obstaculo en las coordenadas (" + coordinates.x() + "," + coordinates.y() + ")");
                            }
                        }
                );
    }
}

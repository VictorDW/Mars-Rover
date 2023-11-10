package com.rover.MarsRover.validations.impl;

import com.rover.MarsRover.infra.errors.BehaviorValidationException;
import com.rover.MarsRover.repository.RoverRepository;
import com.rover.MarsRover.validations.BehavioralValidations;
import com.rover.MarsRover.validations.DTO.CoordinatesData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ObstacleValidation")
@AllArgsConstructor
public class ObstacleBehaviorValidationImpl implements BehavioralValidations {

    private final RoverRepository roverRepository;

    @Override
    public void validations(CoordinatesData coordinates) {

        this.runValidation(coordinates, this::isMapLimit)
                .runValidation(coordinates, this::thereIsRover)
                .runValidation(coordinates, this::thereIsObstacle);
    }

    public void thereIsRover(CoordinatesData coordinates) {

        roverRepository.findAll().stream().findFirst().ifPresent(
                rover -> {
                    if (coordinates.x().equals(rover.getPosition().getCoordinateX()) &&
                            coordinates.y().equals(rover.getPosition().getCoordinateY())) {

                        throw new BehaviorValidationException("El rover esta en las coordenadas (" + coordinates.x() + "," + coordinates.y() + ")");
                    }
                }
        );

    }
}

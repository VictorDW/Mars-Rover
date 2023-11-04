package com.rover.MarsRover.validations.impl;

import com.rover.MarsRover.infra.errors.IntegrityValidationException;
import com.rover.MarsRover.model.Obstacle;
import com.rover.MarsRover.validations.IntegrityValidation;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntegrityValidationImpl implements IntegrityValidation {

    @Override
    public void validations(Optional<Obstacle> obstacle) {

        this.runValidation(obstacle,
                integrityObstacle -> {
                        if(integrityObstacle.isEmpty())
                            throw new IntegrityValidationException("No se encontró ningún obstáculo con ese id");
                    });
    }
}

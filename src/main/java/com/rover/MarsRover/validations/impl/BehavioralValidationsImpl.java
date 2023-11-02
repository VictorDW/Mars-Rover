package com.rover.MarsRover.validations.impl;

import com.rover.MarsRover.service.AbstractObstacleService;
import com.rover.MarsRover.validations.BehavioralValidations;
import com.rover.MarsRover.validations.DTO.CoordinatesData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class BehavioralValidationsImpl implements BehavioralValidations {


    @Override
    public void validations(CoordinatesData coordinates) {

       // this.runValidation(coordinates, this::isCoordenateMapValid)
        this.runValidation(coordinates, this::isMapLimit)
                .runValidation(coordinates, this::thereIsObstacle);
    }

  /*  private void isCoordenateMapValid(CoordinatesData coordinates) {

        if(Objects.isNull(coordinates.x2()) || Objects.isNull(coordinates.y2())) {
            throw new RuntimeException("Error en las dimensiones del mapa");
        }
    }

   */

    private void isMapLimit(CoordinatesData coordinates) {

        if (!(coordinates.x1() > 0 && coordinates.x1() < coordinates.x2() &&
                coordinates.y1() > 0 && coordinates.y1() < coordinates.y2())) {

            throw new RuntimeException("La posición ("+coordinates.x1()+","+coordinates.y1()+") esta superando los limites del mapa");
        }
    }

    private void thereIsObstacle(CoordinatesData coordinates) {

        AbstractObstacleService.obstacles()
                .forEach(
                        obstacle->{
                            if (coordinates.x1().equals(obstacle.getPosition().getCoordinateX()) &&
                                    coordinates.y1().equals(obstacle.getPosition().getCoordinateY())) {

                                throw new RuntimeException("Hay un obstaculo en las coordenadas (" + coordinates.x1() + "," + coordinates.y1() + ")");
                            }
                        }
                );
    }
}

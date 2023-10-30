package com.rover.MarsRover.DTO.response;

import com.rover.MarsRover.model.Obstacle;

public record ObstacleDataResponse(

        Long id,
        Integer coordinateX,
        Integer coordinateY
) {

    public ObstacleDataResponse(Obstacle obstacle) {
        this(obstacle.getId(),
                obstacle.getPosition().getCoordinateX(),
                obstacle.getPosition().getCoordinateY());
    }
}

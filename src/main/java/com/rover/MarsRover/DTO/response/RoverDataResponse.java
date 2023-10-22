package com.rover.MarsRover.DTO.response;

import com.rover.MarsRover.model.Rover;

public record RoverDataResponse(

    Long id,
    String name,
    Integer coordinateX,
    Integer coordinateY,
    String orientation
) {

    public RoverDataResponse(Rover rover) {
        this(rover.getId(),
                rover.getName(),
                rover.getPosition().getCoordinateX(),
                rover.getPosition().getCoordinateY(),
                rover.getOrientation().name());
    }
}

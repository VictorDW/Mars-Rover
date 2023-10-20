package com.rover.MarsRover.DTO;

import com.rover.MarsRover.model.Orientation;

public record RoverDataRequest(
        String name,
        Integer coordinateX,
        Integer coordinateY,
        Orientation orientation
) { }

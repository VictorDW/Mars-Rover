package com.rover.MarsRover.validations.DTO;

import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.model.Rover;

public record InitialData(
        Rover rover,
        Map map,
        boolean isInitiated
) { }

package com.rover.MarsRover.validations;

import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.model.Rover;

public record InitialData(
        Rover rover,
        Map map
) { }

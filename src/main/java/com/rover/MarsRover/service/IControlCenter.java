package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.model.Orientation;
import com.rover.MarsRover.model.Rover;

import java.util.function.ToIntFunction;

public interface IControlCenter {
    void loadItems(Rover rover, Map map);
    CoordinateDataResponse assingMovement(String command);
    CoordinateDataResponse assingNextCoordinates(Integer afterCoordinateX, Integer afterCoordinateY);
    String turn(ToIntFunction<Orientation> rotation);
}

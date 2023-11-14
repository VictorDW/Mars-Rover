package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.model.Rover;

public interface IControlCenter {
    void loadItems(Rover rover, Map map);
    CoordinateDataResponse assingMovement(String command);
    CoordinateDataResponse assingNextCoordinates(Integer afterCoordinateX, Integer afterCoordinateY);
    String turn(String command);
}

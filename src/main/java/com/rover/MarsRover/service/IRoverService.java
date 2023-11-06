package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.model.Rover;

public interface IRoverService {
    RoverDataResponse createRover(RoverDataRequest roverDataRequest);
    String initialization();
    RoverDataResponse getRover();
    void updateRoverCoordinates(Rover rover);
    CoordinateDataResponse moveRover(String commands);
    String turnRover(String command);
    Rover getInstanceRover();

}

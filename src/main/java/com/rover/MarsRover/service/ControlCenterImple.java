package com.rover.MarsRover.service;

import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.model.Rover;

public class ControlCenterImple implements IControlCenter{

    private final IRoverService roverService;
    private Map map;
    private Rover rover;

    public ControlCenterImple(IRoverService roverService) {
        this.roverService = roverService;
    }
    @Override
    public void loadItems(Rover rover, Map map) {
         this.rover = rover;
         this.map = map;

        System.out.println(rover);
        System.out.println(map);
    }
}

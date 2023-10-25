package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.model.Rover;
import com.rover.MarsRover.validations.BehavioralValidations;
import com.rover.MarsRover.validations.CoordinatesData;

import java.util.List;

public class ControlCenterImpl implements IControlCenter{

    private final IRoverService roverService;
    private final BehavioralValidations behavioralValidations;
    private Map map;
    private Rover rover;

    public ControlCenterImpl(IRoverService roverService, BehavioralValidations behavioralValidations) {
        this.roverService = roverService;
        this.behavioralValidations = behavioralValidations;
        ConfigMovement.functionInitialization();
    }

    @Override
    public void loadItems(Rover rover, Map map) {
         this.rover = rover;
         this.map = map;

        System.out.println(rover);
        System.out.println(map);
    }

    @Override
    public CoordinateDataResponse assingMovement(String command) {

        String movementKey = rover.getOrientation().name()+command;

        //Según el movimiento a realizar retorna una lista con las coordenadas actualizadas
        List<Integer> nextPosition =
                ConfigMovement.getMovement(movementKey)
                        .apply(
                                rover.getPosition().getCoordinateX(),
                                rover.getPosition().getCoordinateY()
                        );

        //se ejecutan las validaciones de comportamiento
        behavioralValidations.validations(new CoordinatesData(
                nextPosition.get(0),
                nextPosition.get(1),
                map.getWidth(),
                map.getHeight())
        );

        return assingNextCoordinates(nextPosition.get(0), nextPosition.get(1));
    }

    @Override
    public CoordinateDataResponse assingNextCoordinates(Integer afterCoordinateX, Integer afterCoordinateY) {

        rover.setPositionX(afterCoordinateX);
        rover.setPositionY(afterCoordinateY);

        System.out.println("("+afterCoordinateX+","+afterCoordinateY+")");

        return new CoordinateDataResponse(afterCoordinateX, afterCoordinateY);
    }


}

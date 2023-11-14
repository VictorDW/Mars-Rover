package com.rover.MarsRover.service.impl;

import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.model.Orientation;
import com.rover.MarsRover.model.Rover;
import com.rover.MarsRover.service.IControlCenter;
import com.rover.MarsRover.service.IRoverService;
import com.rover.MarsRover.validations.BehavioralValidations;
import com.rover.MarsRover.validations.DTO.CoordinatesData;

import java.util.List;

public class ControlCenterImpl implements IControlCenter {

    private final IRoverService roverService;
    private final BehavioralValidations behavioralValidations;
    private Map map;
    private Rover rover;

    public ControlCenterImpl(IRoverService roverService, BehavioralValidations behavioralValidations) {
        this.roverService = roverService;
        this.behavioralValidations = behavioralValidations;
    }

    @Override
    public void loadItems(Rover rover, Map map) {
         this.rover = rover;
         this.map = map;
    }

    private void executeBehaviorValidation(List<Integer> nextPosition) {
        //se ejecutan las validaciones de comportamiento para validar la nueva posición
        behavioralValidations.validations(
                new CoordinatesData(
                        nextPosition.get(0),
                        nextPosition.get(1),
                        map.getWidth(),
                        map.getHeight()
                )
        );
    }

    @Override
    public CoordinateDataResponse assingMovement(String command) {

        String movementKey = rover.getOrientation().name()+command;

        //Según el movimiento a realizar retorna una lista con las coordenadas actualizadas
        List<Integer> nextPosition =
                MovementHandler.getMovement(movementKey)
                        .apply(
                                rover.getPosition().getCoordinateX(),
                                rover.getPosition().getCoordinateY()
                        );

        executeBehaviorValidation(nextPosition);

        return assingNextCoordinates(nextPosition.get(0), nextPosition.get(1));

    }

    @Override
    public CoordinateDataResponse assingNextCoordinates(Integer afterCoordinateX, Integer afterCoordinateY) {

        rover.setPositionX(afterCoordinateX);
        rover.setPositionY(afterCoordinateY);
        roverService.updateRoverCoordinates(rover);

        return new CoordinateDataResponse(afterCoordinateX, afterCoordinateY);
    }

    @Override
    public String turn(String command) {

            Orientation afterOrientation =
                    Orientation.getByPosition(
                            MovementHandler.getTurn(command)
                                    .applyAsInt(rover.getOrientation())
                    );

            rover.setOrientation(afterOrientation);
            roverService.updateRoverCoordinates(rover);

            return afterOrientation.name();
    }
}

package com.rover.MarsRover.service.impl;

import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.model.Orientation;
import com.rover.MarsRover.model.Position;
import com.rover.MarsRover.model.Rover;
import com.rover.MarsRover.repository.RoverRepository;
import com.rover.MarsRover.service.IControlCenter;
import com.rover.MarsRover.service.IMapNavigationService;
import com.rover.MarsRover.service.IRoverService;
import com.rover.MarsRover.validations.*;
import com.rover.MarsRover.validations.DTO.CoordinatesData;
import com.rover.MarsRover.validations.DTO.InitialData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoverServiceImpl implements IRoverService {

    private final RoverRepository roverRepository;
    private final IMapNavigationService mapNavigationService;
    private final IControlCenter controlCenter;
    private final BehavioralValidations behavioralValidations;
    private final InitialValidations initialValidations;
    private Rover roverIntance;
    private boolean isInitiated = false;


    public RoverServiceImpl(RoverRepository roverRepository,
                            IMapNavigationService mapNavigationService,
                            @Qualifier("RoverValidate")
                            BehavioralValidations behavioralValidations,
                            InitialValidations initialValidations) {

        this.roverRepository = roverRepository;
        this.mapNavigationService = mapNavigationService;
        this.controlCenter = new ControlCenterImpl(this, behavioralValidations);
        this.behavioralValidations = behavioralValidations;
        this.initialValidations = initialValidations;
        this.roverIntance = getInstanceRover();
    }

    private void executeBehaviorValidations(RoverDataRequest roverDataRequest) {
        behavioralValidations.validations(
                new CoordinatesData(
                        roverDataRequest.coordinateX(),
                        roverDataRequest.coordinateY(),
                        mapNavigationService.getIntanceMap().getWidth(),
                        mapNavigationService.getIntanceMap().getHeight()
                )
        );
    }
    @Override
    public RoverDataResponse createRover(RoverDataRequest roverDataRequest) {

        //validación de inicialización
        InitialValidations.isMapActive(mapNavigationService.getIntanceMap());

        //validaciones de comportamiento
        executeBehaviorValidations(roverDataRequest);

        roverIntance.setName(roverDataRequest.name());
        roverIntance.setPosition(new Position(roverDataRequest.coordinateX(), roverDataRequest.coordinateY()));
        roverIntance.setOrientation(roverDataRequest.orientation());
        roverIntance.onRover();

        roverIntance = roverRepository.save(roverIntance);

        return new RoverDataResponse(roverIntance);
    }

    private void executeInitialValidations() {

        initialValidations.validations(
                new InitialData(
                        roverIntance,
                        mapNavigationService.getIntanceMap(),
                        isInitiated
                )
        );
    }
    private void executeInitialValidations(boolean isInitiated) {

        initialValidations.validations(
                new InitialData(
                        roverIntance,
                        mapNavigationService.getIntanceMap(),
                        isInitiated
                )
        );

        this.isInitiated = isInitiated;
    }

    @Override
    public String initialization() {

        //se ejecuta las validaciones de inicialización
        executeInitialValidations(true);
        controlCenter.loadItems(roverIntance, mapNavigationService.getIntanceMap());
        return "El Rover esta listo para explorar";
    }

    @Override
    public RoverDataResponse getRover() {
        return roverIntance.isActive()?
                new RoverDataResponse(roverIntance) :
                null;
    }

    @Override
    public void updateRoverCoordinates(Rover rover) {
       roverIntance = roverRepository.save(rover);
    }

    @Override
    public CoordinateDataResponse moveRover(String commands) {

        //se ejecuta las validaciones de inicialización
        executeInitialValidations();

        String[] split = commands.split(",");
        List<String> listCommands = Arrays.asList(split);

        return listCommands.stream()
                        .map(controlCenter::assingMovement)
                        .toList()
                        .get(listCommands.size() - 1);
    }

    @Override
    public String turnRover(String command) {

        executeInitialValidations();

        if(command.equals("R")) {
            return controlCenter.turn(
                        beforeOrientatation -> {
                            int newOrientation = beforeOrientatation.ordinal() + 1;
                            return newOrientation > (Orientation.values().length - 1) ?
                                        0 : newOrientation;
                        });
        }else{
            return controlCenter.turn(
                        beforeOrientatation -> {
                            int newOrientation = beforeOrientatation.ordinal() - 1;
                            return newOrientation < 0 ?
                                        (Orientation.values().length-1) :
                                         newOrientation;
                        });
        }
    }

    @Override
    public Rover getInstanceRover() {

        if(roverIntance == null) {
            roverIntance = roverRepository.findAll().stream()
                    .findFirst()
                    .orElseGet(Rover::new);
        }
        return roverIntance;
    }

}

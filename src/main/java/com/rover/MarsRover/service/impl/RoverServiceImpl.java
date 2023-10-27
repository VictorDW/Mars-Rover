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
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoverServiceImpl implements IRoverService {

    private final RoverRepository roverRepository;
    private final IMapNavigationService mapNavigationService;
    private final IControlCenter controlCenter;
    private final BehavioralValidations behavioralValidations;
    private final InitialValidations initialValidations;
    private Rover roverIntance;


    public RoverServiceImpl(RoverRepository roverRepository,
                            IMapNavigationService mapNavigationService,
                            BehavioralValidations behavioralValidations,
                            InitialValidations initialValidations) {

        this.roverRepository = roverRepository;
        this.mapNavigationService = mapNavigationService;
        this.controlCenter = new ControlCenterImpl(this, behavioralValidations);
        this.behavioralValidations = behavioralValidations;
        this.initialValidations = initialValidations;
        this.roverIntance = getInstanceRovert();
    }

    @Override
    public RoverDataResponse createRover(RoverDataRequest roverDataRequest) {

        //se ejecutan las validaciones de comportamiento
        behavioralValidations.validations(new CoordinatesData(
                                        roverDataRequest.coordinateX(),
                                        roverDataRequest.coordinateY(),
                                        mapNavigationService.getIntanceMap().getWidth(),
                                        mapNavigationService.getIntanceMap().getHeight())
        );

        roverIntance.setName(roverDataRequest.name());
        roverIntance.setPosition(new Position(roverDataRequest.coordinateX(), roverDataRequest.coordinateY()));
        roverIntance.setOrientation(roverDataRequest.orientation());
        roverIntance.onRover();

        roverIntance = roverRepository.save(roverIntance);

        return new RoverDataResponse(roverIntance);
    }

    @Override
    public String initialization() {

        //se ejecuta las validaciones de inicialización
        initialValidations.validations(new InitialData(roverIntance,  mapNavigationService.getIntanceMap()));

        controlCenter.loadItems(roverIntance, mapNavigationService.getIntanceMap());

        return "El Rover esta listo para explorar";
    }

    @Override
    public RoverDataResponse getAllRover() {

        /*Optional<Rover> rover = roverRepository.findAll().stream().findFirst();
        return   rover.map(RoverDataResponse::new).orElse(null);
         */
        return roverIntance.isActive()? new RoverDataResponse(roverIntance) : null;
    }

    @Override
    public void updateRoverPosition(Rover rover) {
        roverRepository.save(rover);
    }

    @Override
    public CoordinateDataResponse moveRover(String commands) {

        initialValidations.validations(new InitialData(roverIntance,  mapNavigationService.getIntanceMap()));

        String[] split = commands.split(",");
        List<String> listCommands = Arrays.asList(split);

        return listCommands.stream()
                        .map(controlCenter::assingMovement)
                        .toList()
                        .get(listCommands.size() - 1);
    }

    @Override
    public String turnRover(String command) {

        initialValidations.validations(new InitialData(roverIntance,  mapNavigationService.getIntanceMap()));

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
    public Rover getInstanceRovert() {

        if(roverIntance == null) {
            roverIntance = roverRepository.findAll().stream()
                    .findFirst()
                    .orElseGet(Rover::new);
        }
        return roverIntance;
    }


}

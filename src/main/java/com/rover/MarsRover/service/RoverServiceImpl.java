package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.model.Position;
import com.rover.MarsRover.model.Rover;
import com.rover.MarsRover.repository.RoverRepository;
import com.rover.MarsRover.validations.*;
import org.springframework.stereotype.Service;

@Service
public class RoverServiceImpl implements IRoverService{

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
        this.controlCenter = new ControlCenterImpl(this);
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
    public Rover getInstanceRovert() {

        if(roverIntance == null) {
            roverIntance = roverRepository.findAll().stream()
                    .findFirst()
                    .orElseGet(Rover::new);
        }
        return roverIntance;
    }


}

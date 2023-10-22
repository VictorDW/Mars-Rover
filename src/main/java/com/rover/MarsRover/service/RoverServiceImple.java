package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.model.Position;
import com.rover.MarsRover.model.Rover;
import com.rover.MarsRover.repository.RoverRepository;
import com.rover.MarsRover.validations.CoordinatesData;
import com.rover.MarsRover.validations.IValidations;
import org.springframework.stereotype.Service;

@Service
public class RoverServiceImple implements IRoverService{

    private final RoverRepository roverRepository;
    private final IMapNavigationService mapNavigationService;
    private final IControlCenter controlCenter;
    private final IValidations validations;
    private Rover roverIntance;


    public RoverServiceImple(RoverRepository roverRepository,
                                         IMapNavigationService mapNavigationService,
                                         IValidations validations) {

        this.roverRepository = roverRepository;
        this.mapNavigationService = mapNavigationService;
        this.controlCenter = new ControlCenterImple(this);
        this.validations = validations;
        this.roverIntance = getInstanceRovert();
    }

    @Override
    public RoverDataResponse createRover(RoverDataRequest roverDataRequest) {

        validations.validations(new CoordinatesData(
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

        controlCenter.loadItems(roverIntance, mapNavigationService.getIntanceMap());
        return "funciona";
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

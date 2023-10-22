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
    private final IValidations validations;
    private Rover roverIntance;


    public RoverServiceImple(RoverRepository roverRepository,
                             IMapNavigationService mapNavigationService,
                            IValidations validations) {

        this.roverRepository = roverRepository;
        this.mapNavigationService = mapNavigationService;
        this.validations = validations;
        this.roverIntance = getInstanceRovert();
    }

    @Override
    public RoverDataResponse createRover(RoverDataRequest roverDataRequest) {

        validations.Validations(new CoordinatesData(
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
    public Rover getInstanceRovert() {

        if(roverIntance == null) {
            roverIntance = roverRepository.findAll().stream()
                    .findFirst()
                    .orElseGet(Rover::new);
        }
        return roverIntance;
    }


}

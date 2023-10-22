package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.model.Position;
import com.rover.MarsRover.model.Rover;
import com.rover.MarsRover.repository.RoverRepository;
import org.springframework.stereotype.Service;

@Service
public class RoverServiceImple implements IRoverService{

    private final RoverRepository roverRepository;
    private final IMapNavigationService mapNavigationService;
    private Rover roverIntance;


    public RoverServiceImple(RoverRepository roverRepository,
                             IMapNavigationService mapNavigationService) {

        this.roverRepository = roverRepository;
        this.mapNavigationService = mapNavigationService;
        this.roverIntance = getInstanceRovert();
    }

    @Override
    public RoverDataResponse createRover(RoverDataRequest roverDataRequest) {

        roverIntance.setName(roverDataRequest.name());
        roverIntance.setPosition(new Position(roverDataRequest.coordinateX(), roverDataRequest.coordinateY()));
        roverIntance.setOrientation(roverDataRequest.orientation());
        roverIntance.onRover();

        roverIntance = roverRepository.save(roverIntance);
        System.out.println(roverIntance);

        return new RoverDataResponse(roverIntance);
    }

    @Override
    public Rover getInstanceRovert() {

        if(roverIntance == null) {
            roverIntance = roverRepository.findAll().stream()
                    .findFirst()
                    .orElseGet(Rover::new);
        }
        System.out.println(roverIntance);
        return roverIntance;
    }


}

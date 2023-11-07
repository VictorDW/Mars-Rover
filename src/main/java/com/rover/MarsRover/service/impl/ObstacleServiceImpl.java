package com.rover.MarsRover.service.impl;

import com.rover.MarsRover.DTO.request.ObstacleDataRequest;
import com.rover.MarsRover.DTO.response.ObstacleDataResponse;
import com.rover.MarsRover.model.Obstacle;
import com.rover.MarsRover.model.Position;
import com.rover.MarsRover.repository.ObstacleRepository;
import com.rover.MarsRover.service.AbstractObstacleService;
import com.rover.MarsRover.service.IMapNavigationService;
import com.rover.MarsRover.service.IObstacleService;
import com.rover.MarsRover.validations.BehavioralValidations;
import com.rover.MarsRover.validations.DTO.CoordinatesData;
import com.rover.MarsRover.validations.InitialValidations;
import com.rover.MarsRover.validations.IntegrityValidation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ObstacleServiceImpl extends AbstractObstacleService implements IObstacleService {

    private  final ObstacleRepository obstacleRepository;
    private final IMapNavigationService mapNavigationService;
    private final BehavioralValidations behavioralValidations;
    private final IntegrityValidation integrityValidation;

    public ObstacleServiceImpl(ObstacleRepository obstacleRepository,
                               IMapNavigationService mapNavigationService,
                               @Qualifier("ObstacleValidation")
                               BehavioralValidations behavioralValidations,
                               IntegrityValidation integrityValidation) {

        this.obstacleRepository = obstacleRepository;
        this.mapNavigationService = mapNavigationService;
        this.behavioralValidations = behavioralValidations;
        this.integrityValidation = integrityValidation;
        obstacleRepository.findAll().forEach(this::loadObstacle);
    }

    @Override
    public ObstacleDataResponse createObstacle(ObstacleDataRequest obstacleDataRequest) {

        //validación de inicialización
        InitialValidations.isMapActive(mapNavigationService.getIntanceMap());

        //validaciones de comportamiento
        behavioralValidations.validations(
                new CoordinatesData(
                        obstacleDataRequest.coordinateX(),
                        obstacleDataRequest.coordinateY(),
                        mapNavigationService.getIntanceMap().getWidth(),
                        mapNavigationService.getIntanceMap().getHeight()
                )
        );

        Obstacle obstacle = new Obstacle();

        obstacle.setPosition(
                    new Position(
                            obstacleDataRequest.coordinateX(),
                            obstacleDataRequest.coordinateY()
                    )
        );

        obstacle = obstacleRepository.save(obstacle);
        loadObstacle(obstacle);

        return new ObstacleDataResponse(obstacle);
    }

    @Override
    public List<ObstacleDataResponse> getAllObstacles() {
        return obstacleRepository.findAll().stream().map(ObstacleDataResponse::new).toList();
    }

    @Override
    public ObstacleDataResponse updateObstacle(Long id, ObstacleDataRequest obstacleDataRequest) {

        //Validaciones de comportamiento
        behavioralValidations.validations(
                new CoordinatesData(
                        obstacleDataRequest.coordinateX(),
                        obstacleDataRequest.coordinateY(),
                        mapNavigationService.getIntanceMap().getWidth(),
                        mapNavigationService.getIntanceMap().getHeight()
                )
        );

        Optional<Obstacle> newObstacle = obstacleRepository.findById(id).map(
                obstacle -> {
                        obstacle.setPositionX(obstacleDataRequest.coordinateX());
                        obstacle.setPositionY(obstacleDataRequest.coordinateY());
                        return obstacle;
                });

        //Validación de integridad
        integrityValidation.validations(newObstacle);

        updateObstacleOfMap(newObstacle.get());

        return new ObstacleDataResponse(
                obstacleRepository.save(
                        newObstacle.get()
                )
        );
    }

    @Override
    public void deleteObstacle(Long id) {

        Optional<Obstacle> obstacleRemoved = obstacleRepository.findById(id).map(
                obstacle -> {
                    obstacleRepository.delete(obstacle);
                    return obstacle;
                });

        //Validación de integridad
        integrityValidation.validations(obstacleRemoved);

        removeObstacleOfMap(obstacleRemoved.get());

    }

    @Override
    public void deleteAllObstacle() {

        obstacleRepository.findAll().forEach(
                obstacle -> {
                    obstacleRepository.delete(obstacle);
                    removeObstacleOfMap(obstacle);
                }
        );
    }
}

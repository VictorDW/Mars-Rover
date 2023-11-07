package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.request.ObstacleDataRequest;
import com.rover.MarsRover.DTO.response.ObstacleDataResponse;

import java.util.List;

public interface IObstacleService {

    ObstacleDataResponse createObstacle(ObstacleDataRequest obstacleDataRequest);
    List<ObstacleDataResponse> getAllObstacles();
    ObstacleDataResponse updateObstacle(Long id, ObstacleDataRequest obstacleDataRequest);
    void deleteObstacle(Long id);
    void deleteAllObstacle();

}

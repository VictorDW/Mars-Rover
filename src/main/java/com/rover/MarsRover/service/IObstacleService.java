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

   /* default void updateObstacleOfList(List<Obstacle> obstacles, Obstacle newObstacle) {
        obstacles.forEach(
                obstacle -> {
                    if (obstacle.equals(newObstacle)) {
                        //obstacles.set(obstacles.indexOf(obstacle), afterObstacle);
                            obstacle.setPosition(newObstacle.getPosition());
                    }
                }
        );
    }
    default void removeObstacleOfList(List<Obstacle> obstacles, Obstacle obstacleToRemoved) {
        boolean isRemove = obstacles.removeIf(obstacle -> obstacle.equals(obstacleToRemoved));
        System.out.println(isRemove);
    }

    */

}

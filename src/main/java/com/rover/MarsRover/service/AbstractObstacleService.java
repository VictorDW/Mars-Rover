package com.rover.MarsRover.service;

import com.rover.MarsRover.model.Obstacle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractObstacleService {
    private static final Map<Long, Obstacle> mapObstacles = new HashMap<>();

    protected void loadObstacle(Obstacle obstacle) {
        mapObstacles.put(obstacle.getId(),obstacle);
    }
    protected void updateObstacleOfMap(Obstacle newObstacle) {

        mapObstacles.get(newObstacle.getId())
                .setPosition(newObstacle.getPosition());
    }
    protected void removeObstacleOfMap(Obstacle obstacleRemoved) {
        mapObstacles.remove(obstacleRemoved.getId());
    }

    public static List<Obstacle> obstacles() {
        return mapObstacles.values().stream().toList();
    }

}

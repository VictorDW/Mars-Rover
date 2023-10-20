package com.rover.MarsRover.controller;

import com.rover.MarsRover.DTO.ObstacleDataRequest;
import com.rover.MarsRover.model.Obstacle;
import com.rover.MarsRover.model.Position;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/obstacle")
public class ObstacleController {

    @PostMapping
    public String createObstacle(@RequestBody ObstacleDataRequest obstacleDataRequest) {

        Obstacle obstacle = new Obstacle();
        obstacle.setPosition(new Position(obstacleDataRequest.coordinateX(), obstacleDataRequest.coordinateY()));

        return obstacle.toString();
    }

}

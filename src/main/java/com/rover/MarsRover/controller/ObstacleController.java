package com.rover.MarsRover.controller;

import com.rover.MarsRover.DTO.request.ObstacleDataRequest;
import com.rover.MarsRover.DTO.response.ObstacleDataResponse;
import com.rover.MarsRover.service.IObstacleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/obstacle")
public class ObstacleController {

    private final IObstacleService obstacleService;

    @PostMapping
    public ResponseEntity<ObstacleDataResponse> createObstacle(@RequestBody ObstacleDataRequest obstacleDataRequest) {
        return new ResponseEntity<>(obstacleService.createObstacle(obstacleDataRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ObstacleDataResponse>> getAllObstacle() {
        return ResponseEntity.ok(obstacleService.getAllObstacles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObstacleDataResponse> updateObstacles(@PathVariable Long id, @RequestBody ObstacleDataRequest obstacleDataRequest) {
        return ResponseEntity.ok(obstacleService.updateObstacle(id, obstacleDataRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpHeaders> deletedObstacle(@PathVariable Long id) {
        obstacleService.deleteObstacle(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    public ResponseEntity<HttpHeaders> deletedAllObstacle() {
        obstacleService.deleteAllObstacle();
        return ResponseEntity.noContent().build();
    }

}

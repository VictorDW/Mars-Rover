package com.rover.MarsRover.controller;


import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.service.IRoverService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/rover")
public class RoverController {

    private IRoverService roverService;

    @PostMapping
    public ResponseEntity<RoverDataResponse> createRover(@RequestBody RoverDataRequest roverDataRequest) {
        return ResponseEntity.ok().body(roverService.createRover(roverDataRequest));
    }

    @PostMapping("/initialization")
    public ResponseEntity<String> initialization() {
        return ResponseEntity.ok().body(roverService.initialization());
    }

    @GetMapping
    public ResponseEntity<RoverDataResponse> getAllRover() {
        return ResponseEntity.ok().body(roverService.getAllRover());
    }

    @PostMapping("/move/{commands}")
    public ResponseEntity<CoordinateDataResponse> moveRover(@PathVariable String commands) {
        return ResponseEntity.ok().body(roverService.moveRover(commands));
    }

    @PostMapping("/turn/{orientation}")
    public ResponseEntity<String> turnRover(@PathVariable String orientation) {
        return ResponseEntity.ok().body(roverService.turnRover(orientation));
    }
}

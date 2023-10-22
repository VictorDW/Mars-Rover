package com.rover.MarsRover.controller;


import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.service.IRoverService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/rover")
public class RoverController {

    private IRoverService roverService;

    @PostMapping
    public ResponseEntity<RoverDataResponse> createRover(@RequestBody RoverDataRequest roverDataRequest) {
        return ResponseEntity.ok().body(roverService.createRover(roverDataRequest));
    }
}

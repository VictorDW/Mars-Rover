package com.rover.MarsRover.controller;


import com.rover.MarsRover.DTO.request.RoverDataRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rover")
public class RoverController {


    @PostMapping
    public String createRover(@RequestBody RoverDataRequest roverDataRequest) {

        return null;
    }
}

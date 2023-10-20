package com.rover.MarsRover.controller;

import com.rover.MarsRover.DTO.MapDataRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/map")
public class MapNavigationController {

    @PostMapping
    public String createMap(@RequestBody MapDataRequest mapDataRequest) {

        return null;
    }
}

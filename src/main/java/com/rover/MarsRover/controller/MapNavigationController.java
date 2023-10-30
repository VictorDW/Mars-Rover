package com.rover.MarsRover.controller;

import com.rover.MarsRover.DTO.request.MapDataRequest;
import com.rover.MarsRover.DTO.response.MapDataResponse;
import com.rover.MarsRover.service.IMapNavigationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/map")
@AllArgsConstructor
public class MapNavigationController {

    private IMapNavigationService mapNavigationService;

    @PostMapping
    public ResponseEntity<MapDataResponse> createMap(@RequestBody MapDataRequest mapDataRequest) {
        return ResponseEntity.ok().body(mapNavigationService.createMap(mapDataRequest));
    }

    @GetMapping
    public ResponseEntity<MapDataResponse> getAllMap() {
        return ResponseEntity.ok().body(mapNavigationService.getMap());
    }
}

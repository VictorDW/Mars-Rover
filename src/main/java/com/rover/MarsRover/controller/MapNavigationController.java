package com.rover.MarsRover.controller;

import com.rover.MarsRover.DTO.request.MapDataRequest;
import com.rover.MarsRover.DTO.response.MapDataResponse;
import com.rover.MarsRover.service.IMapNavigationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/map")
@AllArgsConstructor
public class MapNavigationController {

    private IMapNavigationService mapNavigationService;

    @PostMapping
    public ResponseEntity<MapDataResponse> createMap(@Valid @RequestBody MapDataRequest mapDataRequest) {
        return new ResponseEntity<>(mapNavigationService.createMap(mapDataRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MapDataResponse> getMap() {
        return ResponseEntity.ok(mapNavigationService.getMap());
    }
}

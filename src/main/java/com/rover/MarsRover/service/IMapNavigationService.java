package com.rover.MarsRover.service;

import com.rover.MarsRover.DTO.request.MapDataRequest;
import com.rover.MarsRover.DTO.response.MapDataResponse;
import com.rover.MarsRover.model.Map;

public interface IMapNavigationService {
    MapDataResponse createMap(MapDataRequest mapDataRequest);
    MapDataResponse getMap();
    Map getIntanceMap();
}

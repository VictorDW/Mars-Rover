package com.rover.MarsRover.service.impl;

import com.rover.MarsRover.DTO.request.MapDataRequest;
import com.rover.MarsRover.DTO.response.MapDataResponse;
import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.repository.MapRepository;
import com.rover.MarsRover.service.IMapNavigationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapNavigationServiceImpl implements IMapNavigationService {

    private final MapRepository mapRepository;
    private Map mapInstance;


    public MapNavigationServiceImpl(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
        this.mapInstance = getIntanceMap();
    }

    @Override
    public MapDataResponse createMap(MapDataRequest mapDataRequest) {

        mapInstance.setWidth(mapDataRequest.width());
        mapInstance.setHeight(mapDataRequest.height());
        mapInstance.onLoad();

        mapInstance = mapRepository.save(mapInstance);

        return new MapDataResponse(mapInstance);
    }

    @Override
    public MapDataResponse getAllMap() {

        Optional<Map> mapNavigation = mapRepository.findAll().stream().findFirst();
        return mapNavigation.map(MapDataResponse::new).orElse(null);
    }

    public Map getIntanceMap() {

        if (mapInstance == null ) {
               mapInstance = mapRepository.findAll().stream()
                        .findFirst()
                        .orElseGet(Map::new);
        }
        return mapInstance;
    }
}

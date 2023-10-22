package com.rover.MarsRover.DTO.response;

import com.rover.MarsRover.model.Map;

public record MapDataResponse(
        Long id,
        Integer width,
        Integer height
) {
    public MapDataResponse(Map mapInstance) {
        this(mapInstance.getId(),
                mapInstance.getWidth(),
                mapInstance.getWidth());
    }
}

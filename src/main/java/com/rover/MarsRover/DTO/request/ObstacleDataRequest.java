package com.rover.MarsRover.DTO.request;

import jakarta.validation.constraints.NotNull;

public record ObstacleDataRequest(
    @NotNull(message = "{coordinateX.required}")
    Integer coordinateX,
    @NotNull(message = "{coordinateY.required}")
    Integer coordinateY
) { }

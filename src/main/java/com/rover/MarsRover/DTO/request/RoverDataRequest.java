package com.rover.MarsRover.DTO.request;

import com.rover.MarsRover.model.Orientation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoverDataRequest(
        @NotEmpty(message = "{name.required}")
        @Size(min = 3, message = "{name.invalid}")
        String name,
        @NotNull(message = "{coordinateX.required}")
        Integer coordinateX,
        @NotNull(message = "{coordinateY.required}")
        Integer coordinateY,
        @NotNull(message = "{orientation.required}")
        Orientation orientation
) { }

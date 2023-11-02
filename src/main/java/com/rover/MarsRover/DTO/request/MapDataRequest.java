package com.rover.MarsRover.DTO.request;

import jakarta.validation.constraints.NotNull;

public record MapDataRequest(
        @NotNull(message="{width.required}")
        Integer width,
        @NotNull(message="{height.required}")
        Integer height
) { }

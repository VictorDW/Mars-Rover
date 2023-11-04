package com.rover.MarsRover.infra.errors;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime localDateTime,
         int status,
         String error,
         String messaje,
         String path
) { }

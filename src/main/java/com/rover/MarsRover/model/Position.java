package com.rover.MarsRover.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Position {
    private Integer coordinateX;
    private Integer coordinateY;
}

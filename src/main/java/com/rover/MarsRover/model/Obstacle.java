package com.rover.MarsRover.model;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Obstacle")
@Table(name = "obstacle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Obstacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Position position;

    public void setPositionX(Integer x) {
        position.setCoordinateX(x);
    }
    public void setPositionY(Integer y) {
        position.setCoordinateY(y);
    }
}

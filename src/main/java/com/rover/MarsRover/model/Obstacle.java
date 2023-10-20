package com.rover.MarsRover.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Obstacle")
@Table(name = "obstacle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Obstacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Embedded
    private Position position;
}

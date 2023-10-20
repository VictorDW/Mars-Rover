package com.rover.MarsRover.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Rover")
@Table(name = "rover")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Position position;
    @Enumerated(EnumType.STRING)
    private Orientation orientation;
    private Boolean active = false;

     public void onRover() {
         this.active = true;
     }

     public boolean isActive() {
         return active;
     }
}

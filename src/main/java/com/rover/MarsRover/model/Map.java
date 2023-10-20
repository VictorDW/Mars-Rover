package com.rover.MarsRover.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Map")
@Table(name = "map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer width;
    private Integer height;
    private Boolean active = false;

    public void onLoad() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }
}

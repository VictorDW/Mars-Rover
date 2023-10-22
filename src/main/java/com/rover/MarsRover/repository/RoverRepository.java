package com.rover.MarsRover.repository;

import com.rover.MarsRover.model.Rover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoverRepository extends JpaRepository<Rover, Long> {
}

package com.rover.MarsRover.model;

public enum Orientation {
    N,
    E,
    S,
    W;

    public static Orientation getByPosition(Integer value) {
        return values()[value];
    }
}

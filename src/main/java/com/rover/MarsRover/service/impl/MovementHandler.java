package com.rover.MarsRover.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public final class MovementHandler {

    private final Map<String, BiFunction<Integer, Integer, List<Integer>>> functionalities = new HashMap<>();
    private static MovementHandler instance;
    private MovementHandler() {
        movementInitialization();
    }

    private  void movementInitialization() {

        functionalities.put("NF", (x, y) -> List.of(x, y-1));//SB
        functionalities.put("NB", (x, y) -> List.of(x, y+1));//SF
        functionalities.put("EF", (x, y) -> List.of(x+1, y)); //WB
        functionalities.put("EB", (x, y) -> List.of(x-1, y)); //WF
        functionalities.put("SF", (x, y) -> functionalities.get("NB").apply(x,y));
        functionalities.put("SB", (x, y) -> functionalities.get("NF").apply(x,y));
        functionalities.put("WF", (x, y) -> functionalities.get("EB").apply(x,y));
        functionalities.put("WB", (x, y) -> functionalities.get("EF").apply(x,y));
    }

    public static BiFunction<Integer, Integer, List<Integer>> getMovement(String key) {

        if (instance == null ) {
            instance = new MovementHandler();
        }
        return instance.functionalities.get(key);
    }
}

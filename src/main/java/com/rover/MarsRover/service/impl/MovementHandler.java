package com.rover.MarsRover.service.impl;

import com.rover.MarsRover.model.Orientation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.ToIntFunction;

public final class MovementHandler {

    private final Map<String, BiFunction<Integer, Integer, List<Integer>>> functionalities = new HashMap<>();
    private final Map<String, ToIntFunction<Orientation>> functionalitiesTurn = new HashMap<>();
    private static MovementHandler instance;
    private MovementHandler() {
        movementInitialization();
        turnInitialization();
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

    private  void turnInitialization() {

        functionalitiesTurn.put("R",
                beforeOrientatation -> {
                    int newOrientation = beforeOrientatation.ordinal() + 1;
                    return newOrientation > (Orientation.values().length - 1) ?
                            0 : newOrientation;
                });

        functionalitiesTurn.put("L",
                beforeOrientatation -> {
                    int newOrientation = beforeOrientatation.ordinal() - 1;
                    return newOrientation < 0 ?
                            (Orientation.values().length-1) :
                            newOrientation;
                });
    }

    public static BiFunction<Integer, Integer, List<Integer>> getMovement(String key) {
        return getInstance().functionalities.get(key);
    }

    public static ToIntFunction<Orientation> getTurn(String key) {
        return getInstance().functionalitiesTurn.get(key);
    }

    private static MovementHandler getInstance() {
        if (instance == null ) {
            instance = new MovementHandler();
        }
        return instance;
    }
}

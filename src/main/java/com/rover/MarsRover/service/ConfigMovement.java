package com.rover.MarsRover.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public final class ConfigMovement {

    private static final Map<String, BiFunction<Integer, Integer, List<Integer>>> movementFunctionalities = new HashMap<>();

    private ConfigMovement() {}

    public static void functionInitialization() {
        movementFunctionalities.put("NF", (x, y) -> List.of(x, y-1));//SB
        movementFunctionalities.put("NB", (x, y) -> List.of(x, y+1));//SF
        movementFunctionalities.put("EF", (x, y) -> List.of(x+1, y)); //WB
        movementFunctionalities.put("EB", (x, y) -> List.of(x-1, y)); //WF
        movementFunctionalities.put("SF", (x, y) -> movementFunctionalities.get("NB").apply(x,y));
        movementFunctionalities.put("SB", (x, y) -> movementFunctionalities.get("NF").apply(x,y));
        movementFunctionalities.put("WF", (x, y) -> movementFunctionalities.get("EB").apply(x,y));
        movementFunctionalities.put("WB", (x, y) -> movementFunctionalities.get("EF").apply(x,y));
    }

    public static BiFunction<Integer, Integer, List<Integer>> getMovement(String key) {
        return movementFunctionalities.get(key);
    }

}

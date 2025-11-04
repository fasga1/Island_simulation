package island.utils;

import java.util.Map;
import java.util.HashMap;

public class AnimalData {
    public static final Map<String, Map<String, Integer>> EATING_PROBABILITIES = new HashMap<>();

    static {
        EATING_PROBABILITIES.put("Wolf", Map.of(
                "Horse", 10,
                "Deer", 15,
                "Rabbit", 60,
                "Mouse", 80,
                "Goat", 60,
                "Sheep", 70,
                "Boar", 15,
                "Buffalo", 10,
                "Duck", 40
        ));

    }

}
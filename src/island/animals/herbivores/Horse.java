package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Horse extends Herbivore {
    public Horse(Island island, int x, int y) {
        super(400.0, 20, 4, 60.0, "🐎", island, x, y);
    }
}
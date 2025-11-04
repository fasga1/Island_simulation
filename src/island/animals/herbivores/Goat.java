package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Goat extends Herbivore {
    public Goat(Island island, int x, int y) {
        super(60.0, 140, 3, 10.0, "🐐", island, x, y);
    }
}
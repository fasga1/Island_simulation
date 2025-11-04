package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Caterpillar extends Herbivore {
    public Caterpillar(Island island, int x, int y) {
        super(0.01, 1000, 0, 0.0, "🐛", island, x, y);
    }
}
package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Buffalo extends Herbivore {
    public Buffalo(Island island, int x, int y) {
        super(700.0, 10, 3, 100.0, "🐃", island, x, y);
    }
}
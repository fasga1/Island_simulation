package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Mouse extends Herbivore {
    public Mouse(Island island, int x, int y) {
        super(0.05, 500, 1, 0.01, "🐁", island, x, y);
    }
}
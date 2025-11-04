package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Sheep extends Herbivore {
    public Sheep(Island island, int x, int y) {
        super(70.0, 140, 3, 15.0, "🐑", island, x, y);
    }
}
package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Rabbit extends Herbivore {
    public Rabbit(Island island, int x, int y) {
        super(2.0, 150, 2, 0.45, "🐇", island, x, y);
    }
}
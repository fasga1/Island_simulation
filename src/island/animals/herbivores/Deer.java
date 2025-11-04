package island.animals.herbivores;

import island.entities.Herbivore;
import island.model.Island;

public class Deer extends Herbivore {
    public Deer(Island island, int x, int y) {
        super(300.0, 20, 4, 50.0, "🦌", island, x, y);
    }
}
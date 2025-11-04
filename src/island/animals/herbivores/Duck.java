package island.animals.herbivores;

import island.entities.Animal;
import island.entities.Herbivore;
import island.model.Island;

public class Duck extends Herbivore {
    public Duck(Island island, int x, int y) {
        super(1.0, 200, 4, 0.15, "🦆", island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return other instanceof Caterpillar;
    }

    @Override
    public double getEatingProbability(Animal other) {
        if (other instanceof Caterpillar) return 90;
        return 0;
    }
}
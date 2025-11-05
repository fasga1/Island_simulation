package island.animals.predators;

import island.animals.herbivores.*;
import island.entities.Animal;
import island.entities.Predator;
import island.model.Island;

public class Bear extends Predator {
    public Bear(Island island, int x, int y) {
        super(500.0, 5, 2, 80.0, "🐻", island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return other instanceof Boa || other instanceof Horse || other instanceof Deer ||
                other instanceof Rabbit || other instanceof Mouse || other instanceof Goat ||
                other instanceof Sheep || other instanceof Boar || other instanceof Buffalo ||
                other instanceof Duck;
    }

    @Override
    public double getEatingProbability(Animal other) {
        if (other instanceof Boa) return 80;
        if (other instanceof Horse) return 40;
        if (other instanceof Deer) return 80;
        if (other instanceof Rabbit) return 80;
        if (other instanceof Mouse) return 90;
        if (other instanceof Goat) return 70;
        if (other instanceof Sheep) return 70;
        if (other instanceof Boar) return 50;
        if (other instanceof Buffalo) return 20;
        if (other instanceof Duck) return 10;
        return 0;
    }
}
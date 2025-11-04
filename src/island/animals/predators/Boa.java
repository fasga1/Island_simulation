package island.animals.predators;

import island.animals.herbivores.*;
import island.animals.predators.Fox;
import island.entities.Animal;
import island.entities.Predator;
import island.model.Island;

public class Boa extends Predator {
    public Boa(Island island, int x, int y) {
        super(15.0, 30, 1, 3.0, "🐍", island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return other instanceof Fox || other instanceof Rabbit ||
                other instanceof Mouse || other instanceof Duck;
    }

    @Override
    public double getEatingProbability(Animal other) {
        if (other instanceof Fox) return 15;
        if (other instanceof Rabbit) return 20;
        if (other instanceof Mouse) return 40;
        if (other instanceof Duck) return 10;
        return 0;
    }

    @Override public void eat() {}
    @Override public void reproduce() {}
    @Override public void move() {}
}
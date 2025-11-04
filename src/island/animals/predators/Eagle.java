package island.animals.predators;

import island.animals.herbivores.*;
import island.animals.predators.Boa;
import island.entities.Animal;
import island.entities.Predator;
import island.model.Island;

public class Eagle extends Predator {
    public Eagle(Island island, int x, int y) {
        super(6.0, 20, 3, 1.0, "🦅", island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return other instanceof Boa || other instanceof Rabbit ||
                other instanceof Mouse || other instanceof Duck;
    }

    @Override
    public double getEatingProbability(Animal other) {
        if (other instanceof Boa) return 10;
        if (other instanceof Rabbit) return 90;
        if (other instanceof Mouse) return 90;
        if (other instanceof Duck) return 80;
        return 0;
    }

    @Override public void eat() {}
    @Override public void reproduce() {}
    @Override public void move() {}
}
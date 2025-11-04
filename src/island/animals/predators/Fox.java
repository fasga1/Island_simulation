package island.animals.predators;

import island.animals.herbivores.*;
import island.entities.Animal;
import island.entities.Predator;
import island.model.Island;

public class Fox extends Predator {
    public Fox(Island island, int x, int y) {
        super(8.0, 30, 2, 2.0, "🦊", island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return other instanceof Rabbit || other instanceof Mouse ||
                other instanceof Duck || other instanceof Caterpillar;
    }

    @Override
    public double getEatingProbability(Animal other) {
        if (other instanceof Rabbit) return 70;
        if (other instanceof Mouse) return 90;
        if (other instanceof Duck) return 60;
        if (other instanceof Caterpillar) return 40;
        return 0;
    }

    @Override public void eat() {}
    @Override public void reproduce() {}
    @Override public void move() {}
}
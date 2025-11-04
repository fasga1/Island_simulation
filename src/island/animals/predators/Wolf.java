package island.animals.predators;

import island.animals.herbivores.*;
import island.entities.Animal;
import island.entities.Predator;
import island.model.Island;

public class Wolf extends Predator {
    public Wolf(Island island, int x, int y) {
        super(50.0, 30, 3, 8.0, "🐺", island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return other instanceof Horse || other instanceof Deer || other instanceof Rabbit ||
                other instanceof Mouse || other instanceof Goat || other instanceof Sheep ||
                other instanceof Boar || other instanceof Buffalo || other instanceof Duck;
    }

    @Override
    public double getEatingProbability(Animal other) {
        if (other instanceof Horse) return 10;
        if (other instanceof Deer) return 15;
        if (other instanceof Rabbit) return 60;
        if (other instanceof Mouse) return 80;
        if (other instanceof Goat) return 60;
        if (other instanceof Sheep) return 70;
        if (other instanceof Boar) return 15;
        if (other instanceof Buffalo) return 10;
        if (other instanceof Duck) return 40;
        return 0;
    }

    @Override public void eat() {}
    @Override public void reproduce() {}
    @Override public void move() {}
}
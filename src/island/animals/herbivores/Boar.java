package island.animals.herbivores;

import island.entities.Animal;
import island.entities.Herbivore;
import island.model.Island;

public class Boar extends Herbivore {
    public Boar(Island island, int x, int y) {
        super(400.0, 50, 2, 50.0, "🐗", island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return other instanceof Mouse || other instanceof Caterpillar;
    }

    @Override
    public double getEatingProbability(Animal other) {
        if (other instanceof Mouse) return 50;
        if (other instanceof Caterpillar) return 90;
        return 0;
    }
}
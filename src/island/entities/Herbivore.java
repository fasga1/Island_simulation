package island.entities;

import island.model.Island;
import island.model.Cell;
import island.animals.herbivores.Caterpillar;
import island.animals.herbivores.Mouse;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxOnCell, int speed, double foodRequirement, String emoji, Island island, int x, int y) {
        super(weight, maxOnCell, speed, foodRequirement, emoji, island, x, y);
    }

    @Override
    public boolean canEat(Animal other) {
        return false;
    }

    @Override
    public double getEatingProbability(Animal other) {
        return 0;
    }

    @Override
    public void eat() {
        Cell cell = island.getCell(x, y);
        boolean ate = false;

        if (cell.consumePlant()) {
            resetHunger();
            ate = true;
        }

        if (!ate) {
            if (this instanceof island.animals.herbivores.Duck) {
                ate = tryEatSpecific(cell, Caterpillar.class, 90);
            } else if (this instanceof island.animals.herbivores.Boar) {
                if (!tryEatSpecific(cell, Mouse.class, 50)) {
                    ate = tryEatSpecific(cell, Caterpillar.class, 90);
                } else {
                    ate = true;
                }
            }
        }

        if (!ate) {
            increaseHunger();
        }
    }

    protected boolean tryEatSpecific(Cell cell, Class<? extends Animal> preyType, double probability) {
        List<Animal> preyList = cell.getAnimalsByType(preyType);
        if (!preyList.isEmpty()) {
            Animal target = preyList.get(random.nextInt(preyList.size()));
            if (chance(probability)) {
                cell.removeAnimal(target);
                resetHunger();
                return true;
            }
        }
        return false;
    }
}
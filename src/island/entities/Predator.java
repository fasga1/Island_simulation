package island.entities;

import island.model.Island;
import island.model.Cell;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxOnCell, int speed, double foodRequirement, String emoji, Island island, int x, int y) {
        super(weight, maxOnCell, speed, foodRequirement, emoji, island, x, y);
    }
    
    @Override
    public void eat() {
        Cell cell = island.getCell(x, y);

        List<Animal> allAnimals = cell.getAllAnimals().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<Animal> potentialPrey = allAnimals.stream()
                .filter(this::canEat)
                .collect(Collectors.toList());

        boolean ate = false;

        if (!potentialPrey.isEmpty()) {
            Animal prey = potentialPrey.get(random.nextInt(potentialPrey.size()));
            double probability = getEatingProbability(prey);

            if (chance(probability)) {
                cell.removeAnimal(prey);
                resetHunger();
                ate = true;
            }
        }

        if (!ate) {
            increaseHunger();
        }
    }
}
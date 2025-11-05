package island.model;

import island.entities.Animal;
import island.entities.Plant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Map;
import java.util.List;

public class Cell {
    private volatile int plantCount = Plant.getMaxOnCell();

    private final Map<Class<? extends Animal>, List<Animal>> animals = new ConcurrentHashMap<>();

    public Cell() {
        this.plantCount = 100;
    }



    public synchronized void addPlants(int amount) {
        this.plantCount = Math.min(Plant.getMaxOnCell(), this.plantCount + amount);
    }

    public synchronized boolean consumePlant() {
        if (plantCount > 0) {
            plantCount--;
            return true;
        }
        return false;
    }

    public int getPlantCount() {
        return plantCount;
    }



    public void addAnimal(Animal animal) {
        Class<? extends Animal> type = animal.getClass();

        int currentCount = getAnimalCount(type);
        if (currentCount >= animal.getMaxOnCell()) {
            return;
        }

        animals.computeIfAbsent(type, k -> new CopyOnWriteArrayList<>()).add(animal);
    }

    public boolean removeAnimal(Animal animal) {
        List<Animal> list = animals.get(animal.getClass());
        if (list != null) {
            return list.remove(animal);
        }
        return false;
    }

    public List<Animal> getAnimalsByType(Class<? extends Animal> type) {
        return animals.getOrDefault(type, List.of());
    }

    public int getAnimalCount(Class<? extends Animal> type) {
        return getAnimalsByType(type).size();
    }

    public Map<Class<? extends Animal>, List<Animal>> getAllAnimals() {
        return Map.copyOf(animals);
    }

    public int getTotalAnimals() {
        return animals.values().stream().mapToInt(List::size).sum();
    }
}
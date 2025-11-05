package island.model;

import island.animals.herbivores.*;
import island.animals.predators.*;
import island.entities.Animal;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;

    private final Cell[][] cells;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public Island() {
        cells = new Cell[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                cells[y][x] = new Cell();
            }
        }

        initializeInitialPopulation();
    }

    @FunctionalInterface
    private interface AnimalFactory {
        Animal create(Island island, int x, int y);
    }

    private void initializeInitialPopulation() {
        addRandomAnimals(Wolf::new, 10);
        addRandomAnimals(Boa::new, 10);
        addRandomAnimals(Fox::new, 10);
        addRandomAnimals(Bear::new, 10);
        addRandomAnimals(Eagle::new, 10);

        addRandomAnimals(Horse::new, 10);
        addRandomAnimals(Deer::new, 10);
        addRandomAnimals(Rabbit::new, 10);
        addRandomAnimals(Mouse::new, 10);
        addRandomAnimals(Goat::new, 10);
        addRandomAnimals(Sheep::new, 10);
        addRandomAnimals(Boar::new, 10);
        addRandomAnimals(Buffalo::new, 10);
        addRandomAnimals(Duck::new, 10);
        addRandomAnimals(Caterpillar::new, 10);
    }

    private void addRandomAnimals(AnimalFactory factory, int count) {
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            Animal animal = factory.create(this, x, y);
            cells[y][x].addAnimal(animal);
        }
    }
    public Cell getCell(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return null;
        }
        return cells[y][x];
    }

    public int[] getRandomNeighborCoords(int x, int y, int maxDistance) {
        if (maxDistance <= 0) {
            return new int[]{x, y};
        }

        int attempts = 0;
        final int MAX_ATTEMPTS = 10;
        while (attempts < MAX_ATTEMPTS) {
            int dx = random.nextInt(-maxDistance, maxDistance + 1);
            int dy = random.nextInt(-maxDistance, maxDistance + 1);

            int nx = x + dx;
            int ny = y + dy;

            if (nx == x && ny == y) continue; // не остаёмся на месте

            if (nx >= 0 && nx < WIDTH && ny >= 0 && ny < HEIGHT) {
                return new int[]{nx, ny};
            }
            attempts++;
        }

        return new int[]{x, y}; // остаёмся на месте
    }

    public long getTotalAnimals() {
        return getCellsStream().mapToLong(Cell::getTotalAnimals).sum();
    }

    public long getAnimalCount(Class<? extends Animal> type) {
        return getCellsStream()
                .mapToLong(cell -> cell.getAnimalCount(type))
                .sum();
    }

    public long getTotalPlants() {
        return getCellsStream().mapToLong(Cell::getPlantCount).sum();
    }

    public java.util.stream.Stream<Cell> getCellsStream() {
        return java.util.stream.Stream.of(cells)
                .flatMap(java.util.stream.Stream::of);
    }
}
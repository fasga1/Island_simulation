package island.simulation;

import island.model.Island;
import island.model.Cell;
import island.entities.Animal;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class SimulationEngine {
    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ExecutorService workerPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final int TICK_DELAY_MS = 1000; // 1 секунда на такт
    private final int MAX_TICKS = 1000;
    private volatile int tickCount = 0;
    private volatile boolean running = true;

    public SimulationEngine(Island island) {
        this.island = island;
    }

    public void start() {
        System.out.println("🌱 Симуляция экосистемы 'Остров' запущена!\n");
        scheduler.scheduleAtFixedRate(this::simulationStep, 0, TICK_DELAY_MS, TimeUnit.MILLISECONDS);
    }

    private void simulationStep() {
        if (!running || tickCount >= MAX_TICKS) {
            stop();
            return;
        }

        tickCount++;
        growPlants();

        processAnimals();

        removeStarvedAnimals();

        printStatistics();

        if (island.getTotalAnimals() == 0) {
            System.out.println("💀 Все животные вымерли. Симуляция завершена.");
            stop();
        }
    }

    private void growPlants() {
        island.getCellsStream().forEach(cell -> {
            int growth = ThreadLocalRandom.current().nextInt(0, 51); // 0–50 новых растений
            cell.addPlants(growth);
        });
    }

    private void processAnimals() {
        List<Callable<Void>> tasks = new ArrayList<>();

        island.getCellsStream().forEach(cell -> {
            tasks.add(() -> {
                List<Animal> animals = cell.getAllAnimals().values().stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

                for (Animal animal : animals) {
                    if (animal.getIsland() == null) continue;

                    animal.increaseHunger();
                    animal.eat();
                    animal.move();
                    animal.reproduce();
                }
                return null;
            });
        });

        try {
            workerPool.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void removeStarvedAnimals() {
        island.getCellsStream().forEach(cell -> {
            List<Animal> toRemove = new ArrayList<>();
            for (var entry : cell.getAllAnimals().entrySet()) {
                for (Animal animal : entry.getValue()) {
                    if (animal.isStarving()) {
                        toRemove.add(animal);
                    }
                }
            }
            for (Animal animal : toRemove) {
                cell.removeAnimal(animal);
            }
        });
    }

    private void printStatistics() {
        System.out.println("=== ТАКТ " + tickCount + " ===");
        System.out.println("🌿 Растений: " + island.getTotalPlants());
        System.out.println("🐺 Волков: " + island.getAnimalCount(island.animals.predators.Wolf.class));
        System.out.println("🐍 Удавов: " + island.getAnimalCount(island.animals.predators.Boa.class));
        System.out.println("🦊 Лис: " + island.getAnimalCount(island.animals.predators.Fox.class));
        System.out.println("🐻 Медведей: " + island.getAnimalCount(island.animals.predators.Bear.class));
        System.out.println("🦅 Орлов: " + island.getAnimalCount(island.animals.predators.Eagle.class));

        System.out.println("🐇 Кроликов: " + island.getAnimalCount(island.animals.herbivores.Rabbit.class));
        System.out.println("🦌 Оленей: " + island.getAnimalCount(island.animals.herbivores.Deer.class));
        System.out.println("... и других видов.");
        System.out.println("🧍 Всего животных: " + island.getTotalAnimals());
        System.out.println();
    }

    private void stop() {
        if (running) {
            running = false;
            workerPool.shutdown();
            scheduler.shutdown();
            try {
                if (!workerPool.awaitTermination(1, TimeUnit.SECONDS)) {
                    workerPool.shutdownNow();
                }
                if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                workerPool.shutdownNow();
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
            System.out.println("⏹ Симуляция остановлена.");
        }
    }
}
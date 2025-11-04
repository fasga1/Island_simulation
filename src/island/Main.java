package island;

import island.model.Island;
import island.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        Island island = new Island();
        SimulationEngine engine = new SimulationEngine(island);
        engine.start();

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
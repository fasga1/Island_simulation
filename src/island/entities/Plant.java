package island.entities;

public class Plant {
    private static final double WEIGHT = 1.0;
    private static final int MAX_ON_CELL = 200;

    public static double getWeight() { return WEIGHT; }
    public static int getMaxOnCell() { return MAX_ON_CELL; }

    @Override
    public String toString() {
        return "🌿";
    }
}
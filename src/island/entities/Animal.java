package island.entities;

import island.model.Island;

import java.util.concurrent.ThreadLocalRandom;

import island.model.Cell;

public abstract class Animal implements Cloneable {
    protected double weight;
    protected int maxOnCell;
    protected int speed;
    protected double foodRequirement;
    protected String emoji;

    protected int x;
    protected int y;
    protected Island island;
    protected int hunger = 0;
    protected static final int MAX_HUNGER = 3;
    protected final ThreadLocalRandom random = ThreadLocalRandom.current();

    public void increaseHunger() {
        hunger++;
    }

    public void resetHunger() {
        hunger = 0;
    }

    public boolean isStarving() {
        return hunger > MAX_HUNGER;
    }
    public Animal(double weight, int maxOnCell, int speed, double foodRequirement, String emoji, Island island, int x, int y) {
        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.speed = speed;
        this.foodRequirement = foodRequirement;
        this.emoji = emoji;
        this.island = island;
        this.x = x;
        this.y = y;
    }

    public double getWeight() { return weight; }
    public int getMaxOnCell() { return maxOnCell; }
    public int getSpeed() { return speed; }
    public double getFoodRequirement() { return foodRequirement; }
    public String getEmoji() { return emoji; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Island getIsland() { return island; }

    public void moveTo(int newX, int newY) {
        island.getCell(x, y).removeAnimal(this);
        this.x = newX;
        this.y = newY;
        island.getCell(newX, newY).addAnimal(this);
    }

    public void move() {
        if (speed <= 0) {
            return; // например, гусеница
        }

        int[] newCoords = island.getRandomNeighborCoords(x, y, speed);
        int newX = newCoords[0];
        int newY = newCoords[1];

        if (newX != x || newY != y) {
            moveTo(newX, newY);
        }
    }

    public abstract boolean canEat(Animal other);
    public abstract double getEatingProbability(Animal other);
    public abstract void eat();
    public void reproduce() {
        Cell cell = island.getCell(x, y);
        int sameTypeCount = cell.getAnimalCount(this.getClass());

        if (sameTypeCount < 2) {
            return;
        }

        Animal offspring = this.clone();
        offspring.x = this.x;
        offspring.y = this.y;
        offspring.island = this.island;
        offspring.hunger = 0;
        cell.addAnimal(offspring);
    }


    protected boolean chance(double probabilityPercent) {
        return ThreadLocalRandom.current().nextDouble(100) < probabilityPercent;
    }

    @Override
    public Animal clone() {
        try {
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Не удалось клонировать животное", e);
        }
    }
}
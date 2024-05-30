package com.example.ooad_project;

public abstract class Pest {
    protected String name;
    protected double damage;

    public Pest(String name, double damage) {
        this.name = name;
        this.damage = damage;
    }

    // Method to simulate the pest's effect on a plant
    public abstract void affectPlant(Plant plant);

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }
}

package com.example.ooad_project;


/**
 * This is abstract class that represents a plant in the garden.
 * It is the parent class for all the plants in the garden.
 * i.e. flowers, trees, shrubs, etc.
 */
public abstract class Plant {

    protected String name;
    protected double health;
    protected int waterRequirement;

    public Plant(String name, double health, int waterRequirement) {
        this.name = name;
        this.health = health;
        this.waterRequirement = waterRequirement;
    }

    // Standard getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(int waterRequirement) {
        this.waterRequirement = waterRequirement;
    }




}

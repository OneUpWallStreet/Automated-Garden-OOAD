package com.example.ooad_project.Plant;


/**
 * This is abstract class that represents a plant in the garden.
 * It is the parent class for all the plants in the garden.
 * i.e. flowers, trees, shrubs, etc.
 */
public abstract class Plant {

    private String name;
    private double health;
    private int waterRequirement;
    private String imageName;

    public Plant(String name, double health, int waterRequirement, String imageName) {
        this.name = name;
        this.health = health;
        this.waterRequirement = waterRequirement;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }




}

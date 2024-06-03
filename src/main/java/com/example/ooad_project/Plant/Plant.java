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
    private Boolean isWatered = false;
    private int currentWater = 0;
    private int temperatureRequirement;

    public Plant(String name, double health, int waterRequirement, String imageName, int temperatureRequirement) {
        this.name = name;
        this.health = health;
        this.waterRequirement = waterRequirement;
        this.imageName = imageName;
        this.temperatureRequirement = temperatureRequirement;
    }

    public synchronized void addWater(int amount) {
        System.out.println("Adding " + amount + " water to " + name);
        this.currentWater = Math.min(currentWater + amount, waterRequirement);
        this.isWatered = currentWater >= waterRequirement;
    }

    // Standard getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsWatered() {
        return isWatered;
    }

    public void setIsWatered(Boolean isWatered) {
        this.isWatered = isWatered;
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public void setCurrentWater(int currentWater) {
        this.currentWater = currentWater;
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

    public int getTemperatureRequirement() {
        return temperatureRequirement;
    }

    public void setTemperatureRequirement(int temperatureRequirement) {
        this.temperatureRequirement = temperatureRequirement;
    }




}

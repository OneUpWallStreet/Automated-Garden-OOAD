package com.example.ooad_project.Plant;


import java.util.ArrayList;

/**
 * This is abstract class that represents a plant in the garden.
 * It is the parent class for all the plants in the garden.
 * i.e. flowers, trees, shrubs, etc.
 */
public abstract class Plant {

    private final String name;
    private final int waterRequirement;
    private String imageName;
    private Boolean isWatered = false;
    private int currentWater = 0;
    private final int temperatureRequirement;


    private final int healthSmall;
    private final int healthMedium;
    private final int healthFull;
    private int currentHealth;

    private ArrayList<String> vulnerableTo;

//    Default row and col are -1
//    i.e. the plant is not in the garden
    private int row = -1;
    private int col = -1;

    public Plant(String name, int waterRequirement, String imageName, int temperatureRequirement, ArrayList<String> vulnerableTo, int healthSmall, int healthMedium, int healthFull) {
        this.name = name;
        this.waterRequirement = waterRequirement;
        this.imageName = imageName;
        this.temperatureRequirement = temperatureRequirement;
        this.vulnerableTo = vulnerableTo;
        this.healthSmall = healthSmall;
        this.healthMedium = healthMedium;
        this.healthFull = healthFull;

//        Plant starts at small health
//        i.e. it is newly planted
        this.currentHealth = healthSmall;
    }

    public synchronized void addWater(int amount) {
        this.currentWater = Math.min(currentWater + amount, waterRequirement);
        this.isWatered = currentWater >= waterRequirement;
    }

    // Standard getters and setters

    public ArrayList<String> getVulnerableTo() {
        return vulnerableTo;
    }

    public String getName() {
        return name;
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



    public int getWaterRequirement() {
        return waterRequirement;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getHealthSmall() {
        return healthSmall;
    }

    public int getHealthMedium() {
        return healthMedium;
    }

    public int getHealthFull() {
        return healthFull;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }





}

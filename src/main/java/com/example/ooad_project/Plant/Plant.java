package com.example.ooad_project.Plant;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * This is abstract class that represents a plant in the garden.
 * It is the parent class for all the plants in the garden.
 * i.e. flowers, trees, shrubs, etc.
 */
public abstract class Plant {

    private final String name;
    private final int waterRequirement;
    private String currentImage;
    private Boolean isWatered = false;
    private int currentWater = 0;
    private final int temperatureRequirement;
    private static final Logger logger = LogManager.getLogger("PesticideSystemLogger");
    private ArrayList<String> allImages;

    private final int healthSmall;
    private final int healthMedium;
    private final int healthFull;
    private int currentHealth;

    private ArrayList<String> vulnerableTo;

//    Default row and col are -1
//    i.e. the plant is not in the garden
    private int row = -1;
    private int col = -1;

    public Plant(String name, int waterRequirement, String imageName, int temperatureRequirement, ArrayList<String> vulnerableTo, int healthSmall, int healthMedium, int healthFull, ArrayList<String> allImages) {
        this.name = name;
        this.waterRequirement = waterRequirement;
        this.currentImage = imageName;
        this.temperatureRequirement = temperatureRequirement;
        this.vulnerableTo = vulnerableTo;
        this.healthSmall = healthSmall;
        this.healthMedium = healthMedium;
        this.healthFull = healthFull;
        this.allImages = allImages;
//        Plant starts at small health
//        i.e. it is newly planted
        this.currentHealth = healthSmall;
    }

//    Heal plant function

    public synchronized void addWater(int amount) {
        this.currentWater = Math.min(currentWater + amount, waterRequirement);
        this.isWatered = currentWater >= waterRequirement;
    }

    public synchronized void healPlant(int healAmount) {
        // Increase current health by the heal amount but do not exceed the maximum possible health
        this.currentHealth = Math.min(this.currentHealth + healAmount, this.healthFull);
        // Log the healing action
        System.out.println("Plant: " + this.name + " at position (" + this.row + ", " + this.col + ") healed by " + healAmount + " points, new health: " + this.currentHealth);
        logger.info("Plant: {} at position ({}, {}) healed by {} points, new health: {}", this.name, this.row, this.col, healAmount, this.currentHealth);
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


    public String getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(String currentImage) {
        this.currentImage = currentImage;
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

    public ArrayList<String> getAllImages() {
        return allImages;
    }



    /**
     * Retrieves the current health of the plant.
     * This method is synchronized to ensure thread safety.
     * @return the current health of the plant.
     */
    public synchronized int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets the current health of the plant.
     * This method is synchronized to ensure that updates are atomic and changes
     * are visible to other threads.
     * @param health the new health value for the plant.
     */
    public synchronized void setCurrentHealth(int health) {
        this.currentHealth = health;
    }





}

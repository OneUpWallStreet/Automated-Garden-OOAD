package com.example.ooad_project.Parasite.Children;

import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Plant.Plant;

import java.util.ArrayList;
import java.util.Random;

// Slugs class with a 25% miss chance
public class Slugs extends Parasite {
    private static final double MISS_CHANCE = 0.25;  // 25% chance to miss
    private Random random = new Random();

    public Slugs(String name, int damage, String imageName, ArrayList<String> affectedPlants) {
        super(name, damage, imageName, affectedPlants);
    }

    @Override
    public void affectPlant(Plant plant) {
        if (random.nextDouble() >= MISS_CHANCE) {
            int newHealth = Math.max(0, plant.getCurrentHealth() - this.getDamage());
            plant.setCurrentHealth(newHealth);
            System.out.println("Slugs have successfully damaged the plant " + plant.getName() + ". New health: " + newHealth);
        } else {
            System.out.println("Slugs attempted to damage the plant " + plant.getName() + " but missed.");
        }
    }
}
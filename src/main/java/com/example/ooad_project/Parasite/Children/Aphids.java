package com.example.ooad_project.Parasite.Children;

import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Plant.Plant;

import java.util.ArrayList;
import java.util.Random;

// Aphids class with a 10% miss chance
public class Aphids extends Parasite {
    private static final double MISS_CHANCE = 0.10;  // 10% chance to miss
    private Random random = new Random();

    public Aphids(String name, int damage, String imageName, ArrayList<String> affectedPlants) {
        super(name, damage, imageName, affectedPlants);
    }

    @Override
    public void affectPlant(Plant plant) {
        if (random.nextDouble() >= MISS_CHANCE) {
            int newHealth = Math.max(0, plant.getCurrentHealth() - this.getDamage());
            plant.setCurrentHealth(newHealth);
            System.out.println("Aphids have successfully damaged the plant " + plant.getName() + ". New health: " + newHealth);
        } else {
            System.out.println("Aphids attempted to damage the plant " + plant.getName() + " but missed.");
        }
    }
}
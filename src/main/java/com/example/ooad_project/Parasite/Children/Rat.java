package com.example.ooad_project.Parasite.Children;

import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Plant.Plant;

import java.util.ArrayList;
import java.util.Random;

public class Rat extends Parasite {

    private Random random = new Random();
    private static final double MISS_CHANCE = 0.15;  // 15% chance to miss


    public Rat(String name, int damage , String imageName, ArrayList<String> affectedPlants) {
        super(name, damage, imageName, affectedPlants);
    }


    @Override
    public void affectPlant(Plant plant) {
        if (random.nextDouble() >= MISS_CHANCE) {
            // If not missed, apply the damage
            int newHealth = Math.max(0, plant.getCurrentHealth() - this.getDamage());
            plant.setCurrentHealth(newHealth);
//            Later also include old health and new health
            System.out.println("Rat has successfully damaged the plant " + plant.getName() + ". New health: " + newHealth);
        } else {
            // If missed, do nothing
            System.out.println("Rat attempted to damage the plant " + plant.getName() + " but missed.");
        }
    }


}

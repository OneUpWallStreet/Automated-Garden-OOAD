package com.example.ooad_project.Parasite;

import com.example.ooad_project.Plant.Plant;

public abstract class Parasite {
    protected double damage;

    public Parasite(double damage) {
        this.damage = damage;
    }

    // Method to simulate the pest's effect on a plant
    public abstract void affectPlant(Plant plant);


    public double getDamage() {
        return damage;
    }
}

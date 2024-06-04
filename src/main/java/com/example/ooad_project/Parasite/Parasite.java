package com.example.ooad_project.Parasite;

import com.example.ooad_project.Plant.Plant;

import java.util.ArrayList;

public class Parasite {
    private String name;
    private int damage;
    private String imageName;
    private ArrayList<String> affectedPlants;

    public Parasite(String name, int damage, String imageName, ArrayList<String> affectedPlants) {
        this.name = name;
        this.damage = damage;
        this.imageName = imageName;
        this.affectedPlants = affectedPlants;
    }


    public void affectPlant(Plant plant) {
        // Implementation depends on specific effects of parasite
        System.out.println("CALLED AFFECT PLANT METHOD IN PARASITE CLASS. PLEASE OVERRIDE THIS METHOD IN CHILD CLASSES.");
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getImageName() {
        return imageName;
    }


    public ArrayList<String> getAffectedPlants() {
        return affectedPlants;
    }
}


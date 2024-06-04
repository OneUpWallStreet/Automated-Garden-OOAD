package com.example.ooad_project.Parasite.Children;

import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Plant.Plant;

import java.util.ArrayList;

public class Rat extends Parasite {


    public Rat(String name, int damage , String imageName, ArrayList<String> affectedPlants) {
        super(name, damage, imageName, affectedPlants);
    }


    @Override
    public void affectPlant(Plant plant) {
        // Implementation depends on specific effects of parasite
        System.out.println("OVERRIDE -> RAT are affecting the plant.");
    }

}

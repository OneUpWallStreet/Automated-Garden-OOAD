package com.example.ooad_project.Parasite.Children;

import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Plant.Plant;

import java.util.ArrayList;

public class Crow extends Parasite {

    public Crow(String name, int damage , String imageName, ArrayList<String> affectedPlants) {
        super(name, damage, imageName, affectedPlants);
    }

    @Override
    public void affectPlant(Plant plant) {
        // Implementation depends on specific effects of parasite
        System.out.println("CROW are affecting the plant.");
    }


}

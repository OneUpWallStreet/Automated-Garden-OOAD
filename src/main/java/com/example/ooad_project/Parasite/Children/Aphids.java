package com.example.ooad_project.Parasite.Children;

import com.example.ooad_project.Parasite.Parasite;

import java.util.ArrayList;

public class Aphids extends Parasite {

    public Aphids(String name, int damage , String imageName, ArrayList<String> affectedPlants) {
        super(name, damage, imageName, affectedPlants);
    }


}

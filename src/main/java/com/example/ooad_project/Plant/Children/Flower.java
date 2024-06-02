package com.example.ooad_project.Plant.Children;


import com.example.ooad_project.Plant.Plant;

/**
 * This class represents a flower in the garden.
 * It is a subclass of the Plant class.
 * It will be extended by all the flowers in the garden.
 */
public class Flower extends Plant {

    public Flower(String name, double health, int waterRequirement, String imageName){
        super(name, health, waterRequirement, imageName);
    }

}

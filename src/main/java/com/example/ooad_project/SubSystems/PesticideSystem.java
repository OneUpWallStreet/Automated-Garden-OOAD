package com.example.ooad_project.SubSystems;

import com.example.ooad_project.Events.ParasiteEvent;
import com.example.ooad_project.GardenGrid;
import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.ThreadUtils.EventBus;

public class PesticideSystem implements Runnable{

    private final GardenGrid gardenGrid;

    public PesticideSystem() {
        this.gardenGrid = GardenGrid.getInstance();
        System.out.println("Pesticide System Initialized");
        EventBus.subscribe("ParasiteEvent", event -> handlePesticideEvent((ParasiteEvent) event));
    }

    private void handlePesticideEvent(ParasiteEvent event) {
        Parasite parasite = event.getParasite();
//        System.out.println("Parasite attack on plant: " + parasite.getName() + " with damage: " + parasite.getDamage());

        // Loop through all the plants in the garden grid
        for (int i = 0; i < gardenGrid.getNumRows(); i++) {
            for (int j = 0; j < gardenGrid.getNumCols(); j++) {
                Plant plant = gardenGrid.getPlant(i, j);
                if (plant != null && parasite.getAffectedPlants().contains(plant.getName())) {
                    parasite.affectPlant(plant);
                }
            }
        }
    }


//    When handling parasite attacks we will sleep the thread for 5 seconds
//    so that we can see the effects of the parasite attack on the plants in JavaFX


    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}

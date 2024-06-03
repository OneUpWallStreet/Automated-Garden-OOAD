package com.example.ooad_project.SubSystems;

import com.example.ooad_project.GardenGrid;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.ThreadUtils.EventBus;
import com.example.ooad_project.Events.RainEvent;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class WateringSystem implements Runnable {
    private final AtomicBoolean rainRequested = new AtomicBoolean(false);
    private int rainAmount = 0;
    private final GardenGrid gardenGrid;

    @Override
    public void run() {
        while (true) {
            try {
                // Handle scheduled sprinkling every hour at the 59th minute
                if (LocalDateTime.now().getMinute() == 59) {
                    sprinkle();
                }

                Thread.sleep(1000); // Check every second
//                System.out.println("Watering System is running");
            } catch (InterruptedException e) {
                System.out.println("Watering System interrupted");
                return; // Exit if interrupted
            }
        }
    }

    public WateringSystem() {
//        So our watering system is subscribed to the RainEvent
//        When a rain event is published, the watering system will handle it
        EventBus.subscribe("RainEvent", event -> handleRain((RainEvent) event));

//        Get the garden grid instance
//        This is the grid that holds all the plants
        this.gardenGrid = GardenGrid.getInstance();
    }

//    This method is called when a rain event is published
//    It waters all the plants in the garden grid
//    The amount of water each plant gets is the same
    private void handleRain(RainEvent event) {
        System.out.println("Handling rain event with amount: " + event.getAmount());
        int counter = 0;

        for (int i = 0; i < gardenGrid.getNumRows(); i++) {
            for (int j = 0; j < gardenGrid.getNumCols(); j++) {
                Plant plant = gardenGrid.getPlant(i, j);
                if (plant != null) {
                    plant.addWater(event.getAmount());
                    counter++;
                }
            }
        }

        System.out.println("Watered " + counter + " plants");
    }



//    This method is called when the sprinklers are activated
//    It waters all the plants in the garden grid
//    The amount of water each plant gets depends on how much water it needs
    private void sprinkle() {
        System.out.println("Sprinklers activated!");
        int counter = 0; // Counter to keep track of how many plants are watered

        for (int i = 0; i < gardenGrid.getNumRows(); i++) {
            for (int j = 0; j < gardenGrid.getNumCols(); j++) {
                Plant plant = gardenGrid.getPlant(i, j);
                if (plant != null && !plant.getIsWatered()) {
                    int waterNeeded = plant.getWaterRequirement() - plant.getCurrentWater();
                    if (waterNeeded > 0) {
                        plant.addWater(waterNeeded);
                        counter++;
                    }
                }
            }
        }

        System.out.println("Sprinkled " + counter + " plants.");
        gardenGrid.printAllPlantStats();
    }
}



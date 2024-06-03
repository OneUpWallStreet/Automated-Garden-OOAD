package com.example.ooad_project.SubSystems;

import com.example.ooad_project.Events.TemperatureEvent;
import com.example.ooad_project.GardenGrid;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.ThreadUtils.EventBus;

public class TemperatureSystem implements Runnable{

    private final GardenGrid gardenGrid;




    public TemperatureSystem() {
//        Subscribe to the TemperatureEvent
//        Published from GardenSimulationAPI
        this.gardenGrid = GardenGrid.getInstance();
        EventBus.subscribe("TemperatureEvent", event -> handleTemperatureEvent((TemperatureEvent) event));
    }

    private void handleTemperatureEvent(TemperatureEvent event) {
        int currentTemperature = event.getAmount();
        System.out.println("Temperature Event Handled: Current Temp = " + currentTemperature);

        for (int i = 0; i < gardenGrid.getNumRows(); i++) {
            for (int j = 0; j < gardenGrid.getNumCols(); j++) {
                Plant plant = gardenGrid.getPlant(i, j);
                if (plant != null) {
                    int tempDiff = currentTemperature - plant.getTemperatureRequirement();
                    if (tempDiff > 0) {
                        System.out.println("Temperature system cooled " + plant.getName() + " by " + Math.abs(tempDiff) + " degrees F.");
                    } else if (tempDiff < 0) {
                        System.out.println("Temperature system heated " + plant.getName() + " by " + Math.abs(tempDiff) + " degrees F.");
                    } else {
                        System.out.println(plant.getName() + " is at optimal temperature.");
                    }
                }
            }
        }
    }


    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
//                System.out.println("Temperature System is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}

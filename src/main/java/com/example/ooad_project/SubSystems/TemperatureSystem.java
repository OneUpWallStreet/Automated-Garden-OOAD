package com.example.ooad_project.SubSystems;

import com.example.ooad_project.Events.TemperatureEvent;
import com.example.ooad_project.GardenGrid;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.ThreadUtils.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemperatureSystem implements Runnable{

    private final GardenGrid gardenGrid;
    private static final Logger logger = LogManager.getLogger("TemperatureSystemLogger");




    public TemperatureSystem() {
//        Subscribe to the TemperatureEvent
//        Published from GardenSimulationAPI
        this.gardenGrid = GardenGrid.getInstance();
        EventBus.subscribe("TemperatureEvent", event -> handleTemperatureEvent((TemperatureEvent) event));
    }

    private void handleTemperatureEvent(TemperatureEvent event) {
        int currentTemperature = event.getAmount();
        logger.info("Temperature Event Handled: Current Temp = {}", currentTemperature);
        System.out.println("Here");

        for (int i = 0; i < gardenGrid.getNumRows(); i++) {
            for (int j = 0; j < gardenGrid.getNumCols(); j++) {
                Plant plant = gardenGrid.getPlant(i, j);
                if (plant != null) {
                    int tempDiff = currentTemperature - plant.getTemperatureRequirement();
                    if (tempDiff > 0) {
                        logger.info("Temperature system cooled {} by {} degrees F.", plant.getName(), Math.abs(tempDiff));
                    } else if (tempDiff < 0) {
                        logger.info("Temperature system heated {} by {} degrees F.", plant.getName(), Math.abs(tempDiff));
                    } else {
                        logger.info("{} is at optimal temperature.", plant.getName());
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

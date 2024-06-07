package com.example.ooad_project;

import com.example.ooad_project.Events.DayChangeEvent;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.ThreadUtils.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DaySystem {
    private static DaySystem instance = null;
    private final ScheduledExecutorService scheduler;
    private int currentDay;  // Variable to keep track of the current day
    private final Logger logger = LogManager.getLogger("DayLogger");
    GardenGrid gardenGrid = GardenGrid.getInstance();


    private DaySystem() {
        System.out.println("Day System Initialized");
        logger.info("Day System Initialized");

        scheduler = Executors.newScheduledThreadPool(1);
        currentDay = 0;  // Start at Day 0

//      scheduler.scheduleAtFixedRate(this::endOfDayActions, 1, 1, TimeUnit.HOURS);

        // Schedule the end of day actions to run every 1 minute, treating each minute as a new day
//        scheduler.scheduleAtFixedRate(this::endOfDayActions, 0, 1, TimeUnit.MINUTES);

        scheduler.scheduleAtFixedRate(this::endOfDayActions, 0, 10, TimeUnit.SECONDS);
    }

    public static synchronized DaySystem getInstance() {
        if (instance == null) {
            instance = new DaySystem();
        }
        return instance;
    }

    private void endOfDayActions() {
        try {
            logger.info("End of Day: {}", currentDay);


//            Reset the watered status of all plants
//            Heal all plants by 10
            for (int i = 0; i < gardenGrid.getNumRows(); i++) {
                for (int j = 0; j < gardenGrid.getNumCols(); j++) {
                    Plant plant = gardenGrid.getPlant(i, j);
                    if (plant != null) {
                        plant.setIsWatered(false);
                        plant.healPlant(6);
                    }
                }
            }

            currentDay++;
            EventBus.publish("DayChangeEvent", new DayChangeEvent(currentDay));
            logger.info("Changed day to: {}", currentDay);

        } catch (Exception e) {
            logger.error("Error during end of day processing: ", e);
            System.out.println("Error during end of day processing: " + e);
        }
    }


}

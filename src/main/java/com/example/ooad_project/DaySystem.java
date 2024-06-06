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



    private DaySystem() {
        System.out.println("Day System Initialized");
        logger.info("Day System Initialized");

        scheduler = Executors.newScheduledThreadPool(1);
        currentDay = 0;  // Start at Day 0

//      scheduler.scheduleAtFixedRate(this::endOfDayActions, 1, 1, TimeUnit.HOURS);

        // Schedule the end of day actions to run every 1 minute, treating each minute as a new day
        scheduler.scheduleAtFixedRate(this::endOfDayActions, 0, 1, TimeUnit.MINUTES);
    }

    public static synchronized DaySystem getInstance() {
        if (instance == null) {
            instance = new DaySystem();
        }
        return instance;
    }

    private void endOfDayActions() {
        // Get the instance of the GardenGrid
        GardenGrid gardenGrid = GardenGrid.getInstance();


        logger.info("End of Day: {}", currentDay);

        for (int i = 0; i < gardenGrid.getNumRows(); i++) {
            for (int j = 0; j < gardenGrid.getNumCols(); j++) {
                // Get the plant at the current position
                Plant plant = gardenGrid.getPlant(i, j);

                // If there is a plant at the current position, reset its isWatered property and heal it
                if (plant != null) {
                    plant.setIsWatered(false);

                    plant.healPlant(5);
                }
            }
        }


        logger.info("Changed day to: {}", currentDay + 1);
        EventBus.publish("DayChangeEvent", new DayChangeEvent(currentDay + 1));
        currentDay++;  // Increment the day count after each day ends
    }
}

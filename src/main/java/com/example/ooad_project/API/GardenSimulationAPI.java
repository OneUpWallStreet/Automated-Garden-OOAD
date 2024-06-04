package com.example.ooad_project.API;

import com.example.ooad_project.Events.ParasiteEvent;
import com.example.ooad_project.Events.RainEvent;
import com.example.ooad_project.Events.TemperatureEvent;
import com.example.ooad_project.ThreadUtils.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class GardenSimulationAPI implements GardenSimulationAPIInterface {
    private static final Logger logger = LogManager.getLogger("GardenSimulationAPILogger");

    @Override
    public void initializeGarden() {
        logger.info("Initializing Garden");
    }

    @Override
    public Map<String, Object> getPlants() {
        logger.info("Retrieving plant information");
        return Map.of();
    }

    @Override
    public void rain(int amount) {
        logger.info("API called rain with amount: {}", amount);
        EventBus.publish("RainEvent", new RainEvent(amount));
    }

    @Override
    public void temperature(int amount) {
        logger.info("API called temperature set to: {}", amount);
        EventBus.publish("TemperatureEvent", new TemperatureEvent(amount));
    }

    @Override
    public void parasite(String name) {
        logger.info("API called to handle parasite: {}", name);
        EventBus.publish("ParasiteEvent", new ParasiteEvent(name));
    }

    @Override
    public void getState() {
        logger.info("API called to get current state");
    }
}

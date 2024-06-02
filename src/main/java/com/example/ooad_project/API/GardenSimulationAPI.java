package com.example.ooad_project.API;

import com.example.ooad_project.RainEvent;
import com.example.ooad_project.ThreadUtils.EventBus;

import java.util.Map;





public class GardenSimulationAPI  implements GardenSimulationAPIInterface{


    @Override
    public void initializeGarden() {

    }

    @Override
    public Map<String, Object> getPlants() {
        return Map.of();
    }

    @Override
    public void rain(int amount) {
        EventBus.publish("RainEvent", new RainEvent(amount));
    }

    @Override
    public void temperature(int amount) {

    }

    @Override
    public void parasite(String name) {

    }

    @Override
    public void getState() {

    }
}

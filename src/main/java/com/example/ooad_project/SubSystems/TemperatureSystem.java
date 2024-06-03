package com.example.ooad_project.SubSystems;

import com.example.ooad_project.Events.TemperatureEvent;
import com.example.ooad_project.ThreadUtils.EventBus;

public class TemperatureSystem implements Runnable{

    public TemperatureSystem() {
        EventBus.subscribe("TemperatureEvent", event -> handleTemperatureEvent((TemperatureEvent) event));
    }

    private void handleTemperatureEvent(TemperatureEvent event) {
        System.out.println("TEMP NOT WATER: " + event.getAmount());
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

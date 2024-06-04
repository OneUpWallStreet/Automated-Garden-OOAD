package com.example.ooad_project.SubSystems;

import com.example.ooad_project.Events.ParasiteEvent;
import com.example.ooad_project.ThreadUtils.EventBus;

public class PesticideSystem implements Runnable{

    public PesticideSystem() {
        System.out.println("Pesticide System Initialized");
        EventBus.subscribe("ParasiteEvent", event -> handlePesticideEvent((ParasiteEvent) event));
    }

    private void handlePesticideEvent(ParasiteEvent event) {
        System.out.println("Parasite attack on plant: " + event.getParasiteName()) ;
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

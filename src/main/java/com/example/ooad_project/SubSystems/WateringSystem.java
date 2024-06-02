package com.example.ooad_project.SubSystems;

import com.example.ooad_project.ThreadUtils.EventBus;
import com.example.ooad_project.RainEvent;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class WateringSystem implements Runnable {
    private final AtomicBoolean rainRequested = new AtomicBoolean(false);
    private int rainAmount = 0;

    @Override
    public void run() {
        while (true) {
            try {
                // Handle scheduled sprinkling every hour at the 59th minute
                if (LocalDateTime.now().getMinute() == 59) {
                    sprinkle();
                }

                // Handle rain if requested via API
                if (rainRequested.get()) {
                    simulateRain(rainAmount);
                    rainRequested.set(false); // Reset the flag
                }

                Thread.sleep(1000); // Check every second
                System.out.println("Watering System is running");
            } catch (InterruptedException e) {
                System.out.println("Watering System interrupted");
                return; // Exit if interrupted
            }
        }
    }

    public WateringSystem() {
        EventBus.subscribe("RainEvent", event -> handleRain((RainEvent) event));
    }

    private void handleRain(RainEvent event) {
        System.out.println("Handling rain event with amount: " + event.getAmount());
        System.out.println("Rain event received from EventBus");
        System.out.println("HAHAHAHHAHA");
        // Additional rain handling logic
    }

    private void sprinkle() {
        System.out.println("Sprinklers activated!");
        // Logic to activate sprinklers
    }

    public void requestRain(int amount) {
        this.rainAmount = amount;
        rainRequested.set(true);
    }

    private void simulateRain(int amount) {
        System.out.println("Simulating rain with amount: " + amount);
        // Logic to adjust water levels with the specified amount
    }
}



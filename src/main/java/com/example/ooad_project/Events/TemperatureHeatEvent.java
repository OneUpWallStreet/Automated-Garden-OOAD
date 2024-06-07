package com.example.ooad_project.Events;

public class TemperatureHeatEvent {

    int tempDiff;


    public TemperatureHeatEvent(int tempDiff) {
        this.tempDiff = tempDiff;
    }

    public int getTempDiff() {
        return tempDiff;
    }
}

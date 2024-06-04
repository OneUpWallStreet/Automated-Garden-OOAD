package com.example.ooad_project;

import com.example.ooad_project.API.GardenSimulationAPI;
import com.example.ooad_project.SubSystems.TemperatureSystem;
import com.example.ooad_project.SubSystems.WateringSystem;
import com.example.ooad_project.ThreadUtils.ThreadManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("OOAD - Project!");
        stage.setScene(scene);
        stage.show();

        initializeBackgroundServices();

        // Schedule API rain calls using JavaFX Timeline
        runAPIScheduledTasks();
    }

//    We should start all of our background services here
    private void initializeBackgroundServices() {
        Runnable wateringSystem = new WateringSystem();
        Runnable temperatureSystem = new TemperatureSystem();
        ThreadManager.run(wateringSystem);
        ThreadManager.run(temperatureSystem);

    }

//    This is for testing the API
//    I assume Prof is going to do something similar
    private void runAPIScheduledTasks() {
        GardenSimulationAPI api = new GardenSimulationAPI();

//        Schedule rain every 15 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), ev -> {
            api.rain(4);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Random rand = new Random();
//        Schedule temperature every 10 seconds
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(20), ev -> {
            api.temperature(rand.nextInt(70));
        }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();

    }


    public static void main(String[] args) {
        launch();
    }
}






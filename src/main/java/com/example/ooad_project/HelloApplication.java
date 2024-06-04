package com.example.ooad_project;

import com.example.ooad_project.API.GardenSimulationAPI;
import com.example.ooad_project.SubSystems.PesticideSystem;
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
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
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
        Runnable pesticideSystem = new PesticideSystem();
        ThreadManager.run(wateringSystem);
        ThreadManager.run(temperatureSystem);
        ThreadManager.run(pesticideSystem);

    }

//    This is for testing the API
//    I assume Prof is going to do something similar
    private void runAPIScheduledTasks() {
        GardenSimulationAPI api = new GardenSimulationAPI();
        Random rand = new Random();

//        Schedule rain every 15 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), ev -> {
            api.rain(rand.nextInt(10));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


//        Schedule temperature every 10 seconds
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(20), ev -> {
            api.temperature(rand.nextInt(70));
        }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();

//        Schedule parasite every 10 seconds
        Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(20), ev -> {
            api.parasite("Rat");
        }));
        timeline3.setCycleCount(Timeline.INDEFINITE);
        timeline3.play();

    }


    public static void main(String[] args) {
        launch();
    }
}






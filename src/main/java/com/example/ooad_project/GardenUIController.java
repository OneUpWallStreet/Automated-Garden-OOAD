package com.example.ooad_project;

import com.example.ooad_project.Events.*;
import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Parasite.ParasiteManager;
import com.example.ooad_project.Plant.Children.Flower;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.Plant.Children.Tree;
import com.example.ooad_project.Plant.Children.Vegetable;
import com.example.ooad_project.Plant.PlantManager;
import com.example.ooad_project.ThreadUtils.EventBus;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.animation.PauseTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GardenUIController {

    @FXML
    private Button sidButton;

    @FXML
    private Label currentDay;

    @FXML
    private MenuButton parasiteMenuButton;

    @FXML
    private Button pestTestButton;

    @FXML
    private Label rainStatusLabel;
    @FXML
    private Label temperatureStatusLabel;
    @FXML
    private Label parasiteStatusLabel;

    @FXML
    private GridPane gridPane;
    @FXML
    private MenuButton vegetableMenuButton;
    @FXML
    private MenuButton flowerMenuButton;
    @FXML
    private MenuButton treeMenuButton;


    private final Random random = new Random();
    private GardenGrid gardenGrid;

//    This is the plant manager that will be used to get the plant data
//    from the JSON file, used to populate the menu buttons
    private PlantManager plantManager = PlantManager.getInstance();

//    Same as above but for the parasites
    private ParasiteManager parasiteManager = ParasiteManager.getInstance();

    public GardenUIController() {
        gardenGrid = GardenGrid.getInstance();
    }

//    This is the method that will print the grid
    @FXML
    public void printGrid(){
        gardenGrid.printGrid();
    }

    @FXML
    public void sidButtonPressed() {
        System.out.println("SID Button Pressed");
        plantManager.getVegetables().forEach(flower -> System.out.println(flower.getCurrentImage()));
    }

    @FXML
    private TextArea logTextArea;

    private static final Logger logger = LogManager.getLogger(GardenUIController.class);


//    This is the UI Logger for the GardenUIController
//    This is used to log events that happen in the UI
    private Logger log4jLogger = LogManager.getLogger("GardenUIControllerLogger");

    @FXML
    public void initialize() {

        initializeLogger();

        // Add ColumnConstraints
        for (int col = 0; col < gardenGrid.getNumCols(); col++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(80); // Adjust the width as needed
            gridPane.getColumnConstraints().add(colConst);
        }

        // Add RowConstraints
        for (int row = 0; row < gardenGrid.getNumRows(); row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(80); // Adjust the height as needed
            gridPane.getRowConstraints().add(rowConst);
        }

        // Load plants data from JSON file and populate MenuButtons
        loadPlantsData();
        loadParasitesData();

        log4jLogger.info("GardenUIController initialized");



        EventBus.subscribe("RainEvent", event -> changeRainUI((RainEvent) event));
        EventBus.subscribe("DisplayParasiteEvent", event -> handleDisplayParasiteEvent((DisplayParasiteEvent) event));
        EventBus.subscribe("PlantImageUpdateEvent", event -> handlePlantImageUpdateEvent((PlantImageUpdateEvent) event));
        EventBus.subscribe("DayChangeEvent",event -> handleDayChangeEvent((DayChangeEvent) event));
        EventBus.subscribe("TemperatureEvent", event -> changeTemperatureUI((TemperatureEvent) event));
        EventBus.subscribe("ParasiteEvent", event -> changeParasiteUI((ParasiteEvent) event));

//      Gives you row, col and waterneeded
        EventBus.subscribe("SprinklerEvent", event -> handleSprinklerEvent((SprinklerEvent) event));


//        When plant is cooled by x
        EventBus.subscribe("TemperatureCoolEvent", event -> handleTemperatureCoolEvent((TemperatureCoolEvent) event));


//      When plant is heated by x
        EventBus.subscribe("TemperatureHeatEvent", event -> handleTemperatureHeatEvent((TemperatureHeatEvent) event));


//        When plant is damaged by x
//        Includes -> row, col, damage
        EventBus.subscribe("ParasiteDamageEvent", event -> handleParasiteDamageEvent((ParasiteDamageEvent) event));
    }

//    Function that is called when the parasite damage event is published
    private void handleParasiteDamageEvent(ParasiteDamageEvent event) {
        System.out.println("ParasiteDamageEvent received");
    }

    private void handleTemperatureHeatEvent(TemperatureHeatEvent event) {
        System.out.println("TemperatureHeatEvent received");
    }


//    Function that is called when the temperature cool event is published
    private void handleTemperatureCoolEvent(TemperatureCoolEvent event) {
        System.out.println("TemperatureCoolEvent received");
    }

//  Function that is called when the sprinkler event is published
    private void handleSprinklerEvent(SprinklerEvent event) {
        System.out.println("SprinklerEvent received");
    }

    private void initializeLogger() {
        LoggerAppender.setController(this);
    }

    public void appendLogText(String text) {
        Platform.runLater(() -> logTextArea.appendText(text + "\n"));
    }

    public void handleDayChangeEvent(DayChangeEvent event) {
        System.out.println("day changed to: " + event.getDay());
        Platform.runLater(() -> {
            currentDay.setText("Day: " + event.getDay());
            System.out.println("Day changed to: " + event.getDay());
        });
    }

    private void handlePlantImageUpdateEvent(PlantImageUpdateEvent event) {

//        Be sure to wrap the code in Platform.runLater() to update the UI
//        This is because the event is being handled in a different thread
//        and we need to update the UI in the JavaFX Application Thread
        Platform.runLater(() -> {

            Plant plant = event.getPlant();

            // Calculate the grid position
            int row = plant.getRow();
            int col = plant.getCol();

            // Find the ImageView for the plant in the grid and remove it
            gridPane.getChildren().removeIf(node -> {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                    return GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col && node instanceof ImageView;
                }
                return false;
            });

            // Load the new image for the plant
            String imageName = plant.getCurrentImage();
            Image newImage = new Image(getClass().getResourceAsStream("/images/" + imageName));
            ImageView newImageView = new ImageView(newImage);
            newImageView.setFitHeight(40);  // Match the cell size in the grid
            newImageView.setFitWidth(40);

            // Create a pane to center the image
            StackPane pane = new StackPane();
            pane.getChildren().add(newImageView);
            gridPane.add(pane, col, row);

            System.out.println("Updated plant image at row " + row + " and column " + col + " to " + imageName);
    });
    }


    private void handleDisplayParasiteEvent(DisplayParasiteEvent event) {
        // Load the image for the rat
        String imageName = "/images/" + event.getParasite().getImageName();
        Image ratImage = new Image(getClass().getResourceAsStream(imageName));
        ImageView parasiteImageView = new ImageView(ratImage);

//        Change var name
        parasiteImageView.setFitHeight(20);  // Match the cell size in the grid
        parasiteImageView.setFitWidth(20);

        // Use the row and column from the event
        int row = event.getRow();
        int col = event.getColumn();

        // Place the rat image on the grid
//        gridPane.add(parasiteImageView, col, row);
//        System.out.println("Rat placed at row " + row + " and column " + col);


        // Place the parasite image on the grid in the same cell but with offset
        GridPane.setRowIndex(parasiteImageView, row);
        GridPane.setColumnIndex(parasiteImageView, col);
        GridPane.setHalignment(parasiteImageView, HPos.RIGHT);  // Align to right
        GridPane.setValignment(parasiteImageView, VPos.BOTTOM); // Align to bottom
        gridPane.getChildren().add(parasiteImageView);

        // Create a pause transition of 5 seconds before removing the rat image
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(_ -> {
            gridPane.getChildren().remove(parasiteImageView);  // Remove the rat image from the grid
//            System.out.println("Rat removed from row " + row + " and column " + col);
        });
        pause.play();
    }


    private void loadParasitesData() {
        for (Parasite parasite : parasiteManager.getParasites()) {
            MenuItem menuItem = new MenuItem(parasite.getName());
            menuItem.setOnAction(e -> handleParasiteSelection(parasite));
            parasiteMenuButton.getItems().add(menuItem);
        }
    }

    private void handleParasiteSelection(Parasite parasite) {
        // Implement what happens when a parasite is selected
        // For example, display details or apply effects to the garden
        System.out.println("Selected parasite: " + parasite.getName() + " with damage: " + parasite.getDamage());
    }

//
    @FXML
    public void showPestOnGrid() {}


    private void changeRainUI(RainEvent event) {
            // Update UI to reflect it's raining
            System.out.println("Changing UI to reflect rain event");
            rainStatusLabel.setText("Rain amount: "+ event.getAmount() + "mm");

            // Create a pause transition of 5 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> {
                // Update UI to reflect no rain after the event ends
                rainStatusLabel.setText("Sunny");
                System.out.println("Rain event ended, updating UI to show no rain.");
            });
            pause.play();
        }

    private void changeTemperatureUI(TemperatureEvent event) {
        // Update UI to reflect the temperature change
        System.out.println("Changing UI to reflect temperature event");
        temperatureStatusLabel.setText(event.getAmount() + "°F");

        // Create a pause transition of 5 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> {
            // Update UI to reflect optimal temperature after the event ends
            temperatureStatusLabel.setText("Optimal");
            System.out.println("Temperature event ended, updating UI to show optimal temperature.");
        });
        pause.play();
    }

    private void changeParasiteUI(ParasiteEvent event) {
        // Update UI to reflect parasite event
        System.out.println("Changing UI to reflect parasite event");
        parasiteStatusLabel.setText("Parasite: " + event.getParasite().getName() + " detected");

        // Create a pause transition of 5 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> {
            // Update UI to reflect no parasites after the event ends
            parasiteStatusLabel.setText("No Parasites");
            System.out.println("Parasite event ended, updating UI to show no parasites.");
        });
        pause.play();
    }

    //    This is the method that will populate the menu buttons with the plant data
    private void loadPlantsData() {

        for (Flower flower : plantManager.getFlowers()) {
            MenuItem menuItem = new MenuItem(flower.getName());
            menuItem.setOnAction(e -> addPlantToGrid(flower.getName(), flower.getCurrentImage()));
            flowerMenuButton.getItems().add(menuItem);
        }

        for (Tree tree : plantManager.getTrees()) {
            MenuItem menuItem = new MenuItem(tree.getName());
            menuItem.setOnAction(e -> addPlantToGrid(tree.getName(), tree.getCurrentImage()));
            treeMenuButton.getItems().add(menuItem);
        }

        for (Vegetable vegetable : plantManager.getVegetables()) {
            MenuItem menuItem = new MenuItem(vegetable.getName());
            menuItem.setOnAction(e -> addPlantToGrid(vegetable.getName(), vegetable.getCurrentImage()));
            vegetableMenuButton.getItems().add(menuItem);
        }


    }

    private void addPlantToGrid(String name, String imageFile) {
        Plant plant = plantManager.getPlantByName(name); // Assume this method retrieves the correct plant
        if (plant != null) {
            boolean placed = false;
            int attempts = 0;
            while (!placed && attempts < 100) { // Limit attempts to avoid potential infinite loop
                int row = random.nextInt(gardenGrid.getNumRows());
                int col = random.nextInt(gardenGrid.getNumCols());
                if (!gardenGrid.isSpotOccupied(row, col)) {
//                    Need row and col for logging
                    System.out.println("Placing " + name + " at row " + row + " col " + col);
                    plant.setRow(row);
                    plant.setCol(col);
                    gardenGrid.addPlant(plant, row, col);
                    ImageView plantView = new ImageView(new Image(getClass().getResourceAsStream("/images/" + imageFile)));
                    plantView.setFitHeight(40);
                    plantView.setFitWidth(40);

                    // Create a pane to center the image
                    StackPane pane = new StackPane();
                    pane.getChildren().add(plantView);
                    gridPane.add(pane, col, row);
                    placed = true;
                }
                attempts++;
            }
            if (!placed) {
                System.err.println("Failed to place the plant after 100 attempts, grid might be full.");
            }
        } else {
            System.err.println("Plant not found: " + name);
        }
    }



}
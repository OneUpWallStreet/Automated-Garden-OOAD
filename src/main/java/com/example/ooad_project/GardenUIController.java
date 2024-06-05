package com.example.ooad_project;

import com.example.ooad_project.Events.DisplayParasiteEvent;
import com.example.ooad_project.Events.PlantImageUpdateEvent;
import com.example.ooad_project.Events.RainEvent;
import com.example.ooad_project.Parasite.Parasite;
import com.example.ooad_project.Parasite.ParasiteManager;
import com.example.ooad_project.Plant.Children.Flower;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.Plant.Children.Tree;
import com.example.ooad_project.Plant.Children.Vegetable;
import com.example.ooad_project.Plant.PlantManager;
import com.example.ooad_project.ThreadUtils.EventBus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Random;


public class GardenUIController {

    @FXML
    private Button sidButton;

    @FXML
    private MenuButton parasiteMenuButton;

    @FXML
    private Button pestTestButton;

    @FXML
    private Label rainStatusLabel;
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
    public void initialize() {
        // Add ColumnConstraints
        for (int col = 0; col < gardenGrid.getNumCols(); col++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(60); // Adjust the width as needed
            gridPane.getColumnConstraints().add(colConst);
        }

        // Add RowConstraints
        for (int row = 0; row < gardenGrid.getNumRows(); row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(60); // Adjust the height as needed
            gridPane.getRowConstraints().add(rowConst);
        }

        // Load plants data from JSON file and populate MenuButtons
        loadPlantsData();
        loadParasitesData();

        EventBus.subscribe("RainEvent", event -> changeRainUI((RainEvent) event));
        EventBus.subscribe("DisplayParasiteEvent", event -> handleDisplayParasiteEvent((DisplayParasiteEvent) event));
        EventBus.subscribe("PlantImageUpdateEvent", event -> handlePlantImageUpdateEvent((PlantImageUpdateEvent) event));
    }

    private void handlePlantImageUpdateEvent(PlantImageUpdateEvent event) {
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
        newImageView.setFitHeight(60);  // Match the cell size in the grid
        newImageView.setFitWidth(60);

        // Place the new image view in the grid
        gridPane.add(newImageView, col, row);

        System.out.println("Updated plant image at row " + row + " and column " + col + " to " + imageName);
    }


    private void handleDisplayParasiteEvent(DisplayParasiteEvent event) {
        // Load the image for the rat
        String imageName = "/images/" + event.getParasite().getImageName();
        Image ratImage = new Image(getClass().getResourceAsStream(imageName));
        ImageView parasiteImageView = new ImageView(ratImage);

//        Change var name
        parasiteImageView.setFitHeight(60);  // Match the cell size in the grid
        parasiteImageView.setFitWidth(60);

        // Use the row and column from the event
        int row = event.getRow();
        int col = event.getColumn();

        // Place the rat image on the grid
        gridPane.add(parasiteImageView, col, row);
//        System.out.println("Rat placed at row " + row + " and column " + col);

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
            rainStatusLabel.setText("Rain Amount: " + event.getAmount() + "mm");

            // Create a pause transition of 5 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> {
                // Update UI to reflect no rain after the event ends
                rainStatusLabel.setText("No rain currently.");
                System.out.println("Rain event ended, updating UI to show no rain.");
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
                    plantView.setFitHeight(60);
                    plantView.setFitWidth(60);
                    gridPane.add(plantView, col, row);
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
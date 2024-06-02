package com.example.ooad_project;

import com.example.ooad_project.Plant.Children.Flower;
import com.example.ooad_project.Plant.Plant;
import com.example.ooad_project.Plant.Children.Tree;
import com.example.ooad_project.Plant.Children.Vegetable;
import com.example.ooad_project.Plant.PlantManager;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Random;



public class GardenUIController {

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
    private PlantManager plantManager = PlantManager.getInstance();


    public GardenUIController() {
        gardenGrid = GardenGrid.getInstance();
    }

//    This is the method that will print the grid
    @FXML
    public void printGrid(){
        gardenGrid.printGrid();
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
    }


//    This is the method that will populate the menu buttons with the plant data
    private void loadPlantsData() {

        PlantManager plantManager = PlantManager.getInstance();
        for (Flower flower : plantManager.getFlowers()) {
            MenuItem menuItem = new MenuItem(flower.getName());
            menuItem.setOnAction(e -> addPlantToGrid(flower.getName(), flower.getImageName()));
            flowerMenuButton.getItems().add(menuItem);
        }

        for (Tree tree : plantManager.getTrees()) {
            MenuItem menuItem = new MenuItem(tree.getName());
            menuItem.setOnAction(e -> addPlantToGrid(tree.getName(), tree.getImageName()));
            treeMenuButton.getItems().add(menuItem);
        }

        for (Vegetable vegetable : plantManager.getVegetables()) {
            MenuItem menuItem = new MenuItem(vegetable.getName());
            menuItem.setOnAction(e -> addPlantToGrid(vegetable.getName(), vegetable.getImageName()));
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
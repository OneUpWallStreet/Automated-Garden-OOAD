package com.example.ooad_project;

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


    private final int numRows = 10;
    private final int numCols = 10;
    private final Random random = new Random();
    private GardenGrid gardenGrid;
    private PlantManager plantManager = PlantManager.getInstance();

    public GardenUIController() {
        gardenGrid = GardenGrid.getInstance(numRows, numCols);
    }

    @FXML
    public void printGrid(){
        gardenGrid.printGrid();
    }

    @FXML
    public void initialize() {
        // Add ColumnConstraints
        for (int col = 0; col < numCols; col++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(30); // Adjust the width as needed
            gridPane.getColumnConstraints().add(colConst);
        }

        // Add RowConstraints
        for (int row = 0; row < numRows; row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(30); // Adjust the height as needed
            gridPane.getRowConstraints().add(rowConst);
        }

        // Load plants data from JSON file and populate MenuButtons
        loadPlantsData();
    }

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
        Plant plant = plantManager.getPlantByName(name); // This method needs to be implemented
        if (plant != null) {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numCols);
            try {
                if (!gardenGrid.isSpotOccupied(row, col)) {
                    gardenGrid.addPlant(plant, row, col);
                    ImageView plantView = new ImageView(new Image(getClass().getResourceAsStream("/images/" + imageFile)));
                    plantView.setFitHeight(30);
                    plantView.setFitWidth(30);
                    gridPane.add(plantView, col, row);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Spot at " + row + ", " + col + " is already occupied.");
            }
        }
    }


}
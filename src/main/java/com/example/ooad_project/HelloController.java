package com.example.ooad_project;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;



public class HelloController {

    @FXML
    private GridPane gridPane;
    @FXML
    private MenuButton vegetableMenuButton;
    @FXMLg
    private MenuButton flowerMenuButton;
    @FXML
    private MenuButton treeMenuButton;

    private final int numRows = 10;
    private final int numCols = 10;
    private final Random random = new Random();

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
        try {
            String content = new String(Files.readAllBytes(Paths.get("plants.json")));
            JSONObject jsonObject = new JSONObject(content);

            JSONArray flowers = jsonObject.getJSONArray("flowers");
            for (int i = 0; i < flowers.length(); i++) {
                JSONObject flower = flowers.getJSONObject(i);
                String name = flower.getString("name");
                MenuItem menuItem = new MenuItem(name);
                menuItem.setOnAction(e -> addPlantToGrid(name, "flower.png"));
                flowerMenuButton.getItems().add(menuItem);
            }

            JSONArray trees = jsonObject.getJSONArray("trees");
            for (int i = 0; i < trees.length(); i++) {
                JSONObject tree = trees.getJSONObject(i);
                String name = tree.getString("name");
                MenuItem menuItem = new MenuItem(name);
                menuItem.setOnAction(e -> addPlantToGrid(name, "flower.png"));
                treeMenuButton.getItems().add(menuItem);
            }

            JSONArray vegetables = jsonObject.getJSONArray("vegetables");
            for (int i = 0; i < vegetables.length(); i++) {
                JSONObject vegetable = vegetables.getJSONObject(i);
                String name = vegetable.getString("name");
                MenuItem menuItem = new MenuItem(name);
                menuItem.setOnAction(e -> addPlantToGrid(name, "flower.png"));
                vegetableMenuButton.getItems().add(menuItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPlantToGrid(String name, String imageFile) {
        // Load plant image (make sure the image path is correct)
        Image plantImage = new Image(getClass().getResourceAsStream("/images/" + imageFile));
        ImageView imageView = new ImageView(plantImage);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);

        // Find a random cell in the grid
        int row = random.nextInt(numRows);
        int col = random.nextInt(numCols);

        // Add the plant image to the grid
        gridPane.add(imageView, col, row);
    }
}
package com.example.ooad_project;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Random;

public class HelloController {

    @FXML
    private GridPane gridPane;

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
    }

    @FXML
    public void addRandomFlower() {
        // Load a flower image (make sure the image path is correct)
        Image flowerImage = new Image(getClass().getResourceAsStream("/images/flower.png"));
        ImageView imageView = new ImageView(flowerImage);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);

        // Find a random cell in the grid
        int row = random.nextInt(numRows);
        int col = random.nextInt(numCols);

        // Add the flower image to the grid
        gridPane.add(imageView, col, row);
    }
}
package com.example.ooad_project;

import com.example.ooad_project.Plant.Plant;

public class GardenGrid {
    private static GardenGrid instance = null;
    private Plant[][] plantGrid;

    private GardenGrid(int numRows, int numCols) {
        plantGrid = new Plant[numRows][numCols];
    }

    public static GardenGrid getInstance(int numRows, int numCols) {
        if (instance == null) {
            instance = new GardenGrid(numRows, numCols);
        }
        return instance;
    }

    public void printGrid() {

        System.out.println("\nGarden Grid: \n");

        for (int i = 0; i < plantGrid.length; i++) {
            for (int j = 0; j < plantGrid[i].length; j++) {
                if (plantGrid[i][j] != null) {
                    System.out.print(plantGrid[i][j].getName() + "\t");
                } else {
                    System.out.print("Empty\t");
                }
            }
            System.out.println();
        }
    }


    public void addPlant(Plant plant, int row, int col) {
        if (plantGrid[row][col] == null) {
            plantGrid[row][col] = plant;
        } else {
            System.out.println("Spot is already occupied" + "in row: " + row + " col: " + col);
            throw new IllegalArgumentException("Spot is already occupied");
        }
    }

    public boolean isSpotOccupied(int row, int col) {
        return plantGrid[row][col] != null;
    }

}
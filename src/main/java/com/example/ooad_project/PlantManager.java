package com.example.ooad_project;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Plant Manager will get plant data from the JSON file and store it in lists of flowers, trees, and vegetables
public class PlantManager {
    private static PlantManager instance;
    private List<Flower> flowers;
    private List<Tree> trees;
    private List<Vegetable> vegetables;

    private PlantManager() {
        flowers = new ArrayList<>();
        trees = new ArrayList<>();
        vegetables = new ArrayList<>();
        loadPlantsData();
    }

    public static synchronized PlantManager getInstance() {
        if (instance == null) {
            instance = new PlantManager();
        }
        return instance;
    }


    public Plant getPlantByName(String name) {
        // Check in flowers
        for (Flower flower : flowers) {
            if (flower.getName().equals(name)) return flower;
        }
        // Check in trees
        for (Tree tree : trees) {
            if (tree.getName().equals(name)) return tree;
        }
        // Check in vegetables
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getName().equals(name)) return vegetable;
        }
        return null; // Or throw an exception if preferred
    }


    private void loadPlantsData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("plants.json")));
            JSONObject jsonObject = new JSONObject(content);

            loadFlowers(jsonObject.getJSONArray("flowers"));
            loadTrees(jsonObject.getJSONArray("trees"));
            loadVegetables(jsonObject.getJSONArray("vegetables"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFlowers(JSONArray flowerData) {
        for (int i = 0; i < flowerData.length(); i++) {
            JSONObject flower = flowerData.getJSONObject(i);
            flowers.add(new Flower(flower.getString("name"), 100, flower.getInt("waterRequirement"), flower.getString("imageName")));
        }
    }

    private void loadTrees(JSONArray treeData) {
        for (int i = 0; i < treeData.length(); i++) {
            JSONObject tree = treeData.getJSONObject(i);
            trees.add(new Tree(tree.getString("name"), 100, tree.getInt("waterRequirement"), tree.getString("imageName")));
        }
    }

    private void loadVegetables(JSONArray vegetableData) {
        for (int i = 0; i < vegetableData.length(); i++) {
            JSONObject vegetable = vegetableData.getJSONObject(i);
            vegetables.add(new Vegetable(vegetable.getString("name"), 100, vegetable.getInt("waterRequirement"), vegetable.getString("imageName")));
        }
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public List<Vegetable> getVegetables() {
        return vegetables;
    }
}

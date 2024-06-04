package com.example.ooad_project.Parasite;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParasiteManager {
    private static ParasiteManager instance;
    private List<Parasite> parasites;

    private ParasiteManager() {
        parasites = new ArrayList<>();
        loadParasitesData();
    }

    public static synchronized ParasiteManager getInstance() {
        if (instance == null) {
            instance = new ParasiteManager();
        }
        return instance;
    }

    public Parasite getParasiteByName(String name) {
        for (Parasite parasite : parasites) {
            if (parasite.getName().equalsIgnoreCase(name)) {
                return parasite;
            }
        }
        return null; // Or throw an exception if preferred
    }

    private void loadParasitesData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("parasites.json")));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray parasitesArray = jsonObject.getJSONArray("parasites");

            for (int i = 0; i < parasitesArray.length(); i++) {
                JSONObject parasiteJson = parasitesArray.getJSONObject(i);
                JSONArray targetPlantsJsonArray = parasiteJson.getJSONArray("targetPlants");
                ArrayList<String> targetPlants = new ArrayList<>();

                // Explicitly cast each element to String
                for (int j = 0; j < targetPlantsJsonArray.length(); j++) {
                    targetPlants.add(targetPlantsJsonArray.getString(j));
                }

                // Create a new Parasite instance and add it to the list
                parasites.add(new Parasite(
                        parasiteJson.getString("name"),
                        parasiteJson.getInt("damage"),
                        parasiteJson.getString("imageName"),
                        targetPlants
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Parasite> getParasites() {
        return parasites;
    }
}

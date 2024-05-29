package com.example.ooad_project;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add the key-value pair to the JSON object
        jsonObject.put("album", "Led Zeppelin IV");

        // Create or overwrite the JSON file
        try (FileWriter file = new FileWriter("albums.json")) {
            file.write(jsonObject.toString());
            System.out.println("JSON file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
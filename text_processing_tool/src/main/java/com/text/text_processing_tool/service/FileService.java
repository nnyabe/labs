package com.text.text_processing_tool.service;

import com.text.text_processing_tool.models.FileManager;
import com.text.text_processing_tool.utils.AlertManager;
import com.text.text_processing_tool.utils.StageManager;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FileService implements FileManager {
    /**
     * 
     */
    @Override
    public String importFile() throws FileNotFoundException {
        Stage primaryStage = StageManager.getPrimaryStage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(primaryStage);

        if (file == null) {
            new AlertManager().showError("Error", "No file selected", "Please select a valid file to import.");
            return "";
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            new AlertManager().showError("Error", "File import failed",
                    "File import was unsuccessful: " + e.getMessage());
            throw new RuntimeException(e);
        }

        new AlertManager().showAlert("Import Success", "File import successful",
                "The file was imported successfully");

        return content.toString();
    }

    /**
     * 
     */
    @Override
    public void saveTextToFile(File file, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        } catch (IOException e) {
            new AlertManager().showError("Error", "File save failed", "There was an issue saving the Content: \n" + e.getMessage());

        }
    }

    /**
     * 
     */
    @Override
    public void saveFileAs() {
        Stage primaryStage = StageManager.getPrimaryStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setTitle("Save File");


        File file = fileChooser.showSaveDialog(primaryStage);


        if (file == null) {
            new AlertManager().showError("Error", "No file selected", "Please select a valid location to save the file.");
            return;
        }

        String textContent = "This is the text to be saved"; // Replace this with actual text from the UI

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textContent);
        } catch (IOException e) {
            new AlertManager().showError("Error", "File save failed", "There was an issue saving the file: " + e.getMessage());
            return;
        }

        new AlertManager().showAlert("Save Success", "File saved successfully", "The file has been saved at " + file.getAbsolutePath());

    }
}

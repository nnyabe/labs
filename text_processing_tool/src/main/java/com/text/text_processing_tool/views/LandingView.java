package com.text.text_processing_tool.views;

import com.text.text_processing_tool.service.DataService;
import com.text.text_processing_tool.service.FileService;
import com.text.text_processing_tool.service.RegexService;
import com.text.text_processing_tool.service.TextService;
import com.text.text_processing_tool.utils.AlertManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;

public class LandingView {

    @FXML
    private ListView<String> bookmarkField;
    @FXML
    private ComboBox replaceDropdown;

    @FXML
    private TextField regexInput;
    @FXML
    private TextField textSearch;
    @FXML
    private TextField textReplace;
    @FXML
    private TextField textFind;
    @FXML
    private  TextField regexReplace;
    @FXML
    private TextArea processedTextArea;
    @FXML
    private TextArea inputText;


    @FXML
    private Label wordCount;

    @FXML
    private Button regexSearchButton;
    @FXML
    private Button textSearchButton;
    @FXML
    private Button textReplaceButton;
    @FXML
    private Button analyzeButton;
    @FXML
    private Button importButton;
    @FXML
    private Button exportButton;
    @FXML
    private Button regexReplaceButton;
    @FXML
    private Button bookMarkButton;


    private final FileService fileService = new FileService();
    private final TextService textService = new TextService();
    private final AlertManager alert = new AlertManager();
    private final RegexService regexService = new RegexService();
    private final DataService dataService = new DataService();

    @FXML
    public void initialize(){
        regexSearchButton.setOnAction(e -> handleRegexSearch());
        textSearchButton.setOnAction(e -> handleTextSearch());
        textReplaceButton.setOnAction(e -> handleTextReplace());
        analyzeButton.setOnAction(e-> handleAnalyze());
        bookMarkButton.setOnAction(e -> handleBookMark());
        importButton.setOnAction(e -> {
            try {
                handleFileImport();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        exportButton.setOnAction(e -> handleFileExport());
        regexReplaceButton.setOnAction(e -> handleRegexReplace());

        if(inputText.getText().isEmpty()){
            wordCount.setText(""+0);
        }
        bookmarkField.getSelectionModel().selectedItemProperty().addListener((newValue) -> {
            String result= "";
            if(newValue != null){
               result = alert.selectActionOnBookMark();
            }
            if(result.equals("Delete")){
                dataService.deleteRecord(newValue.toString());
            }
        });
    }


    @FXML
    public void handleBookmarkClick() {

    }


    private void handleBookMark() {
        String key = alert.showInputDialog("Input Title", "Value to save", "Enter a value to save the bookmark with");

        if(processedTextArea.getText().isEmpty()){
            alert.showWarning("No data to save", "No value of bookmark", "You haven't processed any input to be saved.");
            return;
        }
            dataService.saveData(key, processedTextArea.getText());

        bookmarkField.getItems().clear();
        bookmarkField.getItems().addAll(dataService.getData().keySet());

    }

    private void handleRegexReplace() {
        String patternInput = regexInput.getText();
        String patternReplace = regexReplace.getText();
        if(patternReplace.isEmpty() || patternInput.isEmpty()){
            alert.showError("Empty Fields", "No pattern input", "Pattern fields for replacing not found");
        }
        updateInput();
        String text = regexService.replaceAllMatches(inputText.getText(), patternInput, patternReplace);
        processedTextArea.setText(text);
    }

    private void handleFileExport() {
        String processedText = processedTextArea.getText();
        if(!processedText.isEmpty()){
            fileService.saveFileAs();
        }
    }

    private void handleFileImport() throws FileNotFoundException {
        String importedText = fileService.importFile();
        if(importedText == null){
            alert.showWarning("Empty File", "Imported and empty file.",
                    "It appears you imported an empty file.");
        }
        inputText.setText(importedText);
    }



    private void handleAnalyze() {
        int counts = textService.countAll(inputText.getText());


        wordCount.setText("" + counts);
        processedTextArea.setText(inputText.getText());
    }

    private void handleTextReplace() {
        String text = "";
        if(textFind.getText().isEmpty() || textReplace.getText().isEmpty() ||  replaceDropdown.getValue() == null){
            alert.showError("Empty Fields", "Input values in empty fields", "Please make sure " +
                    "all the fields are filled");
            return;
        }
        updateInput();
        if(replaceDropdown.getValue().toString().equals("Replace All")){
           text =  textService.replaceAllText(inputText.getText(), textFind.getText(), textReplace.getText());
            processedTextArea.setText(text);
        }
        if(replaceDropdown.getValue().toString().equals("Replace First")){
            text = textService.replaceText(inputText.getText(), textFind.getText(), textReplace.getText());
            processedTextArea.setText(text);
        }
        int count = textService.countAll(text);
        wordCount.setText("" + count);

    }

    private void handleTextSearch() {
        if (textSearch.getText().isEmpty()){
            alert.showAlert("Empty Field", "Fill the values", "Please make sure to have entered all fields");
            return;
        }

        updateInput();
        List<String> matches = textService.findMatches(inputText.getText(), textSearch.getText());
        if(matches.isEmpty()){
            alert.showAlert("No match found", "Word doesn't have a match",
                    " The text filed seems not to have the word you typed, Please type the word very well");
        }else {
            alert.showAlert("Match Found", "Word found in the text",
                    "The text field had a match with the input word.");
        }

    }

    private void handleRegexSearch() {
        List<String> matches = new ArrayList<>();
        String regex = regexInput.getText();
        String input = inputText.getText();

        if(regex.isEmpty() || input.isEmpty()){
            alert.showWarning("Empty Field", "No search or input parameter", "Please provide a regex pattern or input text");
            return;
        }

        updateInput();
        if(regexService.hasMatch(input, regex)){
            matches = regexService.findRegexMatches(input, regex);
        }
        StringBuilder output = new StringBuilder();
        for(String word : matches)
            output.append(" ").append(word);

        processedTextArea.setText(output.toString());
        int count = regexService.countMatches(input, regex);
        wordCount.setText("" + count);

    }


    public void updateInput(){
        if(!processedTextArea.getText().isEmpty()){
            inputText.setText(processedTextArea.getText());
            processedTextArea.setText("");
        }
    }
}

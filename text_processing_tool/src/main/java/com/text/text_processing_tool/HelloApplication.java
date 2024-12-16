package com.text.text_processing_tool;

import com.text.text_processing_tool.services.TextService;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
//extends Application
public class HelloApplication  {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landing" +
//                "-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1220, 660);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }


    public static void main(String[] args) {
//        launch();
        System.out.println(new TextService().findMatches("Hello My friend My", "my"));
    }
}
package com.example.voyageur_commerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    void Continuer(MouseEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("plateau-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), 1000, 600);
        Stage stage = new Stage();
        stage.setScene(scene1);
        stage.setTitle("Construction du graphe");
        stage.show();
    }
}
package com.example.voyageur_commerce;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlateauController {

    @FXML
    private AnchorPane plateau;

    @FXML
    private AnchorPane plateau1;

    @FXML
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private Label labelcomplet;

    @FXML
    private Label labelheuristique;

    @FXML
    private TextField inputVal;

    @FXML
    private AnchorPane window;

    @FXML
    private Button valider;

    @FXML
    void relier(ActionEvent event) throws IOException {
      /*  for (int i = 0; i < Donnees.graphe.NbNoeuds(); i++) {
            for (int j = i+1; j < Donnees.graphe.NbNoeuds(); j++) {
                int val = Integer.parseInt(inputVal.getText());
                Arrete a1 = new Arrete(Donnees.graphe.getNoeud(i),Donnees.graphe.getNoeud(j),val);
                Arrete a2 = new Arrete(Donnees.graphe.getNoeud(j),Donnees.graphe.getNoeud(i),val);
                a2.setLine(a1.getLine());
                plateau.getChildren().add(a1.getLine());
                a1.getLine().setStartX(Donnees.graphe.getNoeud(i).getStackPane().getLayoutX() + 20);
                a1.getLine().setStartY(Donnees.graphe.getNoeud(i).getStackPane().getLayoutY() + 20);
                a1.getLine().setEndX(Donnees.graphe.getNoeud(j).getStackPane().getLayoutX() + 20);
                a1.getLine().setEndY(Donnees.graphe.getNoeud(j).getStackPane().getLayoutY() + 20);
                Donnees.graphe.addArrete(a1);
                Donnees.graphe.addArrete(a2);
            }
        }*/
        GridPane gridpane = new GridPane();
        for(int i = 1; i<=Donnees.graphe.NbNoeuds(); i++){
            Label l = new Label() ;
            l.setId("label"+i+"0");
            l.setText(""+i);
            l.setMinWidth(20);
            gridpane.add(l, 0, i);
            l.setAlignment(Pos.CENTER);

            Label l1 = new Label() ;
            l1.setId("label"+"0"+i);
            l1.setText(""+i);
            l1.setMinWidth(20);
            gridpane.add(l1, i, 0);
            l1.setAlignment(Pos.CENTER);


        }
        for(int i=1; i<=Donnees.graphe.NbNoeuds() ; i++){
            for(int j=i+1; j<=Donnees.graphe.NbNoeuds() ; j++) {
                TextField t = new TextField();
                t.setMinWidth(20);
                t.setPrefWidth(40);
                t.setMinHeight(20);
                t.setPrefHeight(20);
                t.setId("text" + i + j);
                gridpane.add(t, j, i);
            }
        }
        plateau1.getChildren().add(gridpane);
        plateau.setVisible(false);
        plateau1.setVisible(true);
        valider.setVisible(true);
    }

    @FXML
    private Label cout1;

    @FXML
    private Label cout2;


    @FXML
    void valider(ActionEvent event) {
        for (int i = 1; i <= Donnees.graphe.NbNoeuds(); i++) {
            for (int j = i+1; j <= Donnees.graphe.NbNoeuds(); j++) {
                TextField t = (TextField) plateau1.lookup("#text"+(i)+(j));
                int val = Integer.parseInt(t.getText());
                Arrete a1 = new Arrete(Donnees.graphe.getNoeud(i-1),Donnees.graphe.getNoeud(j-1),val);
                Arrete a2 = new Arrete(Donnees.graphe.getNoeud(j-1),Donnees.graphe.getNoeud(i-1),val);
                a1.setLine(a2.getLine());
                plateau.getChildren().add(a1.getLine());
                a1.getLine().setStartX(Donnees.graphe.getNoeud(i-1).getStackPane().getLayoutX() + 20);
                a1.getLine().setStartY(Donnees.graphe.getNoeud(i-1).getStackPane().getLayoutY() + 20);
                a1.getLine().setEndX(Donnees.graphe.getNoeud(j-1).getStackPane().getLayoutX() + 20);
                a1.getLine().setEndY(Donnees.graphe.getNoeud(j-1).getStackPane().getLayoutY() + 20);
                Donnees.graphe.addArrete(a1);
                Donnees.graphe.addArrete(a2);
            }
        }
        plateau1.setVisible(false);
        plateau.setVisible(true);
    }

    @FXML
    void AfficherResultats(ActionEvent event) {
        long tab1[] = Donnees.graphe.HEURISTIQUE_PVC();
        long tab2[] = Donnees.graphe.COMPLET_PVC();
        labelheuristique.setText(labelheuristique.getText()+" "+tab1[1]+" ms");
        cout2.setText(cout2.getText()+" "+tab1[0]);
        labelcomplet.setText(labelcomplet.getText()+" "+tab2[1]+" ms");
        cout1.setText(cout1.getText()+" "+tab2[0]);
    }

    @FXML
    void AjouterArrete(ActionEvent event) {
        int i = Integer.parseInt(input1.getText());
        int j = Integer.parseInt(input2.getText());
        int val = Integer.parseInt(inputVal.getText());
        Arrete a1 = new Arrete(Donnees.graphe.getNoeudByNum(i),Donnees.graphe.getNoeudByNum(j),val);
        Arrete a2 = new Arrete(Donnees.graphe.getNoeudByNum(j),Donnees.graphe.getNoeudByNum(i),val);
        a2.setLine(a1.getLine());
        plateau.getChildren().add(a1.getLine());
        a1.getLine().setStartX(Donnees.graphe.getNoeudByNum(i).getStackPane().getLayoutX() + 20);
        a1.getLine().setStartY(Donnees.graphe.getNoeudByNum(i).getStackPane().getLayoutY() + 20);
        a1.getLine().setEndX(Donnees.graphe.getNoeudByNum(j).getStackPane().getLayoutX() + 20);
        a1.getLine().setEndY(Donnees.graphe.getNoeudByNum(j).getStackPane().getLayoutY() + 20);
        Donnees.graphe.addArrete(a1);
        Donnees.graphe.addArrete(a2);
    }

    @FXML
    void AjouterNoeud(ActionEvent event) {
        Noeud n = new Noeud();
        plateau.setTopAnchor(n.getStackPane(),175.0);
        plateau.setLeftAnchor(n.getStackPane(),375.0);
        plateau.getChildren().add(n.getStackPane());
        plateau.setStyle("-fx-background-color:#808080");
        Donnees.graphe.addNoeud(n);
        n.getStackPane().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                plateau.setLeftAnchor(n.getStackPane(),mouseEvent.getSceneX());
                plateau.setTopAnchor(n.getStackPane(),mouseEvent.getSceneY());
            }
        });
    }

}

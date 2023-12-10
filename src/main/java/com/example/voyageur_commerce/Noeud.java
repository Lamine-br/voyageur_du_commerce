package com.example.voyageur_commerce;


import java.util.ArrayList;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;


public class Noeud {
    private int num ;

    private ArrayList<Noeud> adjcents ;
    private static int NbrNoeuds ;
    private StackPane stackPane;

    //Constructeur
    public Noeud(){
        NbrNoeuds ++ ;
        this.num = NbrNoeuds ;
        this.adjcents = new ArrayList<Noeud>() ;
        Circle circle = new Circle(20, Color.BISQUE);
        Text text = new Text(""+this.num);
        this.stackPane = new StackPane();
        this.stackPane.getChildren().addAll(circle,text);
    }

    //Getters
    public int getNum(){
        return this.num;
    }
    public ArrayList<Noeud> getAdjcents(){
        return this.adjcents ;
    }
    public int getNbrNoeuds(){
        return NbrNoeuds;
    }
    public StackPane getStackPane(){
        return this.stackPane;
    }

    //Setters
    public void setNum(int num){
        this.num = num ;
    }
    public void setAdjcents(ArrayList<Noeud> noeuds){
        this.adjcents = noeuds ;
    }
    public void setStackPane(StackPane c){
        this.stackPane = c ;
    }
    public void setNbrNoeuds(int i){
        NbrNoeuds = i ;
    }

    public Noeud getAdjacent(int i){
        return this.adjcents.get(i);
    }
    public void ajouterAdjacent(Noeud n){
        this.adjcents.add(n);
    }
    public void supprimerAdjacent(Noeud n){
        this.adjcents.remove(n);
    }
}

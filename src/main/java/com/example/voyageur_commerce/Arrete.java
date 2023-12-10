package com.example.voyageur_commerce;


import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Arrete {
    private int num;

    private int valeur ;
    private Noeud noeud1;
    private Noeud noeud2;
    private Line line;

    public Arrete(Noeud noeud1, Noeud noeud2 , int val) {
        this.num = noeud1.getNum() * 10000 + noeud2.getNum();
        this.valeur = val ;
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        noeud1.ajouterAdjacent(noeud2);
        this.setLine(new Line());
        this.line.setStroke(Color.BLACK);
        this.line.setStrokeWidth(3.0);
    }
    public int getNum() {
        return num;
    }
    public int getValeur(){return valeur;}
    public void setNum(int num) {
        this.num = num;
    }
    public void setValeur(int val){this.valeur = val;}
    public Noeud getNoeud1() {
        return noeud1;
    }
    public void setNoeud1(Noeud noeudDepart) {
        this.noeud1 = noeudDepart;
    }
    public Noeud getNoeud2() {
        return noeud2;
    }
    public void setNoeud2(Noeud noeudArrive) {
        this.noeud2 = noeudArrive;
    }
    public Line getLine() {
        return line;
    }
    public void setLine(Line line) {
        this.line = line;
    }

}

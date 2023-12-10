package com.example.voyageur_commerce;

import java.util.ArrayList;

public class Branche implements Comparable {
    private int cout ;
    private ArrayList<Arrete> arretes = new ArrayList<Arrete>();

    public Branche(int cout, ArrayList<Arrete> arretes) {
        this.cout = cout;
        this.arretes = arretes;
    }

    //Getters
    public int getCout(){
        return this.cout ;
    }
    public ArrayList<Arrete> getArretes(){
        return this.arretes ;
    }

    //Setters
    public void setCout(int c){
        this.cout = c ;
    }
    public void setArretes(ArrayList<Arrete> a){
        this.arretes = a ;
    }

    public void ajouter(Arrete a){
        this.arretes.add(a);
    }

    public void supprimer(int a){
        this.arretes.remove(a);
    }

    public void CalculerCout(){
        int cout = 0 ;
        for (int i = 0; i < this.arretes.size(); i++) {
            cout += this.arretes.get(i).getValeur() ;
        }
        this.cout = cout ;
    }

    public Arrete getArreteByNum(int n){
        for (int i=0; i<this.getArretes().size();i++){
            if (this.arretes.get(i).getNum()==n)
                return this.arretes.get(i);
        }
        return null;
    }

    public int contains(Noeud a){
        int nb = 0 ;
        for (int i = 0; i < this.arretes.size(); i++) {
            if(this.arretes.get(i).getNoeud2().getNum() == a.getNum()){
                nb++;
            }
        }
        return nb ;
    }

    public int compareTo(Object o) {
        Branche br = (Branche) o ;
        if(this.getCout()>br.getCout()){
            return 1;
        }else if(this.getCout()< br.getCout()){
            return -1;
        }else{
            return 0;
        }
    }
}

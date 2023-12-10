package com.example.voyageur_commerce;


import javafx.scene.paint.Color;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;


public class Graphe {
    private ArrayList<Noeud> noeuds;

    private ArrayList<Arrete> arretes;

    public Graphe() {
        this.noeuds = new ArrayList<Noeud>();
        this.arretes = new ArrayList<Arrete>();
    }

    public ArrayList<Noeud> getNoeuds() {
        return this.noeuds;
    }

    public ArrayList<Arrete> getArretes() {
        return this.arretes;
    }

    public void setNoeuds(ArrayList<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    public void setArretes(ArrayList<Arrete> arretes) {
        this.arretes = arretes;
    }

    public void addNoeud(Noeud n) {
        this.noeuds.add(n);
    }

    public void addArrete(Arrete a) {
        if (NbArretes() == 0) {
            this.arretes.add(0, a);
        } else {
            int i = 0;
            while (a.getValeur() > this.getArrete(i).getValeur() && i < this.NbArretes() - 1) {
                i++;
            }
            this.arretes.add(i, a);
        }
    }

    public int NbNoeuds() {
        return this.noeuds.size();
    }

    public int NbArretes() {
        return this.arretes.size();
    }


    public Noeud getNoeud(int i) {
        return this.noeuds.get(i);
    }

    public Arrete getArrete(int i) {
        return this.arretes.get(i);
    }


    public Noeud getNoeudByNum(int n) {
        for (int i = 0; i < this.getNoeuds().size(); i++) {
            if (this.getNoeud(i).getNum() == n)
                return this.getNoeud(i);
        }
        return null;
    }

    public Arrete getArreteByNum(int n) {
        for (int i = 0; i < this.getArretes().size(); i++) {
            if (this.getArrete(i).getNum() == n)
                return this.getArrete(i);
        }
        return null;
    }

    public long[] COMPLET_PVC() {
        ArrayList<Branche> list = new ArrayList<Branche>();
        ArrayList<Arrete> array = new ArrayList<Arrete>();
        Branche br = new Branche(0, array);
        Noeud depart = new Noeud();
        Arrete r = new Arrete(depart, this.getNoeud(0), 0);
        br.ajouter(r);
        list.add(br);
        ArrayList<Branche> results = new ArrayList<>();

        // Temps d'exécution
        long startTime = System.currentTimeMillis();

        while (!list.isEmpty() ) {
            int cout = list.get(0).getCout();
            if (list.get(0).contains(this.getNoeud(0))==2) {
           /*     System.out.println("");
                for (int j = 1; j < list.get(0).getArretes().size(); j++) {
                    System.out.print(list.get(0).getArretes().get(j).getNoeud1().getNum()+""+list.get(0).getArretes().get(j).getNoeud2().getNum()+" ");
                }*/
                if(list.get(0).getArretes().size() == this.NbNoeuds() + 1){
                    results.add(list.get(0));
                    list.remove(0);
                }else{
                    list.remove(0);
                }
            } else {
                Branche b = list.remove(0);
                Noeud n1 = b.getArretes().get(b.getArretes().size() - 1).getNoeud2();
                for (int i = 0; i < n1.getAdjcents().size(); i++) {
                    for (int j = 0; j < this.arretes.size(); j++) {
                        int c = 0 ;
                        if(n1.getAdjacent(i).getNum()!=1){
                            if (this.getArrete(j).getNoeud1().getNum() == n1.getNum() && n1.getAdjacent(i).getNum() == this.getArrete(j).getNoeud2().getNum() && b.contains(n1.getAdjacent(i))<1) {
                                ArrayList<Arrete> newChemin = (ArrayList<Arrete>) b.getArretes().clone();
                                newChemin.add(this.getArrete(j));
                                Branche newBranche = new Branche(0, newChemin);
                                newBranche.CalculerCout();
                                list.add(newBranche);
                            }
                        }else{
                            if (this.getArrete(j).getNoeud1().getNum() == n1.getNum() && n1.getAdjacent(i).getNum() == this.getArrete(j).getNoeud2().getNum() && b.contains(n1.getAdjacent(i))<=1) {
                                ArrayList<Arrete> newChemin = (ArrayList<Arrete>) b.getArretes().clone();
                                newChemin.add(this.getArrete(j));
                                Branche newBranche = new Branche(0, newChemin);
                                newBranche.CalculerCout();
                                list.add(newBranche);
                            }
                        }

                    }
                }
            }
        }
        Collections.sort(results);
        for (int h = 1; h < results.get(0).getArretes().size(); h++) {
            if(results.get(0).getArretes().get(h).getLine().getStroke()==Color.BLUE){
                results.get(0).getArretes().get(h).getLine().setStroke(Color.PURPLE);
            }else{
                results.get(0).getArretes().get(h).getLine().setStroke(Color.RED);
            }
        }

        //Temps d'exécution
        long endTime = System.currentTimeMillis();

        System.out.println("\nLes résultats sont :");
        for (int j = 1; j < results.get(0).getArretes().size(); j++) {
            System.out.print(results.get(0).getArretes().get(j).getNoeud1().getNum()+""+results.get(0).getArretes().get(j).getNoeud2().getNum()+" ");
        }

        long time = endTime-startTime;
        System.out.println("\nOpération Terminée. Temps d'execution complet : "+(time)+ " ms " + "Cout : "+results.get(0).getCout());

        long tab[] = new long[2];
        tab[0] = results.get(0).getCout();
        tab[1] = time;
        return tab;
    }

    public long[] HEURISTIQUE_PVC() {
        ArrayList<Branche> list = new ArrayList<Branche>();
        ArrayList<Arrete> array = new ArrayList<Arrete>();
        Branche br = new Branche(0, array);
        Noeud depart = new Noeud();
        Arrete r = new Arrete(depart, this.getNoeud(0), 0);
        br.ajouter(r);
        list.add(br);
        ArrayList<Branche> results = new ArrayList<>();

        // Temps d'exécution
        long startTime = System.currentTimeMillis();

        boolean found = false;
        while (!list.isEmpty() && found == false) {
            int cout = list.get(0).getCout();
            if (list.get(0).contains(this.getNoeud(0))==2) {
                if(list.get(0).getArretes().size() == this.NbNoeuds() + 1){
                    results.add(list.get(0));
                    list.remove(0);
                }else{
                    list.remove(0);
                }
            } else {
                Branche b = list.remove(0);
                Noeud n1 = b.getArretes().get(b.getArretes().size() - 1).getNoeud2();

                for (int i = 0; i < n1.getAdjcents().size(); i++) {
                    for (int j = 0; j < this.arretes.size(); j++) {
                        int c = 0 ;
                        if(n1.getAdjacent(i).getNum()!=1){
                            if (this.getArrete(j).getNoeud1().getNum() == n1.getNum() && n1.getAdjacent(i).getNum() == this.getArrete(j).getNoeud2().getNum() && b.contains(n1.getAdjacent(i))<1) {
                                ArrayList<Arrete> newChemin = (ArrayList<Arrete>) b.getArretes().clone();
                                newChemin.add(this.getArrete(j));
                                Branche newBranche = new Branche(0, newChemin);
                                newBranche.CalculerCout();
                                boolean done = false ;
                                for (int k = 0; k < list.size(); k++) {
                                    if(list.get(k).getArretes().get(list.get(k).getArretes().size()-1).getNoeud2().getNum()==newBranche.getArretes().get(newBranche.getArretes().size()-1).getNoeud2().getNum()){
                                        done = true ;
                                        if(newBranche.getCout()<=list.get(k).getCout()){
                                            break;
                                        }else{
                                            list.remove(k);
                                            list.add(newBranche);
                                            break;
                                        }
                                    }
                                }
                                if(!done){
                                    list.add(newBranche);
                                }
                            }
                        }else{
                            if (this.getArrete(j).getNoeud1().getNum() == n1.getNum() && n1.getAdjacent(i).getNum() == this.getArrete(j).getNoeud2().getNum() && b.contains(n1.getAdjacent(i))<=1) {
                                ArrayList<Arrete> newChemin = (ArrayList<Arrete>) b.getArretes().clone();
                                newChemin.add(this.getArrete(j));
                                Branche newBranche = new Branche(0, newChemin);
                                newBranche.CalculerCout();
                                list.add(newBranche);
                            }
                        }
                    }
                }
                Collections.sort(list);
            }
        }
        Collections.sort(results);
        for (int h = 1; h < results.get(0).getArretes().size(); h++) {
            results.get(0).getArretes().get(h).getLine().setStroke(Color.BLUE);
        }

        //Temps d'exécution
        long endTime = System.currentTimeMillis();

        System.out.println("\nLes résultats sont :");
        for (int i = 0; i < results.size(); i++) {
            System.out.println("");
            for (int j = 1; j < results.get(i).getArretes().size(); j++) {
                System.out.print(results.get(i).getArretes().get(j).getNoeud1().getNum()+""+results.get(i).getArretes().get(j).getNoeud2().getNum()+" ");
            }
        }

        long time = endTime-startTime ;
        System.out.println("\nOpération Terminée. Temps d'execution heuristique : "+(time)+ " ms " + "Cout : "+results.get(0).getCout());

        long tab[] = new long[2];
        tab[0] = results.get(0).getCout();
        tab[1] = time;
        return tab;
    }

}
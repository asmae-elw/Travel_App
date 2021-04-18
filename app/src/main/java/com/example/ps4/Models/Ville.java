package com.example.ps4.Models;

public class Ville {
    private String nom_ville ;
    private String nom_pays ;

    public Ville(String nom_ville, String nom_pays) {
        this.nom_ville = nom_ville;
        this.nom_pays = nom_pays;
    }
    public Ville(){

    }

    public String getNom_ville() {
        return nom_ville;
    }

    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }

    public String getNom_pays() {
        return nom_pays;
    }

    public void setNom_pays(String nom_pays) {
        this.nom_pays = nom_pays;
    }

    @Override
    public String toString() {
        return this.nom_pays + " " + this.nom_ville;
    }
}

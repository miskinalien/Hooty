package com.example.morelle.e4fi_android_hoot.BDD;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by ESTEL on 23/01/2017.
 */


@Table
public class Voyage extends SugarRecord {
    private Long id;
    String titre, date, lieu, description;
    byte[] image;
    float Budget;
    DataBaseHelper db;


    public Voyage(){
        this.titre = "";
        this.date = "";
        this.lieu = "";
        this.description ="";
        this.image = null;
        this.Budget = 0;

    }




    public Voyage(String titre, String date, String lieu, String description, byte[] image) {
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.Budget = 0;

    }

    public Voyage(String titre, String date, String lieu, String description) {
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.image = null;
        this.Budget = 0;

    }





    public float getBudget(){
        return Budget;
    }

    public void setBudget(float budget){
        this.Budget = budget;
        this.save();
    }

    public String getTitre()
    {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu()
    {
        return this.lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int SommeDepense(){
        int total = 0;
        for (Depenses d: db.getDepenses(getId())) {
            total += d.getSomme();
        }
        return total;
    }

    public float ArgentRestant(){
        return Budget - SommeDepense();
    }






}

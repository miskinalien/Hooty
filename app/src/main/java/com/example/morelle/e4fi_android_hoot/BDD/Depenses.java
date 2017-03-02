package com.example.morelle.e4fi_android_hoot.BDD;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by ESTEL on 17/02/2017.
 */
@Table
public class Depenses extends SugarRecord {

    float somme;
    String titre;
    Long voyageId;

    public Depenses(){

    }
    public Depenses(String titre, int somme, Long voyageId){
        this.titre = titre;
        this.somme = somme;
        this.voyageId= voyageId;


    }

    public float getSomme() {
        return somme;
    }

    public void setSomme(float somme) {
        this.somme = somme;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }





}

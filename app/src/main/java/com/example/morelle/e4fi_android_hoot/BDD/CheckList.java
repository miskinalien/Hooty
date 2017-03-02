package com.example.morelle.e4fi_android_hoot.BDD;

import com.orm.SugarRecord;

import java.security.cert.Certificate;
import java.util.List;

/**
 * Created by ESTEL on 18/02/2017.
 */

public class CheckList extends SugarRecord {
    String titre;
    Long voyageId;
    private Long id;


    public CheckList(){

    }

    public CheckList(String titre, Long voyageId){

        this.titre = titre;
        this.voyageId = voyageId;
    }



    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}

package com.example.morelle.e4fi_android_hoot.BDD;

import java.util.List;

/**
 * Created by ESTEL on 19/02/2017.
 */

public class DataBaseHelper  {

    private static Voyage currentVoyage;
    private  static Billet currentBillet;
    private  static CheckList currentCheckList;

    //Voyage Liste
    public static Voyage getCurrentVoyage() {
        return currentVoyage;
    }
    public static void setCurrentVoyage(Voyage _currentVoyage) {
        currentVoyage = _currentVoyage;
    }

    public static List<Voyage> getVoyageList() {
        return Voyage.listAll(Voyage.class);
    }

    //Voyage
    public static List<Depenses> getDepenses(Long voyageId) {
        return Depenses.findWithQuery(Depenses.class, "SELECT * FROM DEPENSES where voyage_Id = ?", Long.toString(voyageId));
    }

    public static List<Billet> getBillets(Long voyageId) {
        return Billet.findWithQuery(Billet.class, "SELECT * FROM BILLET where voyage_Id = ?", Long.toString(voyageId));
    }

    public static List<CheckList> getCheckList(Long voyageId) {
        return CheckList.findWithQuery(CheckList.class, "SELECT * FROM CHECK_LIST where voyage_Id = ?", Long.toString(voyageId));
    }
    public static Voyage getVoyage(Long id){
        return Voyage.findById(Voyage.class,id);
    }




    //Checkliste
    public static List<CheckElement> getCheckElements(Long checkListeId)
    {
        return CheckElement.findWithQuery(CheckElement.class, "SELECT * FROM CHECK_ELEMENT where check_List_Id = ?",  Long.toString(checkListeId));
    }

    public static CheckList getCurrentCheckList() {
        return currentCheckList;
    }

    public static void setCurrentCheckList(CheckList _currentCheckList) {
        currentCheckList = _currentCheckList;
    }

    //Billet
    public static Billet getBillet(Long id){
        return Billet.findById(Billet.class,id);
    }
    public static Billet getCurrentBillet() {
        return currentBillet;
    }

    public static void setCurrentBillet(Billet _currentBillet) {
        currentBillet = _currentBillet;
    }


    //CheckElement
    public static CheckElement getCheckElement(Long id){
        return CheckElement.findById(CheckElement.class,id);
    }
}

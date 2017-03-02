package com.example.morelle.e4fi_android_hoot.BDD;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Table
public class Billet extends SugarRecord{

    private Long id;
    String title, description;
    String  date;
    Long voyageId;


    public Billet(){
        this.title = "";
        this.date = "";
        this.description = "";
        voyageId = 0L;
    }

    public Billet(String title, String description, String date, Long voyageId) {
        this.title = title;

        this.date =date;
        this.description = description;
        this.voyageId = voyageId;
    }

    public String getDate() {

        return date.toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

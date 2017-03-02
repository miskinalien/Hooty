package com.example.morelle.e4fi_android_hoot.BDD;

import com.google.android.gms.common.api.BooleanResult;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ESTEL on 23/01/2017.
 */

@Table
public class CheckElement extends SugarRecord {

    public String eltext;
    public Boolean done;
    public Long checkListId;


    public CheckElement()
    {
        eltext = "";
        done = false;
        checkListId = 0L;
    }

    public CheckElement(String _eltext, Boolean _done, Long _checkListId) {
        this.eltext = _eltext;
        this.done = _done;
        this.checkListId = _checkListId;
    }

    public void setDone(boolean b){
        this.done = b;
    }
}

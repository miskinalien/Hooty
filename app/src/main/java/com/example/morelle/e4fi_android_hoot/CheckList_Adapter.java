package com.example.morelle.e4fi_android_hoot;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.CheckElement;
import com.example.morelle.e4fi_android_hoot.BDD.CheckList;

import java.util.List;

/**
 * Created by ESTEL on 18/02/2017.
 */

public class CheckList_Adapter  extends ArrayAdapter<CheckList> {

    public CheckList_Adapter(Context context, int resource, List<CheckList> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View root = inflater.inflate(R.layout.voyage_checklist, null);
        CheckList e = getItem(position);
        TextView titre = (TextView) root.findViewById(R.id.checkliste_nom);
        titre.setText(e.getTitre());

        return root;
    }
}

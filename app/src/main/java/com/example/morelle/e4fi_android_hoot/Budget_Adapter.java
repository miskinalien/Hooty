package com.example.morelle.e4fi_android_hoot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.Depenses;


import java.util.List;

/**
 * Created by ESTEL on 08/02/2017.
 */

public class Budget_Adapter extends ArrayAdapter<Depenses> {

    public Budget_Adapter(Context context, int resource, List<Depenses> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View root = inflater.inflate(R.layout.depense_item, null);
        Depenses d = getItem(position);
        TextView titre = (TextView)root.findViewById(R.id.depense_titre);
        TextView somme= (TextView)root.findViewById(R.id.depense_somme);
        titre.setText(d.getTitre());
        somme.setText("-"+Float.toString(d.getSomme()));


        return root;
    }
}

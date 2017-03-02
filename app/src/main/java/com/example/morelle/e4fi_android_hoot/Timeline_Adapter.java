package com.example.morelle.e4fi_android_hoot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.Billet;


import java.util.List;

/**
 * Created by ESTEL on 08/02/2017.
 */

public class Timeline_Adapter extends ArrayAdapter<Billet> {

    public Timeline_Adapter(Context context, int resource, List<Billet> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View root = inflater.inflate(R.layout.voyage_billets, null);
        Billet p = getItem(position);
        TextView item_nom = (TextView)root.findViewById(R.id.billet_nom);
        TextView item_date= (TextView)root.findViewById(R.id.billet_date);
        item_nom.setText(p.getTitle());
        item_date.setText(p.getDate());

        return root;
    }
}

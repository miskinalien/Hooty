package com.example.morelle.e4fi_android_hoot;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.Voyage;

import java.util.List;

/**
 * Created by MORELLE on 20/01/2017.
 */

public class Voyage_Item_Adapter extends ArrayAdapter<Voyage>{

       public Voyage_Item_Adapter(Context context, int resource, List<Voyage> objects) {
        super(context, resource, objects);
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View root = inflater.inflate(R.layout.voyage_item, null);
        Voyage p = getItem(position);
        TextView item_nom = (TextView)root.findViewById(R.id.voyage_nom);
        TextView item_lieu = (TextView)root.findViewById(R.id.voyage_lieu);
        item_nom.setText(p.getTitre());
        item_lieu.setText(p.getDate());

        ImageView voyage_image = (ImageView)root.findViewById(R.id.voyage_image);
        if(p.getImage() != null)
            voyage_image.setImageBitmap(ImageUtile.convertByteArrayToBitmap(p.getImage()));
        return root;
    }



}

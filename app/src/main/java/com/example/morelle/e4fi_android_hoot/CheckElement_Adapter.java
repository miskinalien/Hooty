package com.example.morelle.e4fi_android_hoot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.morelle.e4fi_android_hoot.BDD.CheckElement;

import java.util.List;

/**
 * Created by MORELLE on 15/02/2017.
 */

public class CheckElement_Adapter extends ArrayAdapter<CheckElement> {


    public CheckElement_Adapter(Context context, int resource, List<CheckElement> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View root = inflater.inflate(R.layout.voyage_checkelement, null);
        final CheckElement e = getItem(position);
        final CheckBox checkBox = (CheckBox )root.findViewById(R.id.check);
        checkBox.setText(e.eltext);
        checkBox.setChecked(e.done);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.done = checkBox.isChecked();
                e.save();
            }
        });

        return root;
    }



}

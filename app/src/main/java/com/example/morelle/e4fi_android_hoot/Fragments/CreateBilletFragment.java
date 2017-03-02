package com.example.morelle.e4fi_android_hoot.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.morelle.e4fi_android_hoot.BDD.Billet;
import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.DateUtility;
import com.example.morelle.e4fi_android_hoot.ImageUtile;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class CreateBilletFragment extends Fragment {

    private Button add_bt;
    private DataBaseHelper db;

    public CreateBilletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_billet, container, false);
        add_bt = (Button) root.findViewById(R.id.add);
        getActivity().setTitle("Histoire" );
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_OnClick();
            }
        });
    }

    private void Add_OnClick()
    {
        EditText editTitre = (EditText) getView().findViewById(R.id.billet_titre) ;
        EditText editDesc= (EditText) getView().findViewById(R.id.billet_desc) ;
        Billet b;
        b = new Billet(editTitre.getText().toString(),editDesc.getText().toString(), DateUtility.getDate(System.currentTimeMillis(), "EEEE dd MMM yyyy") ,db.getCurrentVoyage().getId());
        Billet.save(b);
        MainActivity parent = (MainActivity)getActivity();
        parent.onItemClick(db.getCurrentVoyage());


    }










}

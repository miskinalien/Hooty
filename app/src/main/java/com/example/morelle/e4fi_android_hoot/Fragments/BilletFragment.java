package com.example.morelle.e4fi_android_hoot.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.Billet;
import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.ImageUtile;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;
import com.example.morelle.e4fi_android_hoot.Timeline_Adapter;

public class BilletFragment extends Fragment {



    private FloatingActionButton edit;
    private EditText des;
    private EditText titre;
    private boolean isEdit ;
    private DataBaseHelper db;
    private Long BilletId;
    private FloatingActionButton delete;
    private deleteBilletListener deleteListener;

    public interface deleteBilletListener {

        void deleteBillet();
    }


    public BilletFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BilletId = db.getCurrentBillet().getId();
        getActivity().setTitle("Billet :  " + db.getBillet(BilletId).getTitle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_billet, container, false);
         des = (EditText) root.findViewById(R.id.billet_desc);
         titre = (EditText) root.findViewById(R.id.billet_titre);
         edit = (FloatingActionButton) root.findViewById(R.id.edit_billet);
        delete = (FloatingActionButton) root.findViewById(R.id.delete_billet);
        isEdit = false;
        des.setFocusable(false);
        titre.setFocusable(false);
        Billet b = db.getBillet(BilletId);
        des.setText(b.getDescription());
        titre.setText(b.getTitle());

        return root;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEdit = !isEdit;
                if(isEdit) {
                    des.setFocusableInTouchMode(true);
                    titre.setFocusableInTouchMode(true);
                    edit.setImageResource(R.drawable.ic_done_white_48dp);
                    delete.setVisibility(View.VISIBLE);
                }
                else{
                    Billet b =db.getBillet(BilletId);
                    b.setDescription(des.getText().toString());
                    b.setTitle(titre.getText().toString());
                    b.save();
                    delete.setVisibility(View.INVISIBLE);
                    des.setFocusable(false);
                    titre.setFocusable(false);
                    edit.setImageResource(R.drawable.ic_mode_edit_white_48dp);
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEdit) {
                    deleteListener.deleteBillet();
                    isEdit = !isEdit;
                }
            }
        });
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof deleteBilletListener) {
            deleteListener = (deleteBilletListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BilletListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        deleteListener = null;
    }



}

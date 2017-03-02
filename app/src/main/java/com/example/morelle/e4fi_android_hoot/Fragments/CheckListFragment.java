package com.example.morelle.e4fi_android_hoot.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.Billet;
import com.example.morelle.e4fi_android_hoot.BDD.CheckList;
import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.Budget_Adapter;
import com.example.morelle.e4fi_android_hoot.CheckListDialog;
import com.example.morelle.e4fi_android_hoot.CheckList_Adapter;
import com.example.morelle.e4fi_android_hoot.DepenseDialog;
import com.example.morelle.e4fi_android_hoot.ImageUtile;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;
import com.example.morelle.e4fi_android_hoot.Timeline_Adapter;

public class CheckListFragment extends Fragment {


    public interface CheckListFragmentListener {
        void onItemClick(CheckList checklist);
    }



    private ListView listView ;
    private CheckList_Adapter adapter;
    private CheckListFragmentListener listener;
    private Long voyageId;
    private DataBaseHelper db;

    private FloatingActionButton addCheckListe;

    public CheckListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Check List de " + db.getCurrentVoyage().getTitre());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        voyageId =  db.getCurrentVoyage().getId();
        View root =  inflater.inflate(R.layout.fragment_check_list, container, false);
        addCheckListe = (FloatingActionButton) root.findViewById(R.id.add_checkliste);
        listView = (ListView) root.findViewById(R.id.listView_checklist);
        adapter = new CheckList_Adapter(getActivity(),R.layout.voyage_checklist, db.getCheckList(voyageId));
        listView.setAdapter(adapter);
        return root;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //list item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                CheckList item = (CheckList) parent.getItemAtPosition(position);
                listener.onItemClick(item);

            }
        });
        addCheckListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.DialogFragment dd = new CheckListDialog();
                dd.show(getFragmentManager(), "CheckListDialog");
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CheckListFragmentListener) {
            listener = (CheckListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CheckListFragmentListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }



}

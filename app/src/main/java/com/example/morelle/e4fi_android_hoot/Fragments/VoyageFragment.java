package com.example.morelle.e4fi_android_hoot.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;
import com.example.morelle.e4fi_android_hoot.Voyage_Item_Adapter;

public class VoyageFragment extends Fragment {
    public interface VoyageFragmentListener {
        void onItemClick(Voyage voyage);
    }
    private ListView listView;
    private Voyage_Item_Adapter adapter;
    private VoyageFragmentListener listener;
    private DataBaseHelper db;


    public static VoyageFragment newInstance() {
        return new VoyageFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Mes voyages");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_voyage, container, false);
        //View main =  inflater.inflate(R.layout.activity_main, container, false);
        listView = (ListView) root.findViewById(R.id.listView);
        adapter = new Voyage_Item_Adapter(getActivity(),R.layout.voyage_item, db.getVoyageList());
        listView.setAdapter(adapter);
        FloatingActionButton button = (FloatingActionButton) root.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)(getActivity())).switchFragment(new CreateVoyageFragment());
            }
        });


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
                Voyage item = (Voyage) parent.getItemAtPosition(position);
                listener.onItemClick(item);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (VoyageFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement peopleListener");
        }
    }



}

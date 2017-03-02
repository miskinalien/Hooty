package com.example.morelle.e4fi_android_hoot.Fragments;

import android.content.Context;
import android.content.Intent;
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
import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.ImageUtile;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;
import com.example.morelle.e4fi_android_hoot.Timeline_Adapter;


public class TimelineFragment extends Fragment {
    public interface TimeLineFragmentListener {
        void onItemClick(Billet billet);
    }

    public interface createBilletListener {
        void createBillet();
    }

    public interface deleteVoyageListener {
        void deleteVoyage();
    }

    private ListView listView ;
    private Timeline_Adapter adapter;
    private createBilletListener listenerCreateBillet;
    private deleteVoyageListener deleteVoyage;
    private TimeLineFragmentListener listener;
    private Long voyaeId;
    private DataBaseHelper db;
    private FloatingActionButton delete;
    private FloatingActionButton add;
    private Long voyageId;



    public static TimelineFragment newInstance() {

        TimelineFragment fragment = new TimelineFragment();
        return fragment;
    }

    public TimelineFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        voyaeId =  db.getCurrentVoyage().getId();
        getActivity().setTitle("TimeLine de " + db.getCurrentVoyage().getTitre());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        voyageId = db.getCurrentVoyage().getId();
        View root =  inflater.inflate(R.layout.fragment_timeline, container, false);
        ImageView image = (ImageView) root.findViewById(R.id.voyage_image);
        delete = (FloatingActionButton) root.findViewById(R.id.delete_voyage);
        Voyage v = Voyage.findById(Voyage.class,voyaeId);
        TextView nom = (TextView) root.findViewById(R.id.voyage_nom);
        nom.setText(v.getTitre());
        if(v.getImage() != null)
        image.setImageBitmap(ImageUtile.convertByteArrayToBitmap(v.getImage()));
        listView = (ListView) root.findViewById(R.id.listView);
        adapter = new Timeline_Adapter(getActivity(),R.layout.voyage_billets, db.getBillets(voyaeId));
        listView.setAdapter(adapter);

        add = (FloatingActionButton) root.findViewById(R.id.fab);



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
                Billet item = (Billet) parent.getItemAtPosition(position);
                listener.onItemClick(item);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerCreateBillet.createBillet();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVoyage.deleteVoyage();

            }
        });



    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (TimeLineFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement Timelinelistener");
        }
        try{
            listenerCreateBillet = (createBilletListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement Timelinelistener");
        }

        try{
            deleteVoyage = (deleteVoyageListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement deleteVoyageListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
       listener = null;
    }

    public void updateContent(Long voyageId){
        this.voyageId = voyageId;
    }
}

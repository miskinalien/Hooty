package com.example.morelle.e4fi_android_hoot.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import com.example.morelle.e4fi_android_hoot.BDD.CheckList;
import com.example.morelle.e4fi_android_hoot.CheckListDialog;
import com.example.morelle.e4fi_android_hoot.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Switch switch1, switch3, switch4 ;
    private OnFragmentInteractionListener mListener;

    public OptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlbumFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionFragment newInstance(String param1, String param2) {
        OptionFragment fragment = new OptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_option, container, false);
        switch1 = (Switch) root.findViewById(R.id.switch1);
        switch3 = (Switch) root.findViewById(R.id.switch3);
        switch4 = (Switch) root.findViewById(R.id.switch4);
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onOptionFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onOptionFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch3.isChecked() && switch4.isChecked())
                {
                    switch4.toggle();
                }
            }
        });

        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch1.isChecked() && switch4.isChecked())
                {
                    switch1.toggle();
                }
            }
        });

        switch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch1.isChecked() && switch3.isChecked())
                {
                    switch3.toggle();
                }
            }
        });



    }
}

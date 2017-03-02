package com.example.morelle.e4fi_android_hoot.Fragments;

import android.app.FragmentTransaction;
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
import android.widget.RelativeLayout;

import com.example.morelle.e4fi_android_hoot.BDD.Billet;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;


public class VoyageDetailFragment extends Fragment {

   public interface TimeLineListener{
       void onTimeLineClick();
   }
    public interface BudgetListener {
        void onBudgetClick();
    }
    public interface checkListListener {
        void onCheckClick();
    }
    private Button tab_bt_budget;
    private  Button tab_bt;
    private Button tab_bt_timeline;

    private TimeLineListener listener;
    private BudgetListener budgetListener;
    private checkListListener checkListListener;


    public VoyageDetailFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_voyage_detail, container, false);
        tab_bt = (Button) root.findViewById(R.id.check_tab);
        tab_bt_budget = (Button) root.findViewById(R.id.check_tab_budget);
        tab_bt_timeline = (Button) root.findViewById(R.id.check_tab_timeline);

        TimelineFragment fr = new TimelineFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.add(R.id.switch_fragment, fr);
        transaction.commit();

        return root;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tab_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkListListener.onCheckClick();
            }
        });
        tab_bt_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budgetListener.onBudgetClick();
            }
        });
        tab_bt_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTimeLineClick();
            }
        });

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (TimeLineListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement Timelinelistener");
        }
        try{
            budgetListener = (BudgetListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement budgetListener");
        }
        try{
            checkListListener = (checkListListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement checklistListener");
        }

    }



    @Override
    public void onDetach() {
        super.onDetach();
        budgetListener = null;
        checkListListener= null;

    }



}

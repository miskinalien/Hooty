package com.example.morelle.e4fi_android_hoot.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Depenses;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.Budget_Adapter;
import com.example.morelle.e4fi_android_hoot.DepenseDialog;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;

public class BudgetFragment extends Fragment {

    public interface BudgetFragmentListerner {
        void onBudgetUpdate(Long voyageId);
    }


    private ListView listView ;
    private Budget_Adapter adapter;
    private BudgetFragmentListerner listener;
    private Long voyageId;
    private DataBaseHelper db;
    private EditText budget;
    private TextView depenses;
    private TextView  restants;
    private FloatingActionButton add;

    public BudgetFragment() {
        // Required empty public constructor
    }

    public static BudgetFragment newInstance() {
        return new BudgetFragment();
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Budget de " + db.getCurrentVoyage().getTitre());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_budget, container, false);
        voyageId = db.getCurrentVoyage().getId();
        Voyage v =  db.getVoyage(voyageId);
         depenses = (TextView) root.findViewById(R.id.depenses);
        budget = (EditText) root.findViewById(R.id.budget_editor);
        restants  = (TextView) root.findViewById(R.id.restants);
        budget.setText(Float.toString(v.getBudget()));
        depenses.setText("-"+Float.toString(v.SommeDepense()));
        add= (FloatingActionButton) root.findViewById(R.id.add_depense);
        restants.setText(Float.toString(v.ArgentRestant()));
        listView = (ListView) root.findViewById(R.id.ListView);
        adapter = new Budget_Adapter(getActivity(), R.layout.depense_item, db.getDepenses(voyageId));
        listView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         EditText editText = (EditText) view.findViewById(R.id.budget_editor);
         editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND  ) {
                    setBudget(v.getText().toString());
                    listener.onBudgetUpdate(voyageId);
                     handled =  true;
                }
                return handled;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.DialogFragment dd = new DepenseDialog();
                dd.show(getFragmentManager(), "DepenseDialog");
            }
        });

    }



    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BudgetFragmentListerner) {
            listener = (BudgetFragmentListerner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BudgetFragmentListerner");
        }
    }

    public void setBudget(String s){

        float budget  = Float.parseFloat(s);
      db.getVoyage(voyageId).setBudget(budget);

    }
    public void updateContent(Long voyageId){
        this.voyageId = voyageId;
    }


}

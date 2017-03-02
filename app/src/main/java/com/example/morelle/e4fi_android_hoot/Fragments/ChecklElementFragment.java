package com.example.morelle.e4fi_android_hoot.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.Billet;
import com.example.morelle.e4fi_android_hoot.BDD.CheckElement;
import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.CheckElement_Adapter;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;


public class ChecklElementFragment extends Fragment {

    public interface checkElementListener {

        void onChecklistFragmentInteraction(Long checkListeId);
    }

    public interface deleteCheckListeListener {

        void deleteCheckListe();
    }


    private  EditText add_text;
    private ListView listView ;
    private CheckElement_Adapter adapter;
    private CheckBox check ;
    private Long checkListeID;
    private boolean isEdit ;
    private FloatingActionButton delete;
    private FloatingActionButton edit;
    private DataBaseHelper db;
    private Long voyageId;
    private deleteCheckListeListener deleteListener;



    private checkElementListener mListener;

    public ChecklElementFragment() {
        // Required empty public constructor
    }
    public static ChecklElementFragment newInstance() {

        ChecklElementFragment fragment = new ChecklElementFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        voyageId = db.getCurrentVoyage().getId();
        getActivity().setTitle("Check list de " + db.getVoyage(voyageId).getTitre());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_checkelement, container, false);

        listView = (ListView) root.findViewById(R.id.list_check);
        add_text = (EditText) root.findViewById(R.id.add_text);
        checkListeID = db.getCurrentCheckList().getId();
        delete = (FloatingActionButton) root.findViewById(R.id.delete_checkliste);
        delete.setVisibility(View.INVISIBLE);
        edit = (FloatingActionButton) root.findViewById(R.id.edit_checkliste);
        adapter = new CheckElement_Adapter(getActivity(),R.layout.voyage_checkelement, db.getCheckElements(checkListeID));
        listView.setAdapter(adapter);


        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Add checkelement
        add_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND  ) {
                    CheckElement c = new CheckElement(add_text.getText().toString(), false, db.getCurrentCheckList().getId());
                    CheckElement.save(c);
                    mListener.onChecklistFragmentInteraction(checkListeID);
                    handled =  true;
                }

                return handled;
            }
        });


        if(check!= null) {
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setCheck(isChecked);
                }
            });
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEdit = !isEdit;
                if(isEdit) {
                    edit.setImageResource(R.drawable.ic_done_white_48dp);
                    delete.setVisibility(View.VISIBLE);
                }
                else{
                    delete.setVisibility(View.INVISIBLE);
                }


            }


        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEdit) {
                    deleteListener.deleteCheckListe();
                    isEdit = !isEdit;
                }
            }
        });
    }

    public void updateContent(Long checkListeID){
        this.checkListeID = checkListeID;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof checkElementListener) {
            mListener = (checkElementListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement checkElementListener");
        }
        if (context instanceof deleteCheckListeListener) {
            deleteListener = (deleteCheckListeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement deleteCheckListeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public void setCheck(boolean b){
        CheckElement c = db.getCheckElement(checkListeID);
        c.setDone(b);
        c.save();

    }
}

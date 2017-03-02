package com.example.morelle.e4fi_android_hoot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.morelle.e4fi_android_hoot.BDD.CheckList;
import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;


public class CheckListDialog extends  android.app.DialogFragment {


    public interface AddCheckListeListener {
        void addCheckListe(CheckList checkList);
    }
    private AddCheckListeListener mListener;
    private EditText nom;
    private DialogInterface.OnDismissListener onDismissListener;
    private DataBaseHelper db;

    public CheckListDialog() {

    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View root = getActivity().getLayoutInflater().inflate(R.layout.checklits_dialog, null);
        nom = (EditText) root.findViewById(R.id.checklist_dialog_nom);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Ajouter une check list");
        builder.setView(root);
        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.addCheckListe(new CheckList(nom.getText().toString(),db.getCurrentVoyage().getId()));
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CheckListDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddCheckListeListener) {
            mListener = (AddCheckListeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddCheckListeListener");
        }
    }






}

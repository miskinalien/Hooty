package com.example.morelle.e4fi_android_hoot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.morelle.e4fi_android_hoot.BDD.DataBaseHelper;
import com.example.morelle.e4fi_android_hoot.BDD.Depenses;

/**
 * Created by ESTEL on 16/12/2016.
 */




public class DepenseDialog extends android.app.DialogFragment {
    private DepenseListener listener;
    private TextView titre;
    private TextView prix;
    private DataBaseHelper db;
    interface DepenseListener {
        void onDepenseValidation(Depenses p);
    }

    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
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
        View root = getActivity().getLayoutInflater().inflate(R.layout.depense_dialog, null);

         titre = (TextView) root.findViewById(R.id.title_depense);
         prix = (EditText) root.findViewById(R.id.editAmount);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Ajouter dépenses");
        builder.setView(root);
        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDepenseValidation(new Depenses(titre.getText().toString(),Integer.parseInt(prix.getText().toString()), db.getCurrentVoyage().getId()));
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DepenseDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            listener = (DepenseListener) context;
        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString()
                    + " must implement DepenseListener.");
        }
    }
}





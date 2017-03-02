package com.example.morelle.e4fi_android_hoot.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;


import com.example.morelle.e4fi_android_hoot.BDD.Voyage;
import com.example.morelle.e4fi_android_hoot.DateUtility;
import com.example.morelle.e4fi_android_hoot.ImageUtile;
import com.example.morelle.e4fi_android_hoot.MainActivity;
import com.example.morelle.e4fi_android_hoot.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateVoyageFragment extends Fragment {


    private Button bt;
    private Button add_bt;


    public Context context ;

    public Bitmap toretBitmap;
    public static long date;


    public CreateVoyageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create_voyage, container, false);
        bt = (Button) root.findViewById(R.id.selectimage);
        add_bt = (Button) root.findViewById(R.id.ADD_BT);
        date = System.currentTimeMillis();
        return root;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //button ajouter click
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Select_OnClick();
            }
        });


        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_OnClick();
            }
        });
    }

    private void Select_OnClick()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }
    private void Add_OnClick()
    {
        EditText editName = (EditText) getView().findViewById(R.id.editName) ;

        EditText editLieu = (EditText) getView().findViewById(R.id.editLieu) ;
        EditText editDesc= (EditText) getView().findViewById(R.id.editDesc) ;
        Voyage v;

        if (toretBitmap != null){
           v  = new Voyage( editName.getText().toString(),DateUtility.getDate(date, "EEEE dd MMM yyyy") ,editLieu.getText().toString(),editDesc.getText().toString(), ImageUtile.GetByteFromBitmap(toretBitmap));
        }
        else
        {
            v = new Voyage( editName.getText().toString(), DateUtility.getDate(date, "EEEE dd MMM yyyy")  ,editLieu.getText().toString(),editDesc.getText().toString());

        }

        Voyage.save(v);

        MainActivity parent = (MainActivity)getActivity();

        parent.switchFragment(new VoyageFragment());


    }
public static void setDate(Long _date){
    date = _date;

}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK){
            try {
                InputStream IS = getContext().getContentResolver().openInputStream(data.getData());
                Bitmap b = BitmapFactory.decodeStream(IS);
                toretBitmap = getResizedBitmap(b,350,250);
                ImageView imageView = (ImageView) getView().findViewById(R.id.imageView2);
                imageView.setImageBitmap(toretBitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }


    public void showDatePickerDialog(View v) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}

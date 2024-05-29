package com.example.moneymanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class AddNoteDialog extends DialogFragment {
    TextView categoryName;
    ImageView categoryImage;
    EditText inputPrice;
    Button button;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.add_note);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        categoryName = getDialog().findViewById(R.id.categoryName);
        categoryImage = getDialog().findViewById(R.id.categoryImage);
        inputPrice = getDialog().findViewById(R.id.inputPrice);
        button = getDialog().findViewById(R.id.addNote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!inputPrice.getText().toString().equals("") &&Double.parseDouble(inputPrice.getText().toString())>0){
                    String userId= Integer.toString(Storage.currentUser.getId());
                    Storage.dbManager.insertNoteIntoDB(Double.parseDouble(inputPrice.getText().toString()),userId,Storage.categories.getCategoryName());
                    Storage.notes = Storage.dbManager.getNotes();
                    ManagerActivity managerActivity = (ManagerActivity) getActivity();
                    managerActivity.updateAll();
                }
                dismiss();
            }
        });
        switch (Storage.categories){
            case CAFE:{
                categoryName.setText("Cafe");
                categoryImage.setImageResource(R.drawable.cafe);
                break;
            }
            case GIFTS:{
                categoryName.setText("Gifts");
                categoryImage.setImageResource(R.drawable.podarki);
                break;
            }
            case FAMILY:{
                categoryName.setText("Family");
                categoryImage.setImageResource(R.drawable.semya);
                break;
            }
            case HEALTH:{
                categoryName.setText("Health");
                categoryImage.setImageResource(R.drawable.zdorovie);
                break;
            }
            case LEISURE:{
                categoryName.setText("Leisure");
                categoryImage.setImageResource(R.drawable.dosug);
                break;
            }
            case PRODUCTS:{
               categoryName.setText("Products");
                categoryImage.setImageResource(R.drawable.eda);
                break;
            }
            case PURCHASES:{
                categoryName.setText("Purchases");
                categoryImage.setImageResource(R.drawable.pokupki);
                break;
            }
            case TRANSPORT:{
                categoryName.setText("Transport");
                categoryImage.setImageResource(R.drawable.transport);
                break;
            }

        }
    }
}

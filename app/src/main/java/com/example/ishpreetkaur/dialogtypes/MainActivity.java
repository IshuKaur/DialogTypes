package com.example.ishpreetkaur.dialogtypes;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView heading;
    Button date_picker,alert,custom,d_list,d_choice;
    Context context;
    List<Item> itemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heading=findViewById(R.id.text1);
        date_picker=findViewById(R.id.date_picker);
        alert=findViewById(R.id.alert);
        custom=findViewById(R.id.custom);
        d_list=findViewById(R.id.d_list);
        d_choice=findViewById(R.id.d_choice);

       /* Typeface heading_font=Typeface.createFromAsset(getAssets(),"fonts/Aller_Lt.tff");
        heading.setTypeface(heading_font);*/

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();
            }
        });

        d_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogList();
            }
        });

        d_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

    }

    public void alertDialog()
    {
        new AlertDialog.Builder(this)
                .setTitle("Closing application")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("No", null).show();
    }

    public void customDialog()
    {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("----Here is Custom Dialog----");
        dialog.show();
    }

    public void dialogList()
    {
        String names[] ={"A","B","C","D"};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dialog_with_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("List");
        ListView lv = (ListView) convertView.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);
        alertDialog.show();

    }

    public void show() {
        if (itemList.isEmpty()) {
            itemList.add(new Item("A", R.mipmap.ic_launcher));
            itemList.add(new Item("B", R.mipmap.ic_launcher));
            itemList.add(new Item("C", R.mipmap.ic_launcher));
        }

        final DialogMultipleChoiceAdapter adapter =
                new DialogMultipleChoiceAdapter(context, itemList);

        new AlertDialog.Builder(context).setTitle("Select Image")
                .setAdapter(adapter, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,
                                "getCheckedItem = " + adapter.getCheckedItem().size(),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}

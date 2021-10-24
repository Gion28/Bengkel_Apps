package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MenuUtama extends AppCompatActivity {

    Spinner sp1, sp2;
    String sspin1, sspin2;
    Button btnlihat;

    ArrayList<String> arrayList1;
    ArrayAdapter<String> arrayAdapterParent;

    ArrayList<String> arrayList2, arrayList3;
    ArrayAdapter<String> arrayAdapterChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        sp1 = (Spinner)findViewById(R.id.spinner1);
        sp2 = (Spinner)findViewById(R.id.spinner2);
        btnlihat = (Button)findViewById(R.id.btnlihat);

        arrayList1 = new ArrayList<>();
        arrayList1.add("Roda Dua");
        arrayList1.add("Roda Empat");
        arrayAdapterParent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList1);
        sp1.setAdapter(arrayAdapterParent);

        arrayList2 = new ArrayList<>();
        arrayList2.add("Sport Bike");
        arrayList2.add("Moped");

        arrayList3 = new ArrayList<>();
        arrayList3.add("SUV");
        arrayList3.add("Sedan");

        sp1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    arrayAdapterChild = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList2);
                }
                if(position==1){
                    arrayAdapterChild = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList3);
                }
                sp2.setAdapter(arrayAdapterChild);
            }
        });

        btnlihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sspin1 = sp1.getSelectedItem().toString();
                sspin2 = sp2.getSelectedItem().toString();
                Intent i = new Intent(MenuUtama.this, Proses.class);
                Bundle b = new Bundle();
                b.putString("jenis_kendaraan", sspin1);
                b.putString("model_kendaraan", sspin2);
                i.putExtras(b);
                startActivity(i);
            }
        });

    }
}
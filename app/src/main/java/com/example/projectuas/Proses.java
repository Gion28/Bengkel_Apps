package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Proses extends AppCompatActivity {

    TextView tJenis, tModel, tBagian;
    String showJenis, showModel, showBagian;
    Spinner sp3;
    Button blogout, bexit;

    ArrayList<String> arrayListBagian;
    ArrayAdapter<String> arrayAdapterBagian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses);

        tJenis = (TextView)findViewById(R.id.tampiljenis);
        tModel = (TextView)findViewById(R.id.tampilmodel);
        sp3 = (Spinner)findViewById(R.id.spinner3);
        tBagian = (TextView)findViewById(R.id.tampilbagian);
        blogout = (Button)findViewById(R.id.btnlogout);
        bexit = (Button)findViewById(R.id.btnexit);

        arrayListBagian = new ArrayList<>();
        arrayListBagian.add("Engine");
        arrayListBagian.add("Gear");
        arrayAdapterBagian = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayListBagian);
        sp3.setAdapter(arrayAdapterBagian);

        Bundle b = getIntent().getExtras();
        showJenis = b.getString("jenis_kendaraan");
        showModel = b.getString("model_kendaraan");

        tJenis.setText(showJenis);
        tModel.setText(showModel);

        sp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    if(showModel=="Sport Bike" || showModel=="Moped"){
                        showBagian = "Buka penutup mesin. Cek kondisi mesin. Ganti oli mesin.";
                    }
                    else if(showModel=="SUV" || showModel=="Sedan"){
                        showBagian = "Buka kompartemen mesin. Cek kondisi mesin. Ganti oli mesin. Cek pendingin.";
                    }
                    tBagian.setText(showBagian);
                }
                if(position==1){
                    if(showModel=="Sport Bike"){
                        showBagian = "Cek kabel kopling. Cek mekanisme gear change";
                    }
                    else if(showModel=="Moped"){
                        showBagian = "Cek sistem CVT";
                    }
                    else if(showModel=="SUV"){
                        showBagian = "Cek mekanisme 4WD. Cek kabel kopling. Cek mekanisme gear change.";
                    }
                    else if(showModel=="Sedan"){
                        showBagian = "Cek sistem gear change.";
                    }
                    tBagian.setText(showBagian);
                }
            }
        });

        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Proses.this, MainActivity.class);
                finish();
                startActivity(intent1);
            }
        });

        bexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
            }
        });
    }
}
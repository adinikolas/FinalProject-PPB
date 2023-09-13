package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.R;

public class Dashboard extends AppCompatActivity {

    Button bKeluar;

    CardView cvMaster, cvTransaksi, cvLaporan, cvAboutme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bKeluar = findViewById(R.id.bKeluar);
        cvMaster = findViewById(R.id.cvMaster);
        cvTransaksi = findViewById(R.id.cvTransaksi);
        cvLaporan = findViewById(R.id.cvLaporan);
        cvAboutme = findViewById(R.id.cvAboutme);
        bKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Login.class));
            }
        });

        cvMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Master.class));
            }
        });

        cvTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Transaksi.class));
            }
        });

        cvLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Laporan.class));
            }
        });

        cvAboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AboutMe.class));
            }
        });
    }
}
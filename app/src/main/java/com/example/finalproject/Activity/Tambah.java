package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambah extends AppCompatActivity {

    EditText etKode, etNama, etSatuan, etHarga, etStok;
    String kode, nama, satuan, harga, stok, terjual;
    Button bSimpan;
    DatabaseReference dbr;
    DataModelBarang modelBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etKode = findViewById(R.id.et_kode);
        etNama = findViewById(R.id.et_nama);
        etSatuan = findViewById(R.id.et_satuan);
        etHarga = findViewById(R.id.et_harga);
        etStok = findViewById(R.id.et_stok);
        bSimpan = findViewById(R.id.b_simpan);

        dbr = FirebaseDatabase.getInstance().getReference().child("barang");
        modelBarang = new DataModelBarang();

        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kode = etKode.getText().toString();
                nama = etNama.getText().toString();
                satuan = etSatuan.getText().toString();
                harga = etHarga.getText().toString();
                stok = etStok.getText().toString();
                terjual = "0";

                modelBarang.setKode(kode);
                modelBarang.setNama(nama);
                modelBarang.setSatuan(satuan);
                modelBarang.setHarga(harga);
                modelBarang.setStok(stok);
                modelBarang.setTerjual(terjual);

                if(kode.trim().equals("")){
                    etKode.setError("Kode Harus Diisi");
                }else if(nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");
                }else if(satuan.trim().equals("")){
                    etSatuan.setError("Telepon Harus Diisi");
                }else if(harga.trim().equals("")){
                    etHarga.setError("Harga Harus Diisi");
                }else if(stok.trim().equals("")){
                    etStok.setError("Stok Harus Diisi");
                }else{
                    // simpan data untuk firebase
                    dbr.push().setValue(modelBarang);

                    // simpan data untuk mysql
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBarang> simpanData = ardData.ardCreateData(kode, nama, satuan, harga, stok, terjual);

        simpanData.enqueue(new Callback<ResponseModelBarang>() {
            @Override
            public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(Tambah.this, pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
                Toast.makeText(Tambah.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
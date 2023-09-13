package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.Model.DataModelLaporan;
import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CetakPesanan extends AppCompatActivity {

    private String xKodeBarang, xNamaPelanggan, xAlamatPelanggan, xNamaBarang, xTotalBeli, xHargaAwal, xTotalBayar;
    private TextView tvKodeBarang, tvNamaPelanggan, tvAlamatPelanggan, tvNamaBarang, tvTotalBeli, tvHargaAwal, tvTotalBayar;
    private int xId;
    private DataModelLaporan modelBarang;
    private DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak_pesanan);

        tvKodeBarang = findViewById(R.id.tv_kode_barang);
        tvNamaPelanggan = findViewById(R.id.tv_nama_pelanggan);
        tvAlamatPelanggan = findViewById(R.id.tv_alamat_pelanggan);
        tvNamaBarang = findViewById(R.id.tv_nama_barang);
        tvTotalBeli = findViewById(R.id.tv_total_beli);
        tvHargaAwal = findViewById(R.id.tv_harga_awal);
        tvTotalBayar = findViewById(R.id.tv_total_bayar);
        dbr = FirebaseDatabase.getInstance().getReference().child("penjualan");
        modelBarang = new DataModelLaporan();

        Intent terima = getIntent();
        xId = terima.getIntExtra("id", -1);
        xKodeBarang = terima.getStringExtra("kode_barang");
        xNamaPelanggan = terima.getStringExtra("nama_pelanggan");
        xAlamatPelanggan = terima.getStringExtra("alamat_pelanggan");
        xNamaBarang = terima.getStringExtra("nama_barang");
        xTotalBeli = terima.getStringExtra("total_beli");
        xHargaAwal = terima.getStringExtra("harga_awal");
        xTotalBayar = terima.getStringExtra("total_bayar");

        tvKodeBarang.setText(xKodeBarang);
        tvNamaPelanggan.setText(xNamaPelanggan);
        tvAlamatPelanggan.setText(xAlamatPelanggan);
        tvNamaBarang.setText(xNamaBarang);
        tvTotalBeli.setText(xTotalBeli);
        tvHargaAwal.setText(xHargaAwal);
        tvTotalBayar.setText(xTotalBayar);

        // simpan data ke firebase
        modelBarang.setKode_barang(xKodeBarang);
        modelBarang.setNama_pelanggan(xNamaPelanggan);
        modelBarang.setAlamat_pelanggan(xAlamatPelanggan);
        modelBarang.setNama_barang(xNamaBarang);
        modelBarang.setTotal_beli(xTotalBeli);
        modelBarang.setHarga_awal(xHargaAwal);
        modelBarang.setTotal_bayar(xTotalBayar);

        dbr.push().setValue(modelBarang);

        // simpan data ke mysql
        createPenjualanData();
    }
    private void createPenjualanData(){
        APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBarang> simpanData = ardData.ardCreatePenjualanData(xId, xKodeBarang, xNamaPelanggan, xAlamatPelanggan, xNamaBarang, xTotalBeli, xHargaAwal, xTotalBayar);

        simpanData.enqueue(new Callback<ResponseModelBarang>() {
            @Override
            public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
//                String kode = response.body().getKode();
//                String pesan = response.body().getPesan();

//                Toast.makeText(CetakPesanan.this, pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
//                Toast.makeText(CetakPesanan.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
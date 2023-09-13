package com.example.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Activity.Detail;
import com.example.finalproject.Activity.Master;
import com.example.finalproject.Activity.Transaksi;
import com.example.finalproject.Activity.Update;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMaster extends RecyclerView.Adapter<AdapterMaster.HolderData> {
    private Context context;
    private List<DataModelBarang> listModel;
    private List<DataModelBarang> listBarang;
    private String idBarang, keyBarang;
    private DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();

    public AdapterMaster(Context context, List<DataModelBarang> listModel) {
        this.context = context;
        this.listModel = listModel;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, @SuppressLint("RecyclerView") int position) {
        final DataModelBarang dm = listModel.get(position);

        holder.tvKey.setText(dm.getKey());
        holder.tvKode.setText(dm.getKode());
        holder.tvNama.setText(dm.getNama());
        holder.tvSatuan.setText(dm.getSatuan());
        holder.tvHarga.setText(dm.getHarga());
        holder.tvStok.setText(dm.getStok());
        holder.tvTerjual.setText(dm.getTerjual());
        holder.listLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(context);
                dialogPesan.setMessage("Pilih Operasi yang akan dilakukan");
                dialogPesan.setCancelable(true);

                idBarang = holder.tvKode.getText().toString();
                keyBarang = holder.tvKey.getText().toString();

                dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder konfirmasi = new AlertDialog.Builder(context);
                        konfirmasi.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData();
                                dialog.dismiss();
                                ((Master) context).tampilData();
                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setMessage("Apakah yakin ingin menghapus data?");
                        konfirmasi.show();
                    }
                });

                dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                    }
                });

                dialogPesan.show();

                return false;
            }

            private void deleteData(){
                // delete for firebase
                dbr.child("barang")
                        .child(keyBarang)
                        .removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Hapus Berhasil", Toast.LENGTH_SHORT).show();
                            }
                        });

                // delete for mysql
                APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModelBarang> hapusData = ardData.ardHapusData(idBarang);

                hapusData.enqueue(new Callback<ResponseModelBarang>() {
                    @Override
                    public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
//                        String kode = response.body().getKode();
//                        String pesan = response.body().getPesan();

//                        Toast.makeText(context, "Kode: "+kode+" | Pesan: "+pesan, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
                        Toast.makeText(context, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void getData(){
                APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModelBarang> ambilData = ardData.ardGetData(idBarang);

                ambilData.enqueue(new Callback<ResponseModelBarang>() {
                    @Override
                    public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
                        String kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        listBarang = response.body().getData();

                        String varKey = listBarang.get(0).getKey();
                        String varKodeBarang = listBarang.get(0).getKode();
                        String varNamaBarang = listBarang.get(0).getNama();
                        String varSatuanBarang = listBarang.get(0).getSatuan();
                        String varHargaBarang = listBarang.get(0).getHarga();
                        String varStokBarang = listBarang.get(0).getStok();
                        String varTerjual = listBarang.get(0).getTerjual();

                        Intent kirim = new Intent(context, Update.class);
                        kirim.putExtra("xKey", varKey);
                        kirim.putExtra("xKode", varKodeBarang);
                        kirim.putExtra("xNama", varNamaBarang);
                        kirim.putExtra("xSatuan", varSatuanBarang);
                        kirim.putExtra("xHarga", varHargaBarang);
                        kirim.putExtra("xStok", varStokBarang);
                        kirim.putExtra("xTerjual", varTerjual);
                        context.startActivity(kirim);

                        //Toast.makeText(context, "Kode: "+kode+" | Pesan: "+pesan+, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
                        Toast.makeText(context, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvKode, tvNama, tvSatuan, tvHarga, tvStok, tvTerjual, tvKey;
        ImageView ivGambar;
        LinearLayout listLayout;

        public HolderData(@NonNull View v) {
            super(v);

            tvKey = v.findViewById(R.id.tv_key);
            ivGambar = v.findViewById(R.id.iv_barang);
            listLayout = v.findViewById(R.id.list_item);
            tvKode = v.findViewById(R.id.tv_kode);
            tvNama = v.findViewById(R.id.tv_nama);
            tvSatuan = v.findViewById(R.id.tv_satuan);
            tvHarga = v.findViewById(R.id.tv_harga);
            tvStok = v.findViewById(R.id.tv_stok);
            tvTerjual = v.findViewById(R.id.tv_terjual);
        }
    }
}


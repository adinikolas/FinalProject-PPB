package com.example.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.R;

import java.util.List;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.HolderData> {
    private Context context;
    private List<DataModelBarang> listModel;

    public AdapterLaporan(Context context, List<DataModelBarang> listModel) {
        this.context = context;
        this.listModel = listModel;
    }

    @NonNull
    @Override
    public AdapterLaporan.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.laporan_item, parent, false);
        AdapterLaporan.HolderData holder = new AdapterLaporan.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLaporan.HolderData holder, @SuppressLint("RecyclerView") int position) {
        DataModelBarang dm = listModel.get(position);

        holder.tvKode.setText(dm.getKode());
        holder.tvNama.setText(dm.getNama());
        holder.tvSatuan.setText(dm.getSatuan());
        holder.tvHarga.setText(dm.getHarga());
        holder.tvStok.setText(dm.getStok());
        holder.tvTerjual.setText(dm.getTerjual());
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvKode, tvNama, tvSatuan, tvHarga, tvStok, tvTerjual;

        public HolderData(@NonNull View v) {
            super(v);

            tvKode = v.findViewById(R.id.tv_kode);
            tvNama = v.findViewById(R.id.tv_nama);
            tvSatuan = v.findViewById(R.id.tv_satuan);
            tvHarga = v.findViewById(R.id.tv_harga);
            tvStok = v.findViewById(R.id.tv_sisa_stok);
            tvTerjual = v.findViewById(R.id.tv_terjual);
        }
    }
}


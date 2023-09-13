package com.example.finalproject.Model;

import java.util.List;

public class ResponseModelBarang {
    private String kode, pesan;
    private List<DataModelBarang> data;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModelBarang> getData() {
        return data;
    }

    public void setData(List<DataModelBarang> data) {
        this.data = data;
    }
}

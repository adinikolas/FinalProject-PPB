package com.example.finalproject.Model;

import java.util.List;

public class ResponseModelLogin {
    private int kode;
    private String pesan;
    private List<DataModelLogin> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModelLogin> getData() {
        return data;
    }

    public void setData(List<DataModelLogin> data) {
        this.data = data;
    }
}

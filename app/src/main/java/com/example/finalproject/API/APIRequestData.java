package com.example.finalproject.API;

import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.Model.ResponseModelLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("tampilBarang.php")
    Call<ResponseModelBarang> ardTampilData();

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseModelLogin> ardLoginData(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("tambahBarang.php")
    Call<ResponseModelBarang> ardCreateData(
            @Field("kode") String kode,
            @Field("nama") String nama,
            @Field("satuan") String satuan,
            @Field("harga") String harga,
            @Field("stok") String stok,
            @Field("terjual") String terjual
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModelBarang> ardHapusData(
            @Field("kode") String kode
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModelBarang> ardGetData(
            @Field("kode") String kode
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModelBarang> ardUpdateData(
            @Field("kode") String kode,
            @Field("nama") String nama,
            @Field("satuan") String satuan,
            @Field("harga") String harga,
            @Field("stok") String stok,
            @Field("terjual") String terjual
    );

    @FormUrlEncoded
    @POST("tambahPenjualan.php")
    Call<ResponseModelBarang> ardCreatePenjualanData(
            @Field("id") int id,
            @Field("kode_barang") String kode_barang,
            @Field("nama_pelanggan") String nama_pelanggan,
            @Field("alamat_pelanggan") String alamat_pelanggan,
            @Field("nama_barang") String nama_barang,
            @Field("total_beli") String total_beli,
            @Field("harga_awal") String harga_awal,
            @Field("total_bayar") String total_bayar
    );
}

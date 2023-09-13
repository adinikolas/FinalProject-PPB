package com.example.finalproject.API;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {
    private static final String baseURL = "http://192.168.1.9/penjualan/";
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if(retro==null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}

package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Model.DataModelLogin;
import com.example.finalproject.Model.ResponseModelLogin;
import com.example.finalproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText etUsername, etPassword;
    String username, password;
    Button bLogin;
    List<DataModelLogin> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                autentikasiLogin();
            }
        });
    }
    public void autentikasiLogin(){
        APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelLogin> loginData = ardData.ardLoginData(username, password);

        loginData.enqueue(new Callback<ResponseModelLogin>() {
            @Override
            public void onResponse(Call<ResponseModelLogin> call, Response<ResponseModelLogin> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(Login.this, pesan, Toast.LENGTH_SHORT).show();
                listData = response.body().getData();
                if(kode==1){
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "Login Gagal!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelLogin> call, Throwable t) {
                Toast.makeText(Login.this, "Gagal Menghubungi Server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.hospitalproyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hospitalproyectointegrador.Interfaces.ApiAdapter;
import com.example.hospitalproyectointegrador.models.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.hospitalproyectointegrador.Login.class));
                finish();
            }
        });

        /*Call<ArrayList<Usuario>> callUser = ApiAdapter.getApiService().getUserResponse();
        callUser.enqueue(this);*/

        /*ArrayList<Usuario> diseases = response.body();
        Log.d("onResponse diseases","Size of diseases => "+ diseases.size());*/

    }
}

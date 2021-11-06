package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospitalproyectointegrador.Interfaces.ApiAdapter;
import com.example.hospitalproyectointegrador.models.LoginRequest;
import com.example.hospitalproyectointegrador.models.LoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button btnIniciarSesionLogin, btnCrearCuenta;
    EditText username, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.edtUsuarioI);
        contraseña = (EditText) findViewById(R.id.edtNombreNU);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, com.example.hospitalproyectointegrador.NuevoUsuario.class));
                finish();
            }
        });
        btnIniciarSesionLogin = findViewById(R.id.btniniciarSesionLogin);
        btnIniciarSesionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(contraseña.getText().toString())) {
                    Toast.makeText(Login.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    //procced to login
                    login();

                    /*LoginResponse loginResponse = new LoginResponse();
                    Toast.makeText(Login.this,""+loginResponse.getNombre_rol(),Toast.LENGTH_LONG).show();
                    if(loginResponse.getNombre_rol().equalsIgnoreCase("ROLE_PACIENTE")){
                        startActivity(new Intent(Login.this, com.example.hospitalproyectointegrador.PacienteIngreso.class));
                        finish();
                    }
                    if(loginResponse.getNombre_rol().equalsIgnoreCase("ROLE_ADMINISTRADOR")){
                        startActivity(new Intent(Login.this, com.example.hospitalproyectointegrador.DoctorLogin.class));
                        finish();
                    }*/
                }

            }
        });

    }
    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setDni(username.getText().toString());
        loginRequest.setContraseña(contraseña.getText().toString());


        Call<ArrayList<LoginResponse>> loginResponseCall = ApiAdapter.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<ArrayList<LoginResponse>>(){
            @Override
            public void onResponse(Call<ArrayList<LoginResponse>> call, Response<ArrayList<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Login.this, "Login succesful", Toast.LENGTH_LONG).show();
                    ArrayList<LoginResponse> loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(loginResponse.get(0).getNombre_rol().equalsIgnoreCase("ROLE_PACIENTE")){
                                Bundle bun=new Bundle();
                                bun.putString("dni",loginResponse.get(0).getDni());
                                bun.putString("nombre",loginResponse.get(0).getNombre());
                                Intent i = new Intent(Login.this,PacienteIngreso.class);
                                i.putExtras(bun);
                                startActivity(i);
                            }
                            else if(loginResponse.get(0).getNombre_rol().equalsIgnoreCase("ROLE_ADMINISTRADOR")){
                                Bundle bun=new Bundle();
                                bun.putString("dnidoc",loginResponse.get(0).getDni());
                                bun.putString("nombredoc",loginResponse.get(0).getNombre());
                                bun.putString("cargodoc",loginResponse.get(0).getNombre_rol());
                                Intent i = new Intent(Login.this,DoctorLogin.class);
                                i.putExtras(bun);
                                startActivity(i);
                            }
                            else{
                                startActivity(new Intent(Login.this, Login.class));
                                Toast.makeText(Login.this, "Rol inaccesible en la base de datos", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    },700);
                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<LoginResponse>> call, Throwable t) {
                Toast.makeText(Login.this,"Trowable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
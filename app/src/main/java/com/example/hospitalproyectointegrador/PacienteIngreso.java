package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospitalproyectointegrador.Interfaces.ApiAdapter;
import com.example.hospitalproyectointegrador.models.LoginResponse;
import com.example.hospitalproyectointegrador.models.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PacienteIngreso extends AppCompatActivity {
    TextView txtNombre,txtDni;
    Button btnPerfilPaciente,btnCrearCitaPaciente,btnHistorialCitasxFechaPaciente,btnCerrarSesionPaciente;

    Usuario objUsuario = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paciente_ingreso);
        txtNombre = (TextView)  findViewById(R.id.txtNombrePac);
        txtDni = (TextView) findViewById(R.id.txtDniPac);

        /*ArrayList<LoginResponse> loginResponse = (ArrayList<LoginResponse>) getIntent().getSerializableExtra("docente");*/
        Intent intent = getIntent();

            String passedName = intent.getStringExtra("nombre");
            String passedDni = intent.getStringExtra("dni");
            txtNombre.setText("NOMBRE: " + passedName);
            txtDni.setText("DNI: "+ passedDni);


        btnPerfilPaciente= findViewById(R.id.btnPerfilPaciente);
        btnPerfilPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarUsuario(passedDni);
                //crear objeto de la clase Intent
                Intent intent=new Intent(PacienteIngreso.this, com.example.hospitalproyectointegrador.PerfilUsuario.class);
                intent.putExtra("dniPerf", (Serializable) objUsuario.getDni());
                //direccionar
                startActivity(intent);
            }
        });

        btnCrearCitaPaciente= findViewById(R.id.btnCrearCitaPaciente);
        btnCrearCitaPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PacienteIngreso.this, com.example.hospitalproyectointegrador.Paciente_CrearCita.class));
                finish();
            }
        });

        btnHistorialCitasxFechaPaciente= findViewById(R.id.btnHistorialCitasxFechaPaciente);
        btnHistorialCitasxFechaPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PacienteIngreso.this, com.example.hospitalproyectointegrador.Paciente_HistorialCitasxFechas.class));
                finish();
            }
        });

        btnCerrarSesionPaciente= findViewById(R.id.btnCerrarSesionPaciente);
        btnCerrarSesionPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PacienteIngreso.this, com.example.hospitalproyectointegrador.Login.class));
                finish();
            }
        });
    }
    public void listarUsuario(String username){
        Call<Usuario> call =  ApiAdapter.getUserService().UsuarioxUsername(username);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    objUsuario =   response.body();

                }else{
                    mensaje("LISTANDO USUARIO....", "ERROR -> Error en else");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mensajeError("ERROR -> Error onFailure 2",t);
            }
        });
    }
    void mensaje(String s, String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
    void mensajeError(String s, Throwable msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(), (CharSequence) msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}

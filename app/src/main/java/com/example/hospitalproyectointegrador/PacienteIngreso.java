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

    List<Usuario> objUsuario = new ArrayList<Usuario>();
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
                List<Usuario> objUsuario = new ArrayList<>();
                //crear objeto de la clase Intent
                Intent intent=new Intent(PacienteIngreso.this, com.example.hospitalproyectointegrador.PerfilUsuario.class);
                //crear una clave dentro del objeto "intent"
                intent.putExtra("usuario", (Serializable) objUsuario);
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
        mensaje("LISTANDO USUARIO....");
        Call<List<Usuario>> call =  ApiAdapter.getUserService().UsuarioxUsername(username);
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    List<Usuario> listausuario =   response.body();
                    objUsuario.addAll(listausuario);
                }else{
                    mensaje("ERROR -> Error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                mensaje("ERROR -> Error en la respuesta");
            }
        });
    }
    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}

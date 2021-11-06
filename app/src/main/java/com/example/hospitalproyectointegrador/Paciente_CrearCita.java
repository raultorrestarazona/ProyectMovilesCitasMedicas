package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Paciente_CrearCita extends AppCompatActivity {

    Button btnPacienteCrearCita_Regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paciente_crear_cita);

        btnPacienteCrearCita_Regresar= findViewById(R.id.btnPacienteCrearCita_Regresar);
        btnPacienteCrearCita_Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Paciente_CrearCita.this, com.example.hospitalproyectointegrador.PacienteIngreso.class));
                finish();
            }
        });
    }
}

package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Paciente_HistorialCitasxFechas extends AppCompatActivity {

    Button btnPacienteRegresar_BusqeudaCitaxFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paciente_historial_citas_fechas);

        btnPacienteRegresar_BusqeudaCitaxFecha= findViewById(R.id.btnPacienteRegresar_BusqeudaCitaxFecha);
        btnPacienteRegresar_BusqeudaCitaxFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Paciente_HistorialCitasxFechas.this, com.example.hospitalproyectointegrador.PacienteIngreso.class));
                finish();
            }
        });
    }

}

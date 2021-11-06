package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilUsuario extends AppCompatActivity {
    Button btnPerfilUsuario_Regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuario);

        btnPerfilUsuario_Regresar= findViewById(R.id.btnPerfilUsuario_Regresar);
        btnPerfilUsuario_Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerfilUsuario.this, com.example.hospitalproyectointegrador.PacienteIngreso.class));
                finish();
            }
        });
    }
}

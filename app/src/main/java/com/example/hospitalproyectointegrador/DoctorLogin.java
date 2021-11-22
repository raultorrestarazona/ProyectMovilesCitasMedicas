package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorLogin extends AppCompatActivity {
    Button CerrarSesionDoc;
    TextView txtNombreDoc,txtDniDoc,txtCargoDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_ingreso);
        txtCargoDoc = (TextView) findViewById(R.id.txtDniPac);
        txtDniDoc = (TextView) findViewById(R.id.txtDniDoc);
        txtNombreDoc = (TextView) findViewById(R.id.txtNombrePac);
        CerrarSesionDoc = (Button) findViewById(R.id.btnCerrarSesionDoctor);

        Intent intent = getIntent();

        if(intent.getExtras() !=null){
            String passedNameDoc = intent.getStringExtra("nombredoc");
            String passedDniDoc = intent.getStringExtra("dnidoc");
            String passedCargoDoc = intent.getStringExtra("cargodoc");
            txtNombreDoc.setText("NOMBRE: " + passedNameDoc);
            txtDniDoc.setText("DNI:" + passedDniDoc);
            txtCargoDoc.setText("CARGO: " + passedCargoDoc);
        }

        CerrarSesionDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorLogin.this, Log.class));
                finish();
            }
        });
    }
}

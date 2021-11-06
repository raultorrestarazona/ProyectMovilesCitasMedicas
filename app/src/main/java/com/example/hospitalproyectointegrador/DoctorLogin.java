package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DoctorLogin extends AppCompatActivity {
    Button CerrarSesionDoc;
    TextView txtNombreDoc,txtDniDoc,txtCargoDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_ingreso);
        txtCargoDoc = (TextView) findViewById(R.id.txtCargoDoc);
        txtDniDoc = (TextView) findViewById(R.id.txtDniDoc);
        txtNombreDoc = (TextView) findViewById(R.id.txtNombreDoc);
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

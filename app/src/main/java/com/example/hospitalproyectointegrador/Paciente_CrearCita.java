package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospitalproyectointegrador.Interfaces.ApiAdapter;
import com.example.hospitalproyectointegrador.models.Area;
import com.example.hospitalproyectointegrador.models.Cita;
import com.example.hospitalproyectointegrador.models.Hora;
import com.example.hospitalproyectointegrador.models.Sede;
import com.example.hospitalproyectointegrador.models.Usuario;
import com.example.hospitalproyectointegrador.utils.GlobalClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Paciente_CrearCita extends AppCompatActivity {
    EditText edtComentarioCitaUC,edtFechaCitaUC;
    Spinner spnSedeCitaPA,spnAreaCitaPA,spnHorarioCitaPA;
    Button btnPacienteCrearCita_Regresar,btnPacienteCrearCita_Registrar;
    List<Sede> listaSede;
    List<Area> listaArea;
    List<Hora> listaHora;

    String reg_texto="[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]{2,25}";
    String reg_contraseña = "[0-9a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]{2,40}";
    String reg_fechaNacimiento = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Usando Variables Globales
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paciente_crear_cita);
        edtComentarioCitaUC=(EditText) findViewById(R.id.edtComentarioCitaUC);
        edtFechaCitaUC=(EditText) findViewById(R.id.edtFechaCitaUC);
        spnSedeCitaPA = (Spinner) findViewById(R.id.spnSedeCitaPA);
        spnAreaCitaPA = (Spinner) findViewById(R.id.spnAreaCitaPA);
        spnHorarioCitaPA = (Spinner) findViewById(R.id.spnHorarioCitaPA);
        btnPacienteCrearCita_Regresar= findViewById(R.id.btnPacienteCrearCita_Regresar);
        btnPacienteCrearCita_Registrar=findViewById(R.id.btnPacienteCrearCita_Registrar);
        spnSedeCitaPA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id)
            {
                Integer cod=listaSede.get(posicion).getId_sede();
            }public void onNothingSelected(AdapterView<?> spn) {
            }
        });
        spnAreaCitaPA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id)
            {
                Integer cod=listaArea.get(posicion).getId_area();
            }public void onNothingSelected(AdapterView<?> spn) {
            }
        });
        spnHorarioCitaPA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id)
            {
                Integer cod=listaHora.get(posicion).getId_hora();
            }public void onNothingSelected(AdapterView<?> spn) {
            }
        });
        btnPacienteCrearCita_Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Paciente_CrearCita.this, com.example.hospitalproyectointegrador.PacienteIngreso.class));
                finish();
            }
        });
        btnPacienteCrearCita_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario = edtComentarioCitaUC.getText().toString();
                String fechaCita = edtFechaCitaUC.getText().toString();
                int idSede=spnSedeCitaPA.getSelectedItemPosition();
                int idArea=spnAreaCitaPA.getSelectedItemPosition();
                int idHora=spnHorarioCitaPA.getSelectedItemPosition();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaComoCadena = sdf.format(new Date());
                System.out.println(fechaComoCadena);

                if(!fechaCita.matches(reg_fechaNacimiento)){
                    edtFechaCitaUC.setError("La fecha de cita es yyyy-mm-dd");
                }else {
                    Cita obj = new Cita();
                    obj.setFecha_cita(fechaCita);
                    obj.setComentario(comentario);
                    obj.setId_sede(listaSede.get(idSede).getId_sede());
                    obj.setId_area(listaArea.get(idArea).getId_area());
                    obj.setId_hora(listaHora.get(idHora).getId_hora());
                    obj.setFecha_registro(fechaComoCadena);
                    obj.setId_paciente(globalVariable.getId_Paciente());
                    crearCita(obj);
                    startActivity(new Intent(Paciente_CrearCita.this, com.example.hospitalproyectointegrador.PacienteIngreso.class));
                    finish();
                }
            }
        });
        getSpnSedeApi();
        getSpnAreaApi();
        getSpnHorarioApi();
    }
    void getSpnSedeApi(){
        Call<List<Sede>> data= ApiAdapter.getUserService().getSedes();
        data.enqueue(new Callback<List<Sede>>() {
            @Override
            public void onResponse(Call<List<Sede>> call, Response<List<Sede>> response) {
                listaSede=response.body();
                llenarSpnSede();
            }

            @Override
            public void onFailure(Call<List<Sede>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }
    void getSpnAreaApi(){
        Call<List<Area>> data= ApiAdapter.getUserService().getAreas();
        data.enqueue(new Callback<List<Area>>() {
            @Override
            public void onResponse(Call<List<Area>> call, Response<List<Area>> response) {
                listaArea=response.body();
                llenarSpnArea();
            }

            @Override
            public void onFailure(Call<List<Area>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }
    void getSpnHorarioApi(){
        Call<List<Hora>> data= ApiAdapter.getUserService().getHoras();
        data.enqueue(new Callback<List<Hora>>() {
            @Override
            public void onResponse(Call<List<Hora>> call, Response<List<Hora>> response) {
                listaHora=response.body();
                llenarSpnHora();
            }

            @Override
            public void onFailure(Call<List<Hora>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }
    void llenarSpnSede(){
        ArrayList<String> listaSedes=new ArrayList<String>();
        for(Sede bean:listaSede)
            listaSedes.add(bean.getNombre_sede());

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaSedes);
        spnSedeCitaPA.setAdapter(adapter);
    }
    void llenarSpnArea(){
        ArrayList<String> listaAreas=new ArrayList<String>();
        for(Area bean:listaArea)
            listaAreas.add(bean.getNombre_area());

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaAreas);
        spnAreaCitaPA.setAdapter(adapter);
    }
    void llenarSpnHora(){
        ArrayList<String> listaHoras=new ArrayList<String>();
        for(Hora bean:listaHora)
            listaHoras.add(bean.getRango_horas());

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaHoras);
        spnHorarioCitaPA.setAdapter(adapter);
    }
    void crearCita(Cita obj){
        Call<Void> call = ApiAdapter.getUserService().saveCita(obj);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    mensaje("","SE REGISTRO EXITOSAMENTE");
                }
                else{
                    mensaje("","ERROR -> Error en la respuesta");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mensaje("","ERROR -> " +   t.getMessage());
            }
        });
    }
    void mensaje(String s, String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}

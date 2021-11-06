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
import com.example.hospitalproyectointegrador.models.Departamento;
import com.example.hospitalproyectointegrador.models.Distrito;
import com.example.hospitalproyectointegrador.models.Provincia;
import com.example.hospitalproyectointegrador.models.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoUsuario extends AppCompatActivity{

    Button btnNuevoUsuario_Regresar,btnNuevoUsuario_Registrar;
    EditText edtNombreNU,edtApellidosNU,edtFechaNacimientoNU,edtCelularNU,edtDNINU,edtContraseñaNU,edtRptContraseñaNU;
    Spinner spnDepartamentosNU,spnProvinciaNU,spnDistritoNU;

    List<Departamento> lista;
    List<Provincia> listaProvincias;
    List<Distrito> listaDistritos;

    String reg_texto="[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]{2,25}";
    String reg_contraseña = "[0-9a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]{2,40}";
    String reg_fechaNacimiento = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);
        edtNombreNU = (EditText) findViewById(R.id.edtNombreNU);
        edtApellidosNU = (EditText) findViewById(R.id.edtApellidosNU);
        edtFechaNacimientoNU = (EditText) findViewById(R.id.edtFechaNacimientoNU);
        edtCelularNU = (EditText) findViewById(R.id.edtCelularNU);
        edtDNINU = (EditText) findViewById(R.id.edtDNINU);
        edtContraseñaNU = (EditText) findViewById(R.id.edtContraseñaNU);
        edtRptContraseñaNU = (EditText) findViewById(R.id.edtRptContraseñaNU);
        spnDepartamentosNU = (Spinner) findViewById(R.id.spnDepartamentosList);
        spnProvinciaNU = (Spinner) findViewById(R.id.spnProvinciaNU);
        spnDistritoNU = (Spinner) findViewById(R.id.spnDistritoListNU);
        btnNuevoUsuario_Regresar= (Button) findViewById(R.id.btnNuevoUsuario_Regresar);
        btnNuevoUsuario_Registrar = (Button) findViewById(R.id.btnNuevoUsuario_Registrar);
        llenarDepartamentos();
        llenarProvincia("01");
        llenarDistrito(101);

        btnNuevoUsuario_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomb = edtNombreNU.getText().toString();
                String ape = edtApellidosNU.getText().toString();
                String fechaNaci = edtFechaNacimientoNU.getText().toString();
                String cel = edtCelularNU.getText().toString();
                String dni = edtDNINU.getText().toString();
                String contra = edtContraseñaNU.getText().toString();
                String rptcontra = edtRptContraseñaNU.getText().toString();
                int idDist=spnDistritoNU.getSelectedItemPosition();
                /*String distrito = spnDistritoNU.getSelectedItem().toString();*/


                if (!nomb.matches(reg_texto)){
                    edtNombreNU.setError("El nombre contiene de 2 a 25 letras");
                }else if (!ape.matches(reg_texto)){
                    edtApellidosNU.setError("El apellido contiene de 2 a 25 letras");
                }else if(!fechaNaci.matches(reg_fechaNacimiento)){
                    edtFechaNacimientoNU.setError("La fecha de nacimiento es yyyy-mm-dd");

                }else if (!cel.matches("\\d{9}")){
                    edtCelularNU.setError("El celular debe de ser 9 numeros");
                }else if (!dni.matches("\\d{8}")){
                    edtDNINU.setError("El Dni tiene 8 digitos");

                }else if (!contra.matches(reg_contraseña)){
                    edtContraseñaNU.setError("Ingrese una contraseña valida");
                }else if (!rptcontra.matches(reg_contraseña)){
                    edtRptContraseñaNU.setError("Repita su contraseña");
                }
                else if (idDist == 0){
                    mensaje("AVISO => "+"Seleccione un Distrito");
                    spnDistritoNU.requestFocus();
                } else {
                    Usuario obj = new Usuario();
                    obj.setNombre(nomb);
                    obj.setApellidos(ape);
                    obj.setFechanacimiento(fechaNaci);
                    obj.setCelular(cel);
                    obj.setDni(dni);
                    obj.setContraseña(contra);
                    obj.setId_distrito(idDist + 10100);
                    registrar(obj);

                    startActivity(new Intent(NuevoUsuario.this, com.example.hospitalproyectointegrador.Login.class));
                    finish();
                }

            }
        });

        btnNuevoUsuario_Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NuevoUsuario.this, com.example.hospitalproyectointegrador.Login.class));
                finish();
            }
        });
        spnDepartamentosNU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id)
            {
                String pos="";
                if(spn.getItemIdAtPosition(posicion)<10){
                    pos="0"+(spn.getItemIdAtPosition(posicion) + 1);
                }else{
                    pos=(spn.getItemIdAtPosition(posicion)+1)+"";
                }
                llenarProvincia(pos);
            }public void onNothingSelected(AdapterView<?> spn) {
            }
        });
        spnProvinciaNU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> spn, View view, int posicion, long id) {
                id=(spn.getItemIdAtPosition(posicion) + 801);
                int poss = (int) id;
                llenarDistrito(poss);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void registrar(Usuario obj){
        Call<Void> call = ApiAdapter.getUserService().regisUsuario(obj);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                        mensaje("SE REGISTRO EXITOSAMENTE");
                }
                else{
                    mensaje("ERROR -> Error en la respuesta");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mensaje("ERROR -> " +   t.getMessage());
            }
        });
    }
    void llenarDistrito(Integer idProvincia){
        Call<List<Distrito>> dataDistrito= ApiAdapter.getUserService().buscaDistrito(idProvincia);
        dataDistrito.enqueue(new Callback<List<Distrito>>() {
            @Override
            public void onResponse(Call<List<Distrito>> call, Response<List<Distrito>> response) {
                listaDistritos=response.body();
                mostrarNombreDistrito();
            }
            @Override
            public void onFailure(Call<List<Distrito>> call, Throwable t) {

            }
        });
    }
    void mostrarNombreDistrito(){
        ArrayList<String> listaDist=new ArrayList<String>();
        for(Distrito bean:listaDistritos){
            listaDist.add(bean.getNombre_distrito());
        }

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaDist);
        spnDistritoNU.setAdapter(adapter);
    }
    void llenarProvincia(String idDepartamento){
        Call<List<Provincia>> dataProvincia= ApiAdapter.getUserService().buscaProvincia(idDepartamento);
        dataProvincia.enqueue(new Callback<List<Provincia>>() {
            @Override
            public void onResponse(Call<List<Provincia>> call, Response<List<Provincia>> response) {
                listaProvincias=response.body();
                mostrarNombreProvicia();
            }

            @Override
            public void onFailure(Call<List<Provincia>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    void mostrarNombreProvicia(){
        ArrayList<String> listaProv=new ArrayList<String>();
        for(Provincia bean:listaProvincias)
            listaProv.add(bean.getNombre_provincia());

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaProv);
        spnProvinciaNU.setAdapter(adapter);
    }

    void llenarDepartamentos(){
        Call<List<Departamento>> data= ApiAdapter.getUserService().getDepartamentos();
        data.enqueue(new Callback<List<Departamento>>() {
            @Override
            public void onResponse(Call<List<Departamento>> call, Response<List<Departamento>> response) {
                lista=response.body();
                mostrarNombreDepartamento();
            }

            @Override
            public void onFailure(Call<List<Departamento>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }
    void mostrarNombreDepartamento(){
        ArrayList<String> listaDepartamentos=new ArrayList<String>();
        for(Departamento bean:lista)
            listaDepartamentos.add(bean.getNombre_departamento());

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaDepartamentos);
        spnDepartamentosNU.setAdapter(adapter);
    }
    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }

}

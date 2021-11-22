package com.example.hospitalproyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class PerfilUsuario extends AppCompatActivity {
    Button btnPerfilUsuario_Regresar,btnActualizarUsuarioPU;
    EditText edtDniPU,edtNombrePU,edtApellidoPU,edtFechaNacimientoPU,edtCelularPU,edtContraseñaPU,edtRepitaContraseñaPU;
    Spinner spnDepartamentoPU,spnProvinciaPU,spnDistritoPU;

    Usuario objUsuario = new Usuario();
    List<Departamento> lista;
    List<Provincia> listaProvincias;
    List<Distrito> listaDistritos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuario);

        btnPerfilUsuario_Regresar=(Button) findViewById(R.id.btnPerfilUsuario_Regresar);
        btnActualizarUsuarioPU=(Button) findViewById(R.id.btnPerfilUsuario_Actualizar);
        edtDniPU=(EditText) findViewById(R.id.edtDniPU);
        edtNombrePU=(EditText) findViewById(R.id.edtNombrePU);
        edtApellidoPU=(EditText) findViewById(R.id.edtApellidosPU);
        edtFechaNacimientoPU=(EditText) findViewById(R.id.edtFechaNacimientoPU);
        edtCelularPU=(EditText) findViewById(R.id.edtCelularPU);
        edtContraseñaPU=(EditText) findViewById(R.id.edtContraseñaPU);
        edtRepitaContraseñaPU=(EditText) findViewById(R.id.edtRepitaContraseñaPU);
        spnDepartamentoPU=(Spinner) findViewById(R.id.spnDepartamentoListPU);
        spnProvinciaPU=(Spinner) findViewById(R.id.spnProvinciaPU);
        spnDistritoPU=(Spinner) findViewById(R.id.spnDistritoPU);
        llenarDepartamentos();
        buscarUsuario("11112222");
        btnPerfilUsuario_Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerfilUsuario.this, com.example.hospitalproyectointegrador.PacienteIngreso.class));
                finish();
            }
        });
    }

    public void buscarUsuario(String username){
        Call<Usuario> call =  ApiAdapter.getUserService().UsuarioxUsername(username);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    objUsuario =   response.body();
                    mostrarDatos(objUsuario);
                    mensaje("OBJETO...",objUsuario.getApellidos());
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
    void mostrarDatos(Usuario objUsuario){
        edtDniPU.setText(objUsuario.getDni());
        edtNombrePU.setText(objUsuario.getNombre());
        edtApellidoPU.setText(objUsuario.getApellidos());
        edtFechaNacimientoPU.setText(objUsuario.getFechanacimiento());
        edtCelularPU.setText(objUsuario.getCelular());
        edtContraseñaPU.setText(objUsuario.getContraseña());
        edtRepitaContraseñaPU.setText(objUsuario.getContraseña());
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
        spnDistritoPU.setAdapter(adapter);
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
        spnProvinciaPU.setAdapter(adapter);
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
        spnDepartamentoPU.setAdapter(adapter);
    }

    /*void seleccionarDistrito(List<Departamento> data){
        for(int i=0;i<data.size();i++) {
            if (data.get(i).getId_departamento() == codigoDistrito) {
                spDistrito.setSelection(i);
                break;
            }
        }
    }*/

    void mensaje(String s, String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
    void mensajeError(String s, Throwable msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(), (CharSequence) msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}

package com.example.hospitalproyectointegrador.Interfaces;

import com.example.hospitalproyectointegrador.models.Area;
import com.example.hospitalproyectointegrador.models.Cita;
import com.example.hospitalproyectointegrador.models.Departamento;
import com.example.hospitalproyectointegrador.models.Distrito;
import com.example.hospitalproyectointegrador.models.Hora;
import com.example.hospitalproyectointegrador.models.LoginRequest;
import com.example.hospitalproyectointegrador.models.LoginResponse;
import com.example.hospitalproyectointegrador.models.Provincia;
import com.example.hospitalproyectointegrador.models.Rol;
import com.example.hospitalproyectointegrador.models.Sede;
import com.example.hospitalproyectointegrador.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST("usuario/login")
    Call<ArrayList<LoginResponse>> userLogin(@Body LoginRequest loginRequest);

    @GET("departamento")
    Call<List<Departamento>> getDepartamentos();

    @GET("provincia/xDepartamento/{idDepartamento}")
    public abstract Call<List<Provincia>> buscaProvincia(@Path("idDepartamento") String idDepartamento);

    @GET("distrito/xProvincia/{idProvincia}")
    public abstract Call<List<Distrito>> buscaDistrito(@Path("idProvincia") int idProvincia);

    @POST("usuario")
    public abstract Call<Void> regisUsuario(@Body Usuario objUsuario);

    @GET("usuario/{username}")
    public abstract Call<Usuario> UsuarioxUsername(@Path("username") String username);

    //Kevin
    @GET("sede")
    Call<List<Sede>> getSedes();
    @GET("area")
    Call<List<Area>> getAreas();
    @GET("hora")
    Call<List<Hora>> getHoras();
    @PUT("usuario/{idUsuario}")
    public abstract Call<Void> updateUsuario(@Path("idUsuario") int idUsuario, @Body Usuario objUsuario);
    @POST("cita")
    public abstract Call<Void> saveCita(@Body Cita objCita);
}

package com.example.hospitalproyectointegrador.utils;

import android.app.Application;

public class GlobalClass extends Application {
    private String dni;
    private String nombre;
    private Integer id_Paciente;
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_Paciente() {
        return id_Paciente;
    }

    public void setId_Paciente(Integer id_Paciente) {
        this.id_Paciente = id_Paciente;
    }
}

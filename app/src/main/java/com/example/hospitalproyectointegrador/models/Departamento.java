package com.example.hospitalproyectointegrador.models;

import java.io.Serializable;

public class Departamento{
    private String id_departamento;
    private String nombre_departamento;

    public Departamento() {
    }
    public Departamento(String id_departamento, String nombre_departamento) {
        this.id_departamento = id_departamento;
        this.nombre_departamento = nombre_departamento;
    }
    public String getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(String id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }
}

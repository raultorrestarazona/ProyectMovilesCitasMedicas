package com.example.hospitalproyectointegrador.models;

public class LoginResponse {

    private String dni;
    private String nombre_rol;
    private String nombre;
    private String apellido;
    private Integer id_Paciente;
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getId_Paciente() {
        return id_Paciente;
    }

    public void setId_Paciente(Integer id_Paciente) {
        this.id_Paciente = id_Paciente;
    }
}

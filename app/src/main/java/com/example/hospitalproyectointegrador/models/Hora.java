package com.example.hospitalproyectointegrador.models;

public class Hora {
    private int id_hora;
    private String rango_horas;
    private boolean estado;

    public int getId_hora() {
        return id_hora;
    }

    public void setId_hora(int id_hora) {
        this.id_hora = id_hora;
    }

    public String getRango_horas() {
        return rango_horas;
    }

    public void setRango_horas(String rango_horas) {
        this.rango_horas = rango_horas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

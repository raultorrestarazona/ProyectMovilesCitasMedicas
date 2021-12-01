package com.example.hospitalproyectointegrador.utils;

import android.app.Application;

public class GlobalClass extends Application {
    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}

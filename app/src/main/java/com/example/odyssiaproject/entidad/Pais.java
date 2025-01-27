package com.example.odyssiaproject.entidad;

import java.util.List;

public class Pais {
    private int id;
    private List<Ciudad> listaCiudades;
    private String Nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", listaCiudades=" + listaCiudades +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}

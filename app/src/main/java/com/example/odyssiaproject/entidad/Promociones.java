package com.example.odyssiaproject.entidad;

public class Promociones {
    private String nombre;
    private int id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Promociones{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}

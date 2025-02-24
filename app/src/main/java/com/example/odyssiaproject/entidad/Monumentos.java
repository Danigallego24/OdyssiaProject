package com.example.odyssiaproject.entidad;

public class Monumentos {

    private String nombre;
    private String horario;
    private String precio;
    private Ciudad ciudad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Monumentos{" +
                "nombre='" + nombre + '\'' +
                ", horario='" + horario + '\'' +
                ", precio='" + precio + '\'' +
                ", ciudad=" + ciudad +
                '}';
    }
}

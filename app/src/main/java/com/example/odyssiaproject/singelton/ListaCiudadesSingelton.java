package com.example.odyssiaproject.singelton;

import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Pais;
import java.util.ArrayList;
import java.util.List;

public class ListaCiudadesSingelton {
    private static ListaCiudadesSingelton instance;
    private List<Ciudad> listCiudades;
    private int contador = 1;

    private ListaCiudadesSingelton(){
        listCiudades = new ArrayList<>();  // Inicializa la lista de ciudades
    }

    public static ListaCiudadesSingelton getInstance() {
        if(instance == null){
            instance = new ListaCiudadesSingelton();
        }
        return instance;
    }

    public Ciudad getCiudadByName(String nombre) {
        for (Ciudad c : listCiudades) {
            if (c.getNombre().equals(nombre)) {
                return c;
            }
        }
        return null;
    }

    // Método privado para agregar una ciudad
    private void agregarCiudad(String nombreCiudad, String nombrePais, String descripcion) {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(contador++);  // Se incrementa el contador
        ciudad.setNombre(nombreCiudad);
        ciudad.setDescripcion(descripcion);

        Pais pais = new Pais();
        pais.setNombre(nombrePais);
        ciudad.setPais(pais);  // Se asigna el país a la ciudad

        listCiudades.add(ciudad);  // Se agrega la ciudad a la lista
    }

    public List<Ciudad> getListaCiudades() {
        return listCiudades;
    }
}

package com.example.odyssiaproject.singelton;

import android.util.Log;
import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Pais;
import java.util.ArrayList;
import java.util.List;

public class ListaCiudadesSingelton {
    private static ListaCiudadesSingelton instance;
    private List<Ciudad> listCiudades;
    private int contador = 1;

    private ListaCiudadesSingelton(){
        super();
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

        public void inicializar() {
        listCiudades = new ArrayList<>();

        agregarCiudad("Madrid", "España");
        agregarCiudad("Barcelona", "España");
        agregarCiudad("Sevilla", "España");

        agregarCiudad("Roma", "Italia");
        agregarCiudad("Florencia", "Italia");
        agregarCiudad("Venecia", "Italia");

        agregarCiudad("Paris", "Francia");
        agregarCiudad("Lyon", "Francia");
        agregarCiudad("Marsella", "Francia");

        agregarCiudad("Zurich", "Suiza");
        agregarCiudad("Ginebra", "Suiza");
        agregarCiudad("Berna", "Suiza");

        agregarCiudad("Santorini", "Grecia");
        agregarCiudad("Atenas", "Grecia");
        agregarCiudad("Salonica", "Grecia");

        agregarCiudad("Porto", "Portugal");
        agregarCiudad("Lisboa", "Portugal");
        agregarCiudad("Braga", "Portugal");

        agregarCiudad("Bruselas", "Belgica");
        agregarCiudad("Brujas", "Belgica");
        agregarCiudad("Amberes", "Belgica");

        agregarCiudad("Oslo", "Noruega");
        agregarCiudad("Bergen", "Noruega");
        agregarCiudad("Tromso", "Noruega");

        agregarCiudad("Londres", "Inglatera");
        agregarCiudad("Manchester", "Inglatera");
        agregarCiudad("Liverpool", "Inglatera");

        agregarCiudad("Amsterdan", "Holanda");
        agregarCiudad("Rotterdam", "Holanda");
        agregarCiudad("Utrecht", "Holanda");

        Log.i("ListaCiudadesSingleton", "Lista de ciudades inicializada con éxito.");
    }

    // Método auxiliar para crear y agregar una ciudad, asignándole su país
    private void agregarCiudad(String nombreCiudad, String nombrePais) {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(contador++);
        ciudad.setNombre(nombreCiudad);

        Pais pais = new Pais();
        pais.setNombre(nombrePais);
        ciudad.setPais(pais);

        listCiudades.add(ciudad);
    }

    public List<Ciudad> getListaCiudades() {
        return listCiudades;
    }
}

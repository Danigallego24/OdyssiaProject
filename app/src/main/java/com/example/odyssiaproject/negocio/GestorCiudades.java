package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.singelton.ListaCiudadesSingelton;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;

public class GestorCiudades {

    private final ListaCiudadesSingelton listaCiudades;

    public GestorCiudades() {
        // Inicializamos una sola vez el singleton
        this.listaCiudades = ListaCiudadesSingelton.getInstance();
    }

    public String imagenCiudad(Ciudad c) {

        if (c == null || c.getNombre() == null) {
            return "0";
        }
        // Se busca el país en el singleton (podría hacerse para validar que exista)
        Ciudad ciudad = listaCiudades.getCiudadByName(c.getNombre());
        if (ciudad == null) {
            return "0";
        }
        String nombreCiudad = ciudad.getNombre().toLowerCase();

        // Si solo necesitas devolver el mismo nombre, este bloque es redundante.
        // En su lugar, podrías devolver el nombre directamente, o mapearlo a un código.
        return ciudad.getImagen();

    }
}

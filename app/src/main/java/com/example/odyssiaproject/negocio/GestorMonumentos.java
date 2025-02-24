package com.example.odyssiaproject.negocio;


import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Monumentos;
import com.example.odyssiaproject.singelton.ListaMonumentosSingelton;

import java.util.ArrayList;
import java.util.List;

public class GestorMonumentos {

    private ListaMonumentosSingelton listaMonumentos;

    public GestorMonumentos() {
        this.listaMonumentos = ListaMonumentosSingelton.getInstance();
        this.listaMonumentos.inicializar();
    }

    // Listar todos los monumentos
    public void listarTodosLosMonumentos() {
        for (Monumentos m : listaMonumentos.getListaMonumentos()) {
            System.out.println(m.getNombre() + " - " + m.getCiudad().getNombre() + " - " + m.getPrecio() + " - " + m.getHorario());
        }
    }

    // Buscar un monumento por nombre
    public List<Monumentos> buscarMonumentoPorNombre(String nombre) {
        return listaMonumentos.getMonumentosByName(nombre);
    }

    // Listar monumentos por ciudad
    public void listarMonumentosPorCiudad(String ciudad) {
        List<Monumentos> monumentosCiudad = new ArrayList<>();

        for (Monumentos m : listaMonumentos.getListaMonumentos()) {
            if (m.getCiudad().getNombre().equalsIgnoreCase(ciudad)) {
                monumentosCiudad.add(m);
            }
        }

        if (monumentosCiudad.isEmpty()) {
            System.out.println("No hay monumentos en " + ciudad);
        } else {
            for (Monumentos m : monumentosCiudad) {
                System.out.println(m.getNombre() + " - " + m.getCiudad().getNombre()
                        + " - " + m.getPrecio() + " - " + m.getHorario());
            }
        }
    }
}

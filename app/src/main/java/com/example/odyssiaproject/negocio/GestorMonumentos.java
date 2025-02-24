package com.example.odyssiaproject.negocio;

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

    // Para depuración: listar todos los monumentos
    public void listarTodosLosMonumentos() {
        for (Monumentos m : listaMonumentos.getListaMonumentos()) {
            System.out.println(m.getNombre() + " - " + m.getCiudad().getNombre() + " - " +
                    m.getPrecio() + " - " + m.getHorario());
        }
    }

    // Buscar monumentos por nombre
    public List<Monumentos> buscarMonumentoPorNombre(String nombre) {
        return listaMonumentos.getMonumentosByName(nombre);
    }

    // Devuelve la lista de monumentos filtrada por ciudad
    public List<Monumentos> obtenerMonumentosPorCiudad(String ciudad) {
        List<Monumentos> monumentosCiudad = new ArrayList<>();
        for (Monumentos m : listaMonumentos.getListaMonumentos()) {
            if (m.getCiudad().getNombre().equalsIgnoreCase(ciudad)) {
                monumentosCiudad.add(m);
            }
        }
        return monumentosCiudad;
    }
}

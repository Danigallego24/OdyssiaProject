package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.singelton.ListaCiudadesSingelton;

public class GestorCiudades {

    private ListaCiudadesSingelton listaCiudades;

    public int imagenCiudad(Ciudad c) {
        listaCiudades = ListaCiudadesSingelton.getInstance();
        Ciudad ciudad = listaCiudades.getCiudadByName(c.getNombre());
        if (ciudad != null) {
            String nombreCiudad = ciudad.getNombre();
            if (nombreCiudad.equals("Madrid")) {
                return 1;
            } else if (nombreCiudad.equals("Barcelona")) {
                return 2;
            } else if (nombreCiudad.equals("Sevilla")) {
                return 3;
            } else if (nombreCiudad.equals("Roma")) {
                return 4;
            } else if (nombreCiudad.equals("Florencia")) {
                return 5;
            } else if (nombreCiudad.equals("Venecia")) {
                return 6;
            } else if (nombreCiudad.equals("Paris")) {
                return 7;
            } else if (nombreCiudad.equals("Lyon")) {
                return 8;
            } else if (nombreCiudad.equals("Marsella")) {
                return 9;
            } else if (nombreCiudad.equals("Zurich")) {
                return 10;
            } else if (nombreCiudad.equals("Ginebra")) {
                return 11;
            } else if (nombreCiudad.equals("Berna")) {
                return 12;
            } else if (nombreCiudad.equals("Santorini")) {
                return 13;
            } else if (nombreCiudad.equals("Atenas")) {
                return 14;
            } else if (nombreCiudad.equals("Salonica")) {
                return 15;
            } else if (nombreCiudad.equals("Porto")) {
                return 16;
            } else if (nombreCiudad.equals("Lisboa")) {
                return 17;
            } else if (nombreCiudad.equals("Braga")) {
                return 18;
            } else if (nombreCiudad.equals("Bruselas")) {
                return 19;
            } else if (nombreCiudad.equals("Brujas")) {
                return 20;
            } else if (nombreCiudad.equals("Amberes")) {
                return 21;
            } else if (nombreCiudad.equals("Oslo")) {
                return 22;
            } else if (nombreCiudad.equals("Bergen")) {
                return 23;
            } else if (nombreCiudad.equals("Tromso")) {
                return 24;
            } else if (nombreCiudad.equals("Londres")) {
                return 25;
            } else if (nombreCiudad.equals("Manchester")) {
                return 26;
            } else if (nombreCiudad.equals("Liverpool")) {
                return 27;
            } else if (nombreCiudad.equals("Amsterdan")) {
                return 28;
            } else if (nombreCiudad.equals("Rotterdam")) {
                return 29;
            } else if (nombreCiudad.equals("Utrecht")) {
                return 30;
            }
        }
        return 0;
    }
}

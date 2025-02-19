package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.singelton.ListaCiudadesSingelton;

public class GestorCiudades {

    private ListaCiudadesSingelton listaCiudades;

    public String imagenCiudad(Ciudad c) {
        listaCiudades = ListaCiudadesSingelton.getInstance();
        Ciudad ciudad = listaCiudades.getCiudadByName(c.getNombre());
        if (ciudad != null) {
            String nombreCiudad = ciudad.getNombre();
            if (nombreCiudad.equals("Madrid")) {
                return "Madrid";
            } else if (nombreCiudad.equals("Barcelona")) {
                return "Barcelona";
            } else if (nombreCiudad.equals("Sevilla")) {
                return "Sevilla";
            } else if (nombreCiudad.equals("Roma")) {
                return "Roma";
            } else if (nombreCiudad.equals("Florencia")) {
                return "Florencia";
            } else if (nombreCiudad.equals("Venecia")) {
                return "Venecia";
            } else if (nombreCiudad.equals("Paris")) {
                return "Paris";
            } else if (nombreCiudad.equals("Lyon")) {
                return "Lyon";
            } else if (nombreCiudad.equals("Marsella")) {
                return "Marsella";
            } else if (nombreCiudad.equals("Zurich")) {
                return "Zurich";
            } else if (nombreCiudad.equals("Ginebra")) {
                return "Ginebra";
            } else if (nombreCiudad.equals("Berna")) {
                return "Berna";
            } else if (nombreCiudad.equals("Santorini")) {
                return "Santorini";
            } else if (nombreCiudad.equals("Atenas")) {
                return "Atenas";
            } else if (nombreCiudad.equals("Salonica")) {
                return "Salonica";
            } else if (nombreCiudad.equals("Porto")) {
                return "Porto";
            } else if (nombreCiudad.equals("Lisboa")) {
                return "Lisboa";
            } else if (nombreCiudad.equals("Braga")) {
                return "Braga";
            } else if (nombreCiudad.equals("Bruselas")) {
                return "Bruselas";
            } else if (nombreCiudad.equals("Brujas")) {
                return "Brujas";
            } else if (nombreCiudad.equals("Amberes")) {
                return "Amberes";
            } else if (nombreCiudad.equals("Oslo")) {
                return "Oslo";
            } else if (nombreCiudad.equals("Bergen")) {
                return "Bergen";
            } else if (nombreCiudad.equals("Tromso")) {
                return "Tromso";
            } else if (nombreCiudad.equals("Londres")) {
                return "Londres";
            } else if (nombreCiudad.equals("Manchester")) {
                return "Manchester";
            } else if (nombreCiudad.equals("Liverpool")) {
                return "Liverpool";
            } else if (nombreCiudad.equals("Amsterdam")) {
                return "Amsterdam";
            } else if (nombreCiudad.equals("Rotterdam")) {
                return "Rotterdam";
            } else if (nombreCiudad.equals("Utrecht")) {
                return "Utrecht";
            }
        }
        return "0";
    }
}

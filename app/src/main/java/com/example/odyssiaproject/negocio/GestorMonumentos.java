package com.example.odyssiaproject.negocio;

import android.util.Log;

import com.example.odyssiaproject.entidad.Monumentos;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.singelton.ListaMonumentosSingelton;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;

import java.util.ArrayList;
import java.util.List;

public class GestorMonumentos {

    private ListaMonumentosSingelton listaMonumentos;

    public String imagenMonumento(Monumentos m) {
        if (m == null) {
            Log.e("GestorMonumentos", "m es null");
            return "error";
        }
        listaMonumentos = ListaMonumentosSingelton.getInstance();
        Monumentos monumento = listaMonumentos.getMonumentosByName(m.getNombre());

        if (monumento.equals("Plaza Mayor")) {
            return "Plaza Mayor";
        } else if (monumento.getNombre().equals("Puerta de Alcalá")) {
            return "Puerta de Alcalá";
        } else if (monumento.getNombre().equals("Parque de El Retiro")) {
            return "Parque de El Retiro";
        } else if (monumento.getNombre().equals("Basílica de la Sagrada Familia")) {
            return "Basílica de la Sagrada Familia";
        } else if (monumento.getNombre().equals("Parque Güell")) {
            return "Parque Güell";
        } else if (monumento.getNombre().equals("Casa Batlló")) {
            return "Casa Batlló";
        } else if (monumento.getNombre().equals("Catedral de Sevilla y La Giralda")) {
            return "Catedral de Sevilla y La Giralda";
        } else if (monumento.getNombre().equals("Real Alcázar de Sevilla")) {
            return "Real Alcázar de Sevilla";
        } else if (monumento.getNombre().equals("Plaza de España")) {
            return "Plaza de España";
        } else if (monumento.getNombre().equals("Catedral de Nuestra Señora")) {
            return "Catedral de Nuestra Señora";
        } else if (monumento.getNombre().equals("Museo Plantin-Moretus")) {
            return "Museo Plantin-Moretus";
        } else if (monumento.getNombre().equals("Castillo de Steen")) {
            return "Castillo de Steen";
        } else if (monumento.getNombre().equals("Campanario de Brujas")) {
            return "Campanario de Brujas";
        } else if (monumento.getNombre().equals("Basílica de la Santa Sangre")) {
            return "Basílica de la Santa Sangre";
        } else if (monumento.getNombre().equals("Hospital de San Juan y Museo Memling")) {
            return "Hospital de San Juan y Museo Memling";
        } else if (monumento.getNombre().equals("Atomium")) {
            return "Atomium";
        } else if (monumento.getNombre().equals("Manneken Pis")) {
            return "Manneken Pis";
        } else if (monumento.getNombre().equals("Palacio Real de Bruselas")) {
            return "Palacio Real de Bruselas";
        } else if (monumento.getNombre().equals("Basílica de Notre-Dame de Fourvière")) {
            return "Basílica de Notre-Dame de Fourvière";
        } else if (monumento.getNombre().equals("Teatros Romanos de Fourvière")) {
            return "Teatros Romanos de Fourvière";
        } else if (monumento.getNombre().equals("Parque de la Tête d'Or")) {
            return "Parque de la Tête d'Or";
        } else if (monumento.getNombre().equals("Basílica de Notre-Dame de la Garde")) {
            return "Basílica de Notre-Dame de la Garde";
        } else if (monumento.getNombre().equals("MUCEM")) {
            return "MUCEM";
        } else if (monumento.getNombre().equals("Fuerte de San Juan")) {
            return "Fuerte de San Juan";
        } else if (monumento.getNombre().equals("Torre Eiffel")) {
            return "Torre Eiffel";
        } else if (monumento.getNombre().equals("Catedral de Notre-Dame")) {
            return "Catedral de Notre-Dame";
        } else if (monumento.getNombre().equals("Arco de Triunfo")) {
            return "Arco de Triunfo";
        } else if (monumento.getNombre().equals("Acrópolis de Atenas")) {
            return "Acrópolis de Atenas";
        } else if (monumento.getNombre().equals("Museo de la Acrópolis")) {
            return "Museo de la Acrópolis";
        } else if (monumento.getNombre().equals("Ágora Antigua")) {
            return "Ágora Antigua";
        } else if (monumento.getNombre().equals("Torre Blanca")) {
            return "Torre Blanca";
        } else if (monumento.getNombre().equals("Arco de Galerio")) {
            return "Arco de Galerio";
        } else if (monumento.getNombre().equals("Rotonda de San Jorge")) {
            return "Rotonda de San Jorge";
        } else if (monumento.getNombre().equals("Museo Arqueológico de Thera")) {
            return "Museo Arqueológico de Thera";
        } else if (monumento.getNombre().equals("Sitio Arqueológico de Akrotiri")) {
            return "Sitio Arqueológico de Akrotiri";
        } else if (monumento.getNombre().equals("Castillo de Oia")) {
            return "Castillo de Oia";
        }
        return "Monumento no definido";
    }
}

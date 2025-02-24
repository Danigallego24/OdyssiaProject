package com.example.odyssiaproject.singelton;

import android.util.Log;

import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Monumentos;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;

import java.util.ArrayList;
import java.util.List;

public class ListaMonumentosSingelton {

    private static ListaMonumentosSingelton instance;

    private List<Monumentos> listaMonumentos;
    private ListaMonumentosSingelton() {
        super();
    }
    public static ListaMonumentosSingelton getInstance() {
        if (instance == null) {
            synchronized (ListaMonumentosSingelton.class) {
                if (instance == null) {
                    instance = new ListaMonumentosSingelton();
                }
            }
        }
        return instance;
    }
    public List<Monumentos> getMonumentosByName(String nombre) {
        List<Monumentos> resultado = new ArrayList<>();
        for (Monumentos m : listaMonumentos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                resultado.add(m);
            }
        }
        return resultado;
    }
    public void inicializar() {
        listaMonumentos = new ArrayList<>();
        agregarMonumento("Plaza Mayor","Madrid","GRATIS","24h/dia");
        agregarMonumento("Puerta de Alcalá","Madrid","GRATIS","24h/dia");
        agregarMonumento("Parque de El Retiro","Madrid","GRATIS","6:00-22:00");

        agregarMonumento("Basílica de la Sagrada Familia","Barcelona","Desde 26€","9:00-20:00");
        agregarMonumento("Parque Güell","Barcelona","Desde 10€","9:30-19:30");
        agregarMonumento("Casa Batlló","Barcelona","Desde 10€","9:00-20:00");

        agregarMonumento("Catedral de Sevilla y La Giralda","Sevilla","Desde 14€","10:45 a 17:00");
        agregarMonumento("Real Alcázar de Sevilla","Sevilla","Desde 14.50€","9:30 a 17:00");
        agregarMonumento("Plaza de España","Sevilla","GRATIS","24/dia");

        agregarMonumento("Catedral de Nuestra Señora", "Amberes", "Desde 12€", "10:00-17:00");
        agregarMonumento("Museo Plantin-Moretus", "Amberes", "Desde 12€", "10:00-17:00");
        agregarMonumento("Castillo de Steen", "Amberes", "GRATIS", "10:00-18:00");

        agregarMonumento("Campanario de Brujas", "Brujas", "Desde 14€", "9:30-18:00");
        agregarMonumento("Basílica de la Santa Sangre", "Brujas", "GRATIS", "9:30-12:00-14:00-17:00");
        agregarMonumento("Hospital de San Juan y Museo Memling", "Brujas", "Desde 12€", "9:30-17:00");

        agregarMonumento("Atomium", "Bruselas", "Desde 16€", "10:00-18:00");
        agregarMonumento("Manneken Pis", "Bruselas", "GRATIS", "24/dia");
        agregarMonumento("Palacio Real de Bruselas", "Bruselas", "GRATIS", "10:30-15:45");

        agregarMonumento("Basílica de Notre-Dame de Fourvière", "Lyon", "GRATIS", "7:00-19:00");
        agregarMonumento("Teatros Romanos de Fourvière", "Lyon", "GRATIS", "7:00-19:00");
        agregarMonumento("Parque de la Tête d'Or", "Lyon", "GRATIS", "6:30-22:30");

        agregarMonumento("Basílica de Notre-Dame de la Garde", "Marsella", "GRATIS", "7:00-18:15");
        agregarMonumento("MUCEM", "Marsella", "Desde 11€", "10:00-18:00");
        agregarMonumento("Fuerte de San Juan", "Marsella", "GRATIS", "10:00-20:00");

        agregarMonumento("Torre Eiffel", "París", "Desde 10€", "9:30-23:45");
        agregarMonumento("Catedral de Notre-Dame", "París", "N/A", "Temporalmente cerrada");
        agregarMonumento("Arco de Triunfo", "París", "13€", "10:00-23:00");

        agregarMonumento("Acrópolis de Atenas", "Atenas", "Desde 20€", "8:00-20:00");
        agregarMonumento("Museo de la Acrópolis", "Atenas", "Desde 10€", "8:00-16:00");
        agregarMonumento("Ágora Antigua", "Atenas", "Desde 30 €", "8:00-20:00");

        agregarMonumento("Torre Blanca", "Salónica", "Desde 4€", "8:00-20:00");
        agregarMonumento("Arco de Galerio", "Salónica", "GRATIS", "24/dia");
        agregarMonumento("Rotonda de San Jorge", "Salónica", "Desde 6€", "8:00-15:00");

        agregarMonumento("Museo Arqueológico de Thera", "Santorini", "Desde 6€", "08:30-15:30");
        agregarMonumento("Sitio Arqueológico de Akrotiri", "Santorini", "Desde 12 €", "08:00-15:00");
        agregarMonumento("Castillo de Oia", "Santorini", "GRATIS", "24/dia");
        // Imprime el último monumento agregado en los logs.
        Log.i("ListaPromocionesSingleton", "tamaño lista" + listaMonumentos.size());
    }

    private void agregarMonumento(String nombreMonumento, String nombreCiudad, String precio, String horario) {
        Monumentos monumentos = new Monumentos();
        monumentos.setNombre(nombreMonumento);
        monumentos.setHorario(horario);
        monumentos.setPrecio(precio);

        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(nombreCiudad);
        monumentos.setCiudad(ciudad);

        listaMonumentos.add(monumentos);
    }
    private List<Monumentos> obtenerMonumentosPorCiudad(String nombreCiudad) {
        List<Monumentos> resultado = new ArrayList<>();
        for (Monumentos m : listaMonumentos) {
            if (m.getCiudad().getNombre().equalsIgnoreCase(nombreCiudad)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public List<Monumentos> getListaMonumentos() {
        return listaMonumentos;
    }
}

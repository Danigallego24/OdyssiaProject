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
        agregarCiudad("Madrid", "España", "La vibrante capital de España, donde la historia, la cultura y la modernidad se encuentran en cada rincón.");
        agregarCiudad("Barcelona", "España", "Una ciudad cosmopolita junto al mar Mediterráneo, famosa por su arquitectura modernista y su ambiente vibrante.");
        agregarCiudad("Sevilla", "España", "Cuna del flamenco y de la pasión andaluza, con su impresionante Alcázar y la icónica Giralda.");

        agregarCiudad("Roma", "Italia", "La ciudad eterna, donde cada calle es un museo y el pasado convive con el presente.");
        agregarCiudad("Florencia", "Italia", "La cuna del Renacimiento, hogar de grandes artistas y joyas arquitectónicas como el Duomo.");
        agregarCiudad("Venecia", "Italia", "La mágica ciudad de los canales, donde el romance fluye entre góndolas y palacios históricos.");

        agregarCiudad("Paris", "Francia", "La ciudad del amor y la luz, donde la Torre Eiffel y el arte del Louvre inspiran a millones.");
        agregarCiudad("Lyon", "Francia", "El epicentro gastronómico de Francia, con su encantador casco antiguo y su rica historia.");
        agregarCiudad("Marsella", "Francia", "Un puerto lleno de vida en el Mediterráneo, donde convergen culturas y sabores únicos.");

        agregarCiudad("Zurich", "Suiza", "El corazón financiero de Suiza, rodeado de montañas y con un casco histórico fascinante.");
        agregarCiudad("Ginebra", "Suiza", "La capital de la diplomacia mundial, a orillas de un hermoso lago y con vistas a los Alpes.");
        agregarCiudad("Berna", "Suiza", "La capital suiza, con su casco antiguo medieval declarado Patrimonio de la Humanidad por la UNESCO.");

        agregarCiudad("Santorini", "Grecia", "La joya del Egeo, con sus casas blancas, cúpulas azules y las mejores puestas de sol del mundo.");
        agregarCiudad("Atenas", "Grecia", "Cuna de la civilización occidental, donde la Acrópolis sigue dominando el horizonte.");
        agregarCiudad("Salonica", "Grecia", "Una vibrante ciudad portuaria con una mezcla única de historia bizantina y cultura contemporánea.");

        agregarCiudad("Porto", "Portugal", "La ciudad del vino de Oporto, con su encantador casco antiguo y sus puentes sobre el río Duero.");
        agregarCiudad("Lisboa", "Portugal", "Una ciudad de colinas, tranvías y fado, donde la historia y la modernidad van de la mano.");
        agregarCiudad("Braga", "Portugal", "Una de las ciudades más antiguas de Portugal, llena de iglesias barrocas y una energía juvenil vibrante.");

        agregarCiudad("Bruselas", "Bélgica", "La capital de Europa, con su impresionante Grand Place y su irresistible chocolate belga.");
        agregarCiudad("Brujas", "Bélgica", "Un cuento de hadas hecho ciudad, con canales pintorescos y arquitectura medieval.");
        agregarCiudad("Amberes", "Bélgica", "El corazón del diamante y la moda belga, con un puerto vibrante y una arquitectura sorprendente.");

        agregarCiudad("Oslo", "Noruega", "La capital de la naturaleza y el diseño escandinavo, rodeada de fiordos y bosques.");
        agregarCiudad("Bergen", "Noruega", "La puerta de entrada a los fiordos noruegos, con su icónico muelle de casas de colores.");
        agregarCiudad("Tromso", "Noruega", "El mejor lugar para ver auroras boreales, rodeado de montañas árticas y una naturaleza espectacular.");

        agregarCiudad("Londres", "Inglaterra", "Una metrópoli vibrante donde la historia, la cultura y la modernidad se encuentran en cada esquina.");
        agregarCiudad("Manchester", "Inglaterra", "El alma del rock y el fútbol inglés, con una energía urbana inigualable.");
        agregarCiudad("Liverpool", "Inglaterra", "La cuna de los Beatles y un puerto lleno de historia y cultura.");

        agregarCiudad("Ámsterdam", "Holanda", "Una ciudad de canales y bicicletas, donde el arte, la historia y la modernidad se mezclan a la perfección.");
        agregarCiudad("Rotterdam", "Holanda", "La ciudad de la innovación y la arquitectura vanguardista, con uno de los puertos más grandes del mundo.");
        agregarCiudad("Utrecht", "Holanda", "El encanto del corazón de los Países Bajos, con canales únicos y una rica historia universitaria.");


            Log.i("ListaCiudadesSingleton", "Lista de ciudades inicializada con éxito.");
    }

    private void agregarCiudad(String nombreCiudad, String nombrePais, String descripcion) {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(contador++);
        ciudad.setNombre(nombreCiudad);
        ciudad.setDescripcion(descripcion);

        Pais pais = new Pais();
        pais.setNombre(nombrePais);
        ciudad.setPais(pais);

        listCiudades.add(ciudad);
    }

    public List<Ciudad> getListaCiudades() {
        return listCiudades;
    }
}

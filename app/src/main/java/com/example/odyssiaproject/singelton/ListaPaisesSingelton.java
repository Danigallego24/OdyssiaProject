package com.example.odyssiaproject.singelton;

import android.util.Log;

import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Pais;

import java.util.ArrayList;
import java.util.List;

public class ListaPaisesSingelton {
    private static ListaPaisesSingelton instance;
    private List<Pais> listPaises;
    private List<Ciudad> listaCiudades;
    private int contador = 1;

    private ListaPaisesSingelton(){
        super();
    }
    public static ListaPaisesSingelton getInstance() {
        if (instance == null) {
            instance = new ListaPaisesSingelton();
        }
        if (instance.listPaises == null) {
            instance.inicializar();
        }
        return instance;
    }

    public Pais getPaisByName(String nombre) {
        for (Pais p : listPaises) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }


    public void inicializar(){
        listaCiudades = ListaCiudadesSingelton.getInstance().getListaCiudades();
        listPaises = new ArrayList<>();
        Pais pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("España");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Italia");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Francia");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Suiza");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Grecia");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Portugal");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Belgica");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Noruega");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Inglaterra");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Holanda");
        pais.setListaCiudades(obtenerCiudadesPorPais(pais.getNombre(), listaCiudades));
        listPaises.add(pais);

        for (Pais p : listPaises) {
            Log.i("ListaPaises", "País: " + p.getNombre());
        }
    }
    private List<Ciudad> obtenerCiudadesPorPais(String nombrePais, List<Ciudad> ciudades) {
        List<Ciudad> resultado = new ArrayList<>();
        if (nombrePais.equals("España")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Madrid") ||
                        c.getNombre().equals("Barcelona") ||
                        c.getNombre().equals("Sevilla")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Italia")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Roma") ||
                        c.getNombre().equals("Florencia") ||
                        c.getNombre().equals("Venecia")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Francia")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Paris") ||
                        c.getNombre().equals("Lyon") ||
                        c.getNombre().equals("Marsella")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Suiza")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Zurich") ||
                        c.getNombre().equals("Ginebra") ||
                        c.getNombre().equals("Berna")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Grecia")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Santorini") ||
                        c.getNombre().equals("Atenas") ||
                        c.getNombre().equals("Salonica")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Portugal")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Porto") ||
                        c.getNombre().equals("Lisboa") ||
                        c.getNombre().equals("Braga")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Belgica")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Bruselas") ||
                        c.getNombre().equals("Brujas") ||
                        c.getNombre().equals("Amberes")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Noruega")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Oslo") ||
                        c.getNombre().equals("Bergen") ||
                        c.getNombre().equals("Tromso")) {
                    resultado.add(c);
                }
            }
        } else if (nombrePais.equals("Inglaterra")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Londres") ||
                        c.getNombre().equals("Manchester") ||
                        c.getNombre().equals("Liverpool")) {
                    resultado.add(c);
                }
            }

    } else if (nombrePais.equals("Holanda")) {
            for (Ciudad c : ciudades) {
                if (c.getNombre().equals("Amsterdam") ||
                        c.getNombre().equals("Rotterdam") ||
                        c.getNombre().equals("Utrecht")) {
                    resultado.add(c);
                }
            }
        }

        return resultado;
    }

    public List<Pais> getListaPaises() {
        return listPaises;
    }
}

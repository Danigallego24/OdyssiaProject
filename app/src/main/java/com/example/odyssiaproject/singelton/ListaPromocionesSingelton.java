package com.example.odyssiaproject.singelton;

import android.util.Log;

import com.example.odyssiaproject.entidad.Promociones;

import java.util.ArrayList;
import java.util.List;

public class ListaPromocionesSingelton {
    private static ListaPromocionesSingelton instance;
    private List<Promociones> listaPromociones;
    private int contador = 1;

    private ListaPromocionesSingelton(){
        super();
    }

    public static ListaPromocionesSingelton getInstance() {
        if(instance == null){
            instance = new ListaPromocionesSingelton();
        }
        return instance;
    }

    public Promociones getPromocionById(int id) {
        for (Promociones p : listaPromociones) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public void inicializar(){
        listaPromociones = new ArrayList<>();
        Promociones promociones = new Promociones();
        promociones.setId(contador++);
        promociones.setNombre("Rebajas");

        listaPromociones.add(promociones);

        promociones = new Promociones();
        promociones.setId(contador++);
        promociones.setNombre("Pack");

        listaPromociones.add(promociones);

        promociones = new Promociones();
        promociones.setId(1);
        promociones.setNombre("Pack");

        listaPromociones.add(promociones);

        promociones = new Promociones();
        promociones.setId(2);
        promociones.setNombre("Pack");

        listaPromociones.add(promociones);

        Log.i("ListaPromocionesSingleton", "########" + promociones);
    }

    public List<Promociones> getListaPromociones() {
        return listaPromociones;
    }
}

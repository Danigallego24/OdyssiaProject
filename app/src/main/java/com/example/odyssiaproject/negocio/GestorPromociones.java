package com.example.odyssiaproject.negocio;

import android.util.Log;

import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;

public class GestorPromociones {
    private ListaPromocionesSingelton listaPromociones;

    public int imagenPromocion (Promociones p){
        if(p == null){
            Log.e("GestorPromociones", "p es null");
            return -1;// Valor de error
        }
        listaPromociones = ListaPromocionesSingelton.getInstance();
        Promociones promocion = listaPromociones.getPromocionById(p.getId());

        if(promocion == null){
            Log.e("GestorPromociones", "Promocio es null" +  p.getId());
            return -1;
        }

        if(promocion.getId() == 1){
            return 1;
        } else if (promocion.getId() == 2) {
            return 2;
        }
        return 3;
    }
}
package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;

public class negocioPromociones {
    private ListaPromocionesSingelton listaPromociones;

    public int imagenPromocion (Promociones p){
        listaPromociones = ListaPromocionesSingelton.getInstance();
        Promociones promocion = listaPromociones.getPromocionById(p.getId());
        if(promocion.getId() == 1){
            return 1;
        } else if (promocion.getId() == 2) {
            return 2;
        }
        return 3;
    }
}

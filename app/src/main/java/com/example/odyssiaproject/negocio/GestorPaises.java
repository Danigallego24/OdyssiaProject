package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;

public class GestorPaises {
    private ListaPaisesSingelton listaPaises;
    public int imagenPaises(Pais p){
        listaPaises = ListaPaisesSingelton.getInstance();
        Pais pais = listaPaises.getPaisByName(p.getNombre());
        if(pais.getNombre().equals("España")){
            return 1;
        } else if (pais.getNombre().equals("Italia")) {
            return 2;
        }else if (pais.getNombre().equals("Francia")) {
            return 3;
        }else if (pais.getNombre().equals("Suiza")) {
            return 4;
        }else if (pais.getNombre().equals("Grecia")) {
            return 5;
        }else if (pais.getNombre().equals("Portugal")) {
            return 6;
        }else if (pais.getNombre().equals("Belgica")) {
            return 7;
        }else if (pais.getNombre().equals("Noruega")) {
            return 8;
        }else if (pais.getNombre().equals("Inglaterra")) {
            return 9;
        } else if (pais.getNombre().equals("Holanda")) {
            return 10;
        }
        return 0;
    }
}

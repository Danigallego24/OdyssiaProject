package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;

public class GestorPaises {
    private ListaPaisesSingelton listaPaises;
    public String imagenPaises(Pais p){
        listaPaises = ListaPaisesSingelton.getInstance();
        Pais pais = listaPaises.getPaisByName(p.getNombre());
        if(pais.getNombre().equals("España")){
            return "España";
        } else if (pais.getNombre().equals("Italia")) {
            return "Italia";
        }else if (pais.getNombre().equals("Francia")) {
            return "Francia";
        }else if (pais.getNombre().equals("Suiza")) {
            return "Suiza";
        }else if (pais.getNombre().equals("Grecia")) {
            return "Grecia";
        }else if (pais.getNombre().equals("Portugal")) {
            return "Portugal";
        }else if (pais.getNombre().equals("Belgica")) {
            return "Belgica";
        }else if (pais.getNombre().equals("Noruega")) {
            return "Noruega";
        }else if (pais.getNombre().equals("Inglaterra")) {
            return "Inglaterra";
        } else if (pais.getNombre().equals("Holanda")) {
            return "Holanda";
        }
        return "0";
    }
}

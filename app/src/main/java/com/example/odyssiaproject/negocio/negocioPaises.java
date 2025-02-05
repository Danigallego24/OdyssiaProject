package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;

public class negocioPaises {
    private ListaPaisesSingelton listaPaises;
    public int imagenPaises(Pais p){
        listaPaises = ListaPaisesSingelton.getInstance();
        Pais pais = listaPaises.getPaisById(p.getId());
        if(pais.getId() == 1){
            return 1;
        } else if (pais.getId() == 2) {
            return 2;
        }else if (pais.getId() == 3) {
            return 3;
        }else if (pais.getId() == 4) {
            return 4;
        }else if (pais.getId() == 5) {
            return 5;
        }else if (pais.getId() == 6) {
            return 6;
        }else if (pais.getId() == 7) {
            return 7;
        }else if (pais.getId() == 8) {
            return 8;
        }else if (pais.getId() == 9) {
            return 9;
        }
        return 10;
    }
}

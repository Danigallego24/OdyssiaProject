package com.example.odyssiaproject.singelton;

import android.util.Log;

import com.example.odyssiaproject.entidad.Pais;

import java.util.ArrayList;
import java.util.List;

public class ListaPaisesSingelton {
    private static ListaPaisesSingelton instance;
    private List<Pais> listPaises;
    private int contador = 1;

    private ListaPaisesSingelton(){
        super();
    }
    public static ListaPaisesSingelton getInstance() {
        if(instance == null){
            instance = new ListaPaisesSingelton();
        }
        return instance;
    }

    public Pais getPaisById(int id) {
        for (Pais p : listPaises) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void inicializar(){
        listPaises = new ArrayList<>();
        Pais pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("España");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Italia");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Francia");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Suiza");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Grecia");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Portugal");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Belgica");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Noruega");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Inglatera");

        listPaises.add(pais);

        pais = new Pais();
        pais.setId(contador++);
        pais.setNombre("Holanda");

        listPaises.add(pais);

        Log.i("ListaPaisesSingleton", "########" + pais);
    }

    public List<Pais> getListaPaises() {
        return listPaises;
    }
}

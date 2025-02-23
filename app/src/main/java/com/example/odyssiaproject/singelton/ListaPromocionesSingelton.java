package com.example.odyssiaproject.singelton;

import android.util.Log;
import com.example.odyssiaproject.entidad.Promociones;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Singleton para gestionar la lista de promociones en la aplicación.
 * Se asegura de que solo haya una única instancia de la lista en toda la aplicación.
 */
public class ListaPromocionesSingelton {

    private static ListaPromocionesSingelton instance;

    private List<Promociones> listaPromociones;

    private int contador = 1;

    /**
     * Constructor privado para evitar la creación de múltiples instancias.
     */
    private ListaPromocionesSingelton() {
        super();
    }

    /**
     * Obtiene la única instancia del Singleton. Si no existe, la crea.
     *
     * @return La instancia única de ListaPromocionesSingelton.
     */
    public static ListaPromocionesSingelton getInstance() {
        if (instance == null) {
            instance = new ListaPromocionesSingelton();
        }
        return instance;
    }

    /**
     * Busca y devuelve una promoción por su ID.
     *
     * @param id El identificador de la promoción a buscar.
     * @return La promoción correspondiente al ID, o null si no se encuentra.
     */
    public Promociones getPromocionById(int id) {
        if(listaPromociones == null){
            Log.e("ListaPromociones", "Lista de promociones nula");
            return null;
        }
        for (Promociones p : listaPromociones) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Inicializa la lista de promociones con valores predefinidos.
     */
    public void inicializar() {
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
        promociones.setId(contador++);
        promociones.setNombre("Rebajas");
        listaPromociones.add(promociones);

        promociones = new Promociones();
        promociones.setId(contador++);
        promociones.setNombre("Pack");
        listaPromociones.add(promociones);

        // Imprime la última promoción agregada en los logs.
        Log.i("ListaPromocionesSingleton", "tamaño lista" + listaPromociones.size());
    }

    public List<Promociones> getListaPromociones() {
        return listaPromociones;
    }
}

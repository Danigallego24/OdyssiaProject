package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;

public class GestorPaises {

    private final ListaPaisesSingelton listaPaises;

    public GestorPaises() {
        // Inicializamos una sola vez el singleton
        this.listaPaises = ListaPaisesSingelton.getInstance();
    }

    /**
     * Retorna un código o nombre de país que se utilizará para seleccionar la imagen.
     * Si el país no se encuentra o no coincide, se retorna "0" (o podrías retornar un valor por defecto).
     *
     * @param p Objeto Pais con el nombre a buscar.
     * @return El nombre del país si coincide o "0" si no se encuentra.
     */
    public String imagenPaises(Pais p) {
        if (p == null || p.getNombre() == null) {
            return "0";
        }
        // Se busca el país en el singleton (podría hacerse para validar que exista)
        Pais pais = listaPaises.getPaisByName(p.getNombre());
        if (pais == null) {
            return "0";
        }
        String nombrePais = pais.getNombre().toLowerCase();

        // Si solo necesitas devolver el mismo nombre, este bloque es redundante.
        // En su lugar, podrías devolver el nombre directamente, o mapearlo a un código.
       return pais.getImagen();
    }
}

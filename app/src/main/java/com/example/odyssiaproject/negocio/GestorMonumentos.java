package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Monumentos;
import com.example.odyssiaproject.singelton.ListaMonumentosSingleton;

public class GestorMonumentos {

    private final ListaMonumentosSingleton listaMonumentos;

    public GestorMonumentos() {
        // Inicializamos una sola vez el singleton
        this.listaMonumentos = ListaMonumentosSingleton.getInstance();
    }

    public String imagenMonumento(Monumentos m) {

        if (m == null || m.getNombre() == null) {
            return "0";
        }
        // Se busca el país en el singleton (podría hacerse para validar que exista)
        Monumentos monumentos = listaMonumentos.getMonumentoByName(m.getNombre());
        if (monumentos == null) {
            return "0";
        }
        String nombreMonumento = monumentos.getNombre().toLowerCase();

        // Si solo necesitas devolver el mismo nombre, este bloque es redundante.
        // En su lugar, podrías devolver el nombre directamente, o mapearlo a un código.
        return monumentos.getNombre();

    }
}

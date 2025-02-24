package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Pais;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class GestorPaises {

    private final FirebaseFirestore db;

    public GestorPaises() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Obtiene la URL de la imagen de un país desde Firestore.
     *
     * @param pais Objeto Pais con al menos el nombre.
     * @return URL de la imagen o valor por defecto si no se encuentra.
     */
    public String imagenPaises(Pais pais) {
        if (pais == null || pais.getImagen() == null || pais.getImagen().isEmpty()) {
            return "https://ejemplo.com/default.jpg"; // URL por defecto
        }
        return pais.getImagen();
    }

    /**
     * Carga la lista de países desde Firestore.
     *
     * @param listener Callback para manejar el resultado.
     */
    public void cargarPaises(OnPaisesCargadosListener listener) {
        db.collection("paises")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Pais> paises = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            Pais pais = doc.toObject(Pais.class);
                            pais.setId(doc.getId()); // Guardar ID de Firestore
                            paises.add(pais);
                        }
                        listener.onExito(paises);
                    } else {
                        listener.onError(task.getException());
                    }
                });
    }

    // Interfaz para manejar la respuesta asíncrona
    public interface OnPaisesCargadosListener {
        void onExito(List<Pais> paises);
        void onError(Exception e);
    }
}
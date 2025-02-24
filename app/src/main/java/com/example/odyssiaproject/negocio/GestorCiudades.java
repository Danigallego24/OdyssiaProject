package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Ciudad;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase para gestionar la lógica relacionada con ciudades (obtención de imágenes, validaciones, etc.).
 */
public class GestorCiudades {

    // Mapa estático para asociar ciudades con sus URLs de imagen (puede reemplazarse con Firestore)
    private static final Map<String, String> ciudadImagenMap = new HashMap<>();

    static {
        // Ejemplo de URLs hardcodeadas (usar solo para pruebas)
        ciudadImagenMap.put("Madrid", "https://ejemplo.com/madrid.jpg");
        ciudadImagenMap.put("Barcelona", "https://ejemplo.com/barcelona.jpg");
        ciudadImagenMap.put("Roma", "https://ejemplo.com/roma.jpg");
        // Añadir más ciudades según sea necesario
    }

    /**
     * Obtiene la URL de la imagen de una ciudad desde Firestore o un mapa estático.
     *
     * @param ciudad Objeto Ciudad del cual se requiere la imagen.
     * @return URL de la imagen o valor por defecto si no se encuentra.
     */
    public String imagenCiudad(Ciudad ciudad) {
        // Opción 1: Obtener URL desde Firestore (recomendado)
        if (ciudad.getImagen() != null && !ciudad.getImagen().isEmpty()) {
            return ciudad.getImagen();
        }

        // Opción 2: Usar mapa estático (solo para pruebas)
        String url = ciudadImagenMap.get(ciudad.getImagen());
        return url != null ? url : "https://ejemplo.com/default.jpg";
    }
}
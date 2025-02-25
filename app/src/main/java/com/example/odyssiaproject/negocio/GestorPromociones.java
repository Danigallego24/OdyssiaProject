package com.example.odyssiaproject.negocio;

import android.util.Log;

import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;

/**
 * GestorPromociones se encarga de la lógica relacionada con las promociones.
 * <p>
 * Este gestor utiliza el Singleton {@link ListaPromocionesSingelton} para obtener una promoción
 * a partir de su identificador y, en base a ese dato, determinar qué valor entero se debe retornar,
 * lo que puede interpretarse como un código para asignar una imagen.
 */
public class GestorPromociones {
    // Instancia del Singleton que almacena la lista de promociones.
    private ListaPromocionesSingelton listaPromociones;

    /**
     * Determina un código entero que representa la imagen asociada a una promoción.
     *
     * @param p Objeto {@link Promociones} del cual se quiere obtener la imagen.
     * @return Un entero que indica:
     *         <ul>
     *           <li>-1 si el objeto p es nulo o no se encuentra la promoción.</li>
     *           <li>1 si el id de la promoción es 1.</li>
     *           <li>2 si el id de la promoción es 2.</li>
     *           <li>3 en cualquier otro caso.</li>
     *         </ul>
     */
    public int imagenPromocion(Promociones p) {
        // Verifica si el objeto p es nulo para evitar errores.
        if (p == null) {
            Log.e("GestorPromociones", "p es null");
            return -1; // Valor de error
        }
        // Obtiene la instancia del Singleton de promociones.
        listaPromociones = ListaPromocionesSingelton.getInstance();
        // Busca la promoción en la lista utilizando el id del objeto p.
        Promociones promocion = listaPromociones.getPromocionById(p.getId());

        // Verifica si se encontró la promoción, en caso contrario registra el error y retorna -1.
        if (promocion == null) {
            Log.e("GestorPromociones", "Promocion es null: " + p.getId());
            return -1;
        }

        // Retorna un código entero según el id de la promoción:
        if (promocion.getId() == 1) {
            return 1;
        } else if (promocion.getId() == 2) {
            return 2;
        }
        // Si el id no es 1 ni 2, se retorna 3.
        return 3;
    }
}

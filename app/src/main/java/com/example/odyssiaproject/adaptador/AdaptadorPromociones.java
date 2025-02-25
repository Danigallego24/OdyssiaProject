package com.example.odyssiaproject.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.negocio.GestorPromociones;

import java.util.List;

/**
 * AdaptadorPromociones es un adaptador para gestionar la lista de promociones en un RecyclerView.
 * <p>
 * Se encarga de inflar el layout correspondiente a cada ítem, asignar los datos de cada promoción
 * y gestionar la visualización de la imagen de la promoción según la lógica definida en GestorPromociones.
 */
public class AdaptadorPromociones extends RecyclerView.Adapter<AdaptadorPromociones.ViewHolder> {
    // Objeto Promociones utilizado para almacenar la promoción actual en onBindViewHolder.
    private Promociones p;
    // Lista de promociones que se mostrarán en el RecyclerView.
    private List<Promociones> listaPromociones;

    /**
     * Constructor del adaptador.
     *
     * @param listaPromociones Lista de promociones a mostrar.
     */
    public AdaptadorPromociones(List<Promociones> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    /**
     * ViewHolder que representa cada ítem de la lista.
     * Contiene la referencia al ImageButton que muestra la imagen de la promoción.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imagenPromocion;

        /**
         * Constructor del ViewHolder.
         *
         * @param v Vista que representa el ítem.
         */
        public ViewHolder(View v) {
            super(v);
            // Se obtiene la referencia al ImageButton definido en el layout item_promotions.
            imagenPromocion = v.findViewById(R.id.imagePromotion);
        }
    }

    /**
     * Infla el diseño XML de cada elemento de la lista.
     *
     * @param parent   El ViewGroup en el que se va a inflar la vista.
     * @param viewType Tipo de vista (en este caso, se utiliza un único tipo de vista).
     * @return Un ViewHolder que contiene la vista del elemento.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout item_promotions para cada ítem del RecyclerView.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotions, parent, false);
        AdaptadorPromociones.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    /**
     * Asigna datos a cada elemento de la lista en función de su posición.
     *
     * @param holder   ViewHolder del ítem.
     * @param position Posición del ítem en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull AdaptadorPromociones.ViewHolder holder, int position) {
        // Se obtiene la promoción correspondiente a la posición actual.
        p = listaPromociones.get(position);

        if (p == null) {
            // Manejo de error: Si la promoción es nula, se muestra una imagen por defecto o un placeholder.
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion);
            return; // Se detiene la ejecución para evitar NullPointerException.
        }

        // Se crea una instancia de GestorPromociones para obtener la imagen correspondiente a la promoción.
        GestorPromociones nP = new GestorPromociones();
        int resultadoImagen = nP.imagenPromocion(p); // Llama al método UNA sola vez.

        // Según el resultado obtenido, se asigna la imagen adecuada al ImageButton.
        if (resultadoImagen == 1) {
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion2);
        } else if (resultadoImagen == 2) {
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion);
        } else {
            // Opcional: Imagen por defecto si no coincide con ningún caso.
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion);
        }
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Tamaño de la lista de promociones.
     */
    @Override
    public int getItemCount() {
        return listaPromociones.size();
    }
}

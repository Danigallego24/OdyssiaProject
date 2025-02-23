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
 * Adaptador para gestionar la lista de promociones en un RecyclerView.
 * Muestra las promociones con su respectiva imagen.
 */
public class AdaptadorPromociones extends RecyclerView.Adapter<AdaptadorPromociones.ViewHolder> {
    private Promociones p;
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
            imagenPromocion = v.findViewById(R.id.imagePromotion);
        }
    }

    /**
     * Infla el diseño XML de cada elemento de la lista.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        p = listaPromociones.get(position);

        if (p == null) {
            // Manejo de error: muestra una imagen por defecto o un placeholder
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion);
            return; // Detiene la ejecución para evitar el NullPointerException
        }

        GestorPromociones nP = new GestorPromociones();
        int resultadoImagen = nP.imagenPromocion(p); // Llama al método UNA sola vez

        if (resultadoImagen == 1) {
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion2);
        } else if (resultadoImagen == 2) {
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion);
        } else {
            // Opcional: Imagen por defecto si no coincide con ningún caso
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion);
        }
    }
        /**
         * Devuelve el número total de elementos en la lista.
         *
         * @return Tamaño de la lista de promociones.
         */
        @Override
        public int getItemCount(){
            return listaPromociones.size();
        }
    }


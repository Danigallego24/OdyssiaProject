package com.example.odyssiaproject.adaptador;

import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.odyssiaproject.ExplorationActivity;
import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.negocio.GestorCiudades;
import java.util.List;
/**
 * Adaptador para gestionar la lista de ciudades en un RecyclerView.
 * Muestra cada ciudad con su imagen, nombre, descripción y botón de "Me gusta".
 */
public class AdaptadorCiudades extends RecyclerView.Adapter<AdaptadorCiudades.ViewHolder>{
    private List<Ciudad> listaCiudades;
    private GestorCiudades gestorCiudades;
    private TextView tvNameCity, descriptionCity;

    /**
     * Constructor del adaptador.
     *
     * @param listaCiudades Lista de ciudades a mostrar.
     */
    public AdaptadorCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
        this.gestorCiudades = new GestorCiudades();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cities, parent, false);
        return new AdaptadorCiudades.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Ciudad ciudadActual = listaCiudades.get(position);
        String imagenCiudadesUrl = new GestorCiudades().imagenCiudad(ciudadActual);
        if (imagenCiudadesUrl == null || imagenCiudadesUrl.isEmpty()) {
            Log.w("Glide", "URL de la imagen es nula o vacía para el país: " + ciudadActual.getNombre());
            imagenCiudadesUrl = "url_default_image";  // Asigna una URL predeterminada o usa una imagen local
        }

        Log.d("Glide", "Cargando imagen desde: " + imagenCiudadesUrl);

        // Asigna el recurso de imagen según el código obtenido
        Log.d("MUONES DE MIERDA", "Cargando imagen desde: " + ciudadActual.getNombre());
        Glide.with(holder.itemView.getContext())
                .load(imagenCiudadesUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                // Imagen de error
                .into(holder.imagenCiudad);

        // Asigna los valores a los TextView
        holder.nombreCiudad.setText(ciudadActual.getNombre());
        holder.descripcionCiudad.setText(ciudadActual.getDescripcion());
        
        // Configura el click sobre el botón de imagen para abrir la CityActivity
        holder.abrir.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Ciudad ciudadClick = listaCiudades.get(pos);
                Log.i("DEBUG", "Ciudad enviado a ExplorationActivity: " + ciudadClick.getNombre());
                // Inicia la Activity de ciudades y pasa el nombre del país (o también su ID si lo necesitas)
                Intent intent = new Intent(v.getContext(), ExplorationActivity.class);
                intent.putExtra("ciudad", ciudadClick.getNombre());
                v.getContext().startActivity(intent);
            }
        });

        holder.like.setOnTouchListener(new View.OnTouchListener() {

            private final GestureDetector gestureDetector = new GestureDetector(holder.itemView.getContext(),
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onDoubleTap(MotionEvent e) {
                            holder.like.setImageResource(R.drawable.buttonlikered);
                            return true;
                        }
                    });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCiudades.size();
    }

    /**
     * ViewHolder que representa cada ítem de la lista.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagenCiudad;
        private TextView nombreCiudad;
        private ImageButton like;
        private TextView descripcionCiudad;

            private Button abrir;

            public ViewHolder(View v) {
                super(v);
                imagenCiudad = v.findViewById(R.id.imageView);
                nombreCiudad = v.findViewById(R.id.tvNameCity);
                like = v.findViewById(R.id.buttonLikeCity);
                descripcionCiudad = v.findViewById(R.id.descriptionCity);
                abrir = v.findViewById(R.id.buttonOpen);
            }
        }
    }


package com.example.odyssiaproject.adaptador;

import android.content.Context;
import android.content.ContextWrapper;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.negocio.GestorCiudades;
import com.example.odyssiaproject.ui.city.CityFragment;
import com.example.odyssiaproject.ui.option.OptionFragment;

import java.util.List;

public class AdaptadorCiudades extends RecyclerView.Adapter<AdaptadorCiudades.ViewHolder> {
    private List<Ciudad> listaCiudades;
    private GestorCiudades gestorCiudades;

    public AdaptadorCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
        this.gestorCiudades = new GestorCiudades();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cities, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ciudad ciudadActual = listaCiudades.get(position);
        String imagenCiudadesUrl = gestorCiudades.imagenCiudad(ciudadActual);
        if (imagenCiudadesUrl == null || imagenCiudadesUrl.isEmpty()) {
            Log.w("Glide", "URL de la imagen es nula o vacía para: " + ciudadActual.getNombre());
            imagenCiudadesUrl = "url_default_image";
        }

        Glide.with(holder.itemView.getContext())
                .load(imagenCiudadesUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.imagenCiudad);

        holder.nombreCiudad.setText(ciudadActual.getNombre());
        holder.descripcionCiudad.setText(ciudadActual.getDescripcion());

        // Al pulsar el botón "abrir" se crea el fragmento CityFragment con el nombre de la ciudad
        holder.abrir.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Ciudad ciudadClick = listaCiudades.get(pos);
                // Crear el CityFragment con el nombre de la ciudad
                CityFragment cityFragment = CityFragment.newInstance(ciudadClick.getNombre());

                // Obtener el Activity desde el contexto de forma segura
                Context context = v.getContext();
                while (!(context instanceof AppCompatActivity) && context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                AppCompatActivity activity = (AppCompatActivity) context;

                // Realizar la transacción de fragmentos
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new OptionFragment(ciudadClick.getNombre()))
                        .addToBackStack(null)
                        .commit();
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

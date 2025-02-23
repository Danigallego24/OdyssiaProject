package com.example.odyssiaproject.adaptador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.odyssiaproject.CityActivity;
import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.negocio.GestorPaises;
import com.example.odyssiaproject.ui.city.CityFragment;

import java.util.List;

public class AdaptadorPaises extends RecyclerView.Adapter<AdaptadorPaises.ViewHolder> {

    private List<Pais> listaPais;
    private GestorPaises gestorPaises;

    public AdaptadorPaises(List<Pais> listaPaises) {
        this.listaPais = listaPaises;
        this.gestorPaises = new GestorPaises();
    }

    // Clase ViewHolder que representa cada item (país) en el RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imagenPais;

        public ViewHolder(View v) {
            super(v);
            imagenPais = v.findViewById(R.id.imageCountries);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout para cada item (item_countries.xml)
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Pais paisActual = listaPais.get(position);
        String imagenPaisesUrl = new GestorPaises().imagenPaises(paisActual);
        if (imagenPaisesUrl == null || imagenPaisesUrl.isEmpty()) {
            Log.w("Glide", "URL de la imagen es nula o vacía para el país: " + paisActual.getNombre());
            imagenPaisesUrl = "url_default_image";  // Asigna una URL predeterminada o usa una imagen local
        }

        Log.d("Glide", "Cargando imagen desde: " + imagenPaisesUrl);

        // Asigna el recurso de imagen según el código obtenido
        Log.d("MUONES DE MIERDA", "Cargando imagen desde: " + paisActual.getNombre());
        Glide.with(holder.itemView.getContext())
                .load(paisActual.getNombre())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                // Imagen de error
                .into(holder.imagenPais);


        // Configura el click sobre el botón de imagen para abrir la CityActivity
        holder.imagenPais.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Pais paisClick = listaPais.get(pos);
                Log.i("DEBUG", "País enviado a CityActivity: " + paisClick.getImagen());
                // Crear el nuevo fragmento y pasarle el nombre del país como argumento
                CityFragment cityFragment = new CityFragment();
                Bundle args = new Bundle();
                args.putString("pais", paisClick.getImagen());
                cityFragment.setArguments(args);

                // Obtener el contexto y asegurarse de que es una AppCompatActivity
                Context context = v.getContext();
                if (context instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) context;
                    if (!activity.isFinishing() && !activity.getSupportFragmentManager().isStateSaved()) {
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, cityFragment) // Asegúrate de que el ID es correcto
                                .addToBackStack(null) // Permite volver atrás
                                .commit();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPais.size();
    }
}

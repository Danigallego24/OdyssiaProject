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

    // Lista de países a mostrar
    private List<Pais> listaPais;
    // Gestor para operaciones relacionadas con países
    private GestorPaises gestorPaises;

    // Constructor que recibe la lista de países
    public AdaptadorPaises(List<Pais> listaPaises) {
        this.listaPais = listaPaises;
        this.gestorPaises = new GestorPaises();
    }

    // ViewHolder: Contenedor de vistas para cada elemento de la lista
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imagenPais;  // Botón con imagen del país

        public ViewHolder(View v) {
            super(v);
            // Vincula el ImageButton del layout XML
            imagenPais = v.findViewById(R.id.imageCountries);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crea una nueva vista inflando el layout de item_countries
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtiene el país actual según la posición
        Pais paisActual = listaPais.get(position);

        // Intenta obtener la URL de la imagen del país
        String imagenPaisesUrl = gestorPaises.imagenPaises(paisActual);

        // Validación de URL (si es nula usa una por defecto)
        if (imagenPaisesUrl == null || imagenPaisesUrl.isEmpty()) {
            Log.w("Glide", "URL de la imagen es nula o vacía para el país: " + paisActual.getNombre());
            imagenPaisesUrl = "url_default_image";  // Considerar usar R.drawable.imagen_default
        }

        // Carga la imagen usando Glide
        Glide.with(holder.itemView.getContext())
                .load(paisActual.getNombre())  // ⚠️ ¿Debería ser imagenPaisesUrl?
                .diskCacheStrategy(DiskCacheStrategy.ALL)  // Almacenamiento en disco
                .skipMemoryCache(true)  // No usar cache en RAM
                .into(holder.imagenPais);

        // Manejador de clic en la imagen del país
        holder.imagenPais.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Pais paisClick = listaPais.get(pos);

                // 1. Crear fragmento de detalle
                CityFragment cityFragment = new CityFragment();

                // 2. Preparar argumentos (envía nombre del país)
                Bundle args = new Bundle();
                args.putString("pais", paisClick.getImagen());
                cityFragment.setArguments(args);

                // 3. Obtener contexto y navegar
                Context context = v.getContext();
                if (context instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) context;

                    // Validación para evitar crashes en estados inválidos
                    if (!activity.isFinishing() && !activity.getSupportFragmentManager().isStateSaved()) {
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, cityFragment)  // Reemplaza el fragmento actual
                                .addToBackStack(null)  // Permite retroceder con botón Back
                                .commit();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        // Retorna el total de elementos en la lista
        return listaPais.size();
    }
}
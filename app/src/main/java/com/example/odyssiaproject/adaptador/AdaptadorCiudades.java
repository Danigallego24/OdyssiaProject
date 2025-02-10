package com.example.odyssiaproject.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Ciudad;

import java.util.List;

public class AdaptadorCiudades extends RecyclerView.Adapter<AdaptadorCiudades.ViewHolder>{
    private Ciudad c;
    private List<Ciudad> listaCiudades;

    public AdaptadorCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageCiudad;
        private TextView nombreCiudad;
        private ImageButton like;
        private TextView descripcionCiudad;
        private Button abrir;

        public ViewHolder(View v) {
            super(v);
            imageCiudad = v.findViewById(R.id.imageCountries);
            nombreCiudad = v.findViewById(R.id.tvNameCity);
            like = v.findViewById(R.id.buttonLikeCity);
            descripcionCiudad = v.findViewById(R.id.descriptionCity);
            abrir = v.findViewById(R.id.buttonOpen);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cities, parent, false);
        AdaptadorCiudades.ViewHolder viewHolder = new AdaptadorCiudades.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listaCiudades.size();
    }
}

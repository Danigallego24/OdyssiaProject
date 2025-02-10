package com.example.odyssiaproject.adaptador;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Pais;

import java.util.List;

public class AdaptadorCiudades {
    private Ciudad c;
    private List<Ciudad> listaCiudades;

    public AdaptadorCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagenPais;

        public ViewHolder(View v) {
            super(v);
            imagenPais = v.findViewById(R.id.imageCountries);
        }
    }
}

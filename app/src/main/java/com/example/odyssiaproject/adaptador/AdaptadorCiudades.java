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
import com.example.odyssiaproject.negocio.GestorCiudades;

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
        GestorCiudades gC = new GestorCiudades();
        Ciudad ciudad = listaCiudades.get(position);
        if (gC.imagenCiudad(ciudad).equals("Madrid")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Barcelona")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Sevilla")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Roma")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Florencia")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Venecia")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Paris")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Lyon")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Marsella")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Zurich")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Ginebra")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Berna")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Santorini")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Atenas")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Salonica")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Porto")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Lisboa")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Braga")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Bruselas")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Brujas")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Amberes")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Oslo")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Bergen")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Tromso")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Londres")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Manchester")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Liverpool")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Amsterdan")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Rotterdam")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (gC.imagenCiudad(ciudad).equals("Utrecht")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        }
        holder.nombreCiudad.setText(ciudad.getNombre());
        holder.like.setImageResource(R.drawable.buttonlike);
        holder.descripcionCiudad.setText(ciudad.getDescripcion());
        if (position % 2 == 0) {
            holder.itemView.getLayoutParams().height = 400;
        } else {
            holder.itemView.getLayoutParams().height = 200;
        }
    }

    @Override
    public int getItemCount() {
        return listaCiudades.size();
    }
}

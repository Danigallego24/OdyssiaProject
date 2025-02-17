package com.example.odyssiaproject.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.CityActivity;
import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.negocio.GestorPaises;

import java.util.List;

public class AdaptadorPaises extends RecyclerView.Adapter<AdaptadorPaises.ViewHolder>{
    private Pais p;
    private List<Pais> listaPais;
    public AdaptadorPaises(List<Pais> listaPaises) {
        this.listaPais = listaPaises;
    }
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries, parent, false);
        AdaptadorPaises.ViewHolder viewHolder = new AdaptadorPaises.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GestorPaises nP = new GestorPaises();
        p = listaPais.get(position);
        if (nP.imagenPaises(p).equals("España")){
            holder.imagenPais.setImageResource(R.drawable.listaspain);
        } else if (nP.imagenPaises(p).equals("Italia")) {
            holder.imagenPais.setImageResource(R.drawable.listaitaly);
        }else if (nP.imagenPaises(p).equals("Francia")) {
            holder.imagenPais.setImageResource(R.drawable.listafrance);
        }else if (nP.imagenPaises(p).equals("Suiza")) {
            holder.imagenPais.setImageResource(R.drawable.listaswitzerland);
        }else if (nP.imagenPaises(p).equals("Grecia")) {
            holder.imagenPais.setImageResource(R.drawable.listagreece);
        }else if (nP.imagenPaises(p).equals("Portugal")) {
            holder.imagenPais.setImageResource(R.drawable.listaportugal);
        }else if (nP.imagenPaises(p).equals("Belgica")) {
            holder.imagenPais.setImageResource(R.drawable.listabelgium);
        }else if (nP.imagenPaises(p).equals("Noruega")) {
            holder.imagenPais.setImageResource(R.drawable.listanorway);
        }else if (nP.imagenPaises(p).equals("Inglaterra")) {
            holder.imagenPais.setImageResource(R.drawable.listaengland);
        }else if (nP.imagenPaises(p).equals("Holanda")){
            holder.imagenPais.setImageResource(R.drawable.listanetherlands);
        }

        holder.imagenPais.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CityActivity.class);
            intent.putExtra("pais", p.getNombre());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPais.size();
    }



}

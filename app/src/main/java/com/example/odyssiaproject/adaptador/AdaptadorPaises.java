package com.example.odyssiaproject.adaptador;

import android.content.Intent;
import android.util.Log;
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
        Log.i("AdaptadorPaises", "Posición: " + position + " - País: " + paisActual.getNombre());

        // Obtiene el código/nombre de imagen que se usará para elegir el recurso adecuado
        String codigoPais = gestorPaises.imagenPaises(paisActual);

        // Asigna el recurso de imagen según el código obtenido
        if (codigoPais.equals("Espania")) {
            holder.imagenPais.setImageResource(R.drawable.listaspain);
        } else if (codigoPais.equals("Italia")) {
            holder.imagenPais.setImageResource(R.drawable.listaitaly);
        } else if (codigoPais.equals("Francia")) {
            holder.imagenPais.setImageResource(R.drawable.listafrance);
        } else if (codigoPais.equals("Suiza")) {
            holder.imagenPais.setImageResource(R.drawable.listaswitzerland);
        } else if (codigoPais.equals("Grecia")) {
            holder.imagenPais.setImageResource(R.drawable.listagreece);
        } else if (codigoPais.equals("Portugal")) {
            holder.imagenPais.setImageResource(R.drawable.listaportugal);
        } else if (codigoPais.equals("Belgica")) {
            holder.imagenPais.setImageResource(R.drawable.listabelgium);
        } else if (codigoPais.equals("Noruega")) {
            holder.imagenPais.setImageResource(R.drawable.listanorway);
        } else if (codigoPais.equals("Inglaterra")) {
            holder.imagenPais.setImageResource(R.drawable.listaengland);
        } else if (codigoPais.equals("Holanda")){
            holder.imagenPais.setImageResource(R.drawable.listanetherlands);
        }

        // Configura el click sobre el botón de imagen para abrir la CityActivity
        holder.imagenPais.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Pais paisClick = listaPais.get(pos);
                Log.i("DEBUG", "País enviado a CityActivity: " + paisClick.getNombre());
                // Inicia la Activity de ciudades y pasa el nombre del país (o también su ID si lo necesitas)
                Intent intent = new Intent(v.getContext(), CityActivity.class);
                intent.putExtra("pais", paisClick.getNombre());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPais.size();
    }
}

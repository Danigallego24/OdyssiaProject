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

/**
 * Adaptador para gestionar la lista de países en un RecyclerView.
 * Permite mostrar los países con su respectiva imagen y manejar eventos de clic.
 */
public class AdaptadorPaises extends RecyclerView.Adapter<AdaptadorPaises.ViewHolder>{
    private Pais p;
    private List<Pais> listaPais;
    /**
     * Constructor del adaptador.
     *
     * @param listaPaises Lista de países a mostrar.
     */
    public AdaptadorPaises(List<Pais> listaPaises) {
        this.listaPais = listaPaises;
    }
    /**
     * ViewHolder que representa cada ítem de la lista.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imagenPais;
        /**
         * Constructor del ViewHolder.
         *
         * @param v Vista que representa el ítem.
         */
        public ViewHolder(View v) {
            super(v);
            imagenPais = v.findViewById(R.id.imageCountries);
        }
    }
    /**
     * Infla el diseño XML de cada elemento de la lista.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries, parent, false);
        AdaptadorPaises.ViewHolder viewHolder = new AdaptadorPaises.ViewHolder(v);
        return viewHolder;
    }
    /**
     * Asigna datos a cada elemento de la lista en función de su posición.
     *
     * @param holder   ViewHolder del ítem.
     * @param position Posición del ítem en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GestorPaises nP = new GestorPaises();
        p = listaPais.get(position);
        Log.i("AdaptadorPaises", "Posición: " + position + " - País: " + p.getNombre());
        String codigoPais = nP.imagenPaises(p);
        if (codigoPais.equals("España")) {
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

        holder.imagenPais.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Pais paisClick = listaPais.get(pos);
                Log.i("DEBUG", "País enviado a CityActivity: " + paisClick.getNombre());
                Intent intent = new Intent(v.getContext(), CityActivity.class);
                intent.putExtra("pais", paisClick.getNombre());
                v.getContext().startActivity(intent);
            }
        });
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Tamaño de la lista de países.
     */
    @Override
    public int getItemCount() {
        return listaPais.size();
    }



}

package com.example.odyssiaproject.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.negocio.GestorPromociones;

import java.util.List;

public class AdaptadorPromociones extends RecyclerView.Adapter<AdaptadorPromociones.ViewHolder> {
    private Promociones p;
    private List<Promociones> listaPromociones;
    public AdaptadorPromociones(List<Promociones> listaVideoJuegos) {
        this.listaPromociones = listaVideoJuegos;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imagenPromocion;

        public ViewHolder(View v) {
            super(v);
            imagenPromocion = v.findViewById(R.id.imagePromotion);
        }
    }
    @NonNull
    @Override
    public AdaptadorPromociones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotions, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPromociones.ViewHolder holder, int position) {
        GestorPromociones nP = new GestorPromociones();
        p = listaPromociones.get(position);
        if (nP.imagenPromocion(p) == 1){
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion);
        } else if (nP.imagenPromocion(p) == 2) {
            holder.imagenPromocion.setImageResource(R.drawable.imgpromotion2);
        }
    }

    @Override
    public int getItemCount() {
        return listaPromociones.size();
    }
}

package com.example.odyssiaproject.adaptador;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.entidad.Promociones;

import java.util.List;

public class AdaptadorPromociones extends RecyclerView.Adapter<AdaptadorPromociones.ViewHolder> {
    private List<Promociones> listaPromociones;
    public AdaptadorPromociones(List<Promociones> listaVideoJuegos) {
        this.listaPromociones = listaVideoJuegos;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imagenPromocion;

        public ViewHolder(View v) {
            super(v);
            imagenPromocion = v.findViewById(R.id.imagenPromocion);
        }
    }
    @NonNull
    @Override
    public AdaptadorPromociones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPromociones.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

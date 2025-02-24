package com.example.odyssiaproject.adaptador;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.entidad.Monumentos;

import java.util.List;

public class AdaptadorMonumentos extends RecyclerView.Adapter<AdaptadorMonumentos.ViewHolder> {

    private List<Monumentos> listaMonumentos;

    public AdaptadorMonumentos(List<Monumentos> listaMonumentos) {
        this.listaMonumentos = listaMonumentos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_options, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Monumentos monumento = listaMonumentos.get(position);

        if (monumento == null) {
            holder.imagenMonumento.setImageResource(R.drawable.imgpromotion);
            holder.tvNamePlace.setText("Nombre no disponible");
            holder.tvPricePlace.setText("Precio no disponible");
            holder.tvTimePlace.setText("Horario no disponible");
            return;
        }

        holder.tvNamePlace.setText(monumento.getNombre());
        holder.tvPricePlace.setText("Precio: " + monumento.getPrecio());
        holder.tvTimePlace.setText("Horario: " + monumento.getHorario());

        holder.like.setOnTouchListener(new View.OnTouchListener() {
            private final GestureDetector gestureDetector = new GestureDetector(holder.itemView.getContext(),
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onDoubleTap(MotionEvent e) {
                            holder.like.setImageResource(R.drawable.buttonlikered);
                            return true;
                        }
                    });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaMonumentos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenMonumento;
        TextView tvNamePlace, tvPricePlace, tvTimePlace;
        ImageButton like;
        Button abrir;

        public ViewHolder(View v) {
            super(v);
            imagenMonumento = v.findViewById(R.id.iwPlace);
            tvNamePlace = v.findViewById(R.id.tvNamePlace);
            tvPricePlace = v.findViewById(R.id.tvPricePlace);
            tvTimePlace = v.findViewById(R.id.tvTimePlace);
            like = v.findViewById(R.id.buttonLikePlace);
            abrir = v.findViewById(R.id.buttonOpen);
        }
    }
}

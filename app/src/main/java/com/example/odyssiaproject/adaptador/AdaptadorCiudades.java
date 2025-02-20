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
            imageCiudad = v.findViewById(R.id.imageView);
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
        Ciudad ciudad = listaCiudades.get(position);

        GestorCiudades gC = new GestorCiudades();
        String cityCode = gC.imagenCiudad(ciudad);

        if (cityCode.equals("Madrid")) {
            holder.imageCiudad.setImageResource(R.drawable.citymadrid);
        } else if (cityCode.equals("Barcelona")) {
            holder.imageCiudad.setImageResource(R.drawable.citybarcelona);
        } else if (cityCode.equals("Sevilla")) {
            holder.imageCiudad.setImageResource(R.drawable.citysevilla);
        } else if (cityCode.equals("Roma")) {
            holder.imageCiudad.setImageResource(R.drawable.cityroma);
        } else if (cityCode.equals("Florencia")) {
            holder.imageCiudad.setImageResource(R.drawable.cityflorencia);
        } else if (cityCode.equals("Venecia")) {
            holder.imageCiudad.setImageResource(R.drawable.cityvenecia);
        } else if (cityCode.equals("Paris")) {
            holder.imageCiudad.setImageResource(R.drawable.cityparis);
        } else if (cityCode.equals("Lyon")) {
            holder.imageCiudad.setImageResource(R.drawable.citylyon);
        } else if (cityCode.equals("Marsella")) {
            holder.imageCiudad.setImageResource(R.drawable.citymarsella);
        } else if (cityCode.equals("Zurich")) {
            holder.imageCiudad.setImageResource(R.drawable.cityzurich);
        } else if (cityCode.equals("Ginebra")) {
            holder.imageCiudad.setImageResource(R.drawable.cityginebra);
        } else if (cityCode.equals("Berna")) {
            holder.imageCiudad.setImageResource(R.drawable.cityberna);
        } else if (cityCode.equals("Santorini")) {
            holder.imageCiudad.setImageResource(R.drawable.citysantorini);
        } else if (cityCode.equals("Atenas")) {
            holder.imageCiudad.setImageResource(R.drawable.cityatenas);
        } else if (cityCode.equals("Salonica")) {
            holder.imageCiudad.setImageResource(R.drawable.citysalonica);
        } else if (cityCode.equals("Porto")) {
            holder.imageCiudad.setImageResource(R.drawable.cityporto);
        } else if (cityCode.equals("Lisboa")) {
            holder.imageCiudad.setImageResource(R.drawable.citylisboa);
        } else if (cityCode.equals("Braga")) {
            holder.imageCiudad.setImageResource(R.drawable.citybraga);
        } else if (cityCode.equals("Bruselas")) {
            holder.imageCiudad.setImageResource(R.drawable.citybruselas);
        } else if (cityCode.equals("Brujas")) {
            holder.imageCiudad.setImageResource(R.drawable.citybrujas);
        } else if (cityCode.equals("Amberes")) {
            holder.imageCiudad.setImageResource(R.drawable.cityamberes);
        } else if (cityCode.equals("Oslo")) {
            holder.imageCiudad.setImageResource(R.drawable.cityoslo);
        } else if (cityCode.equals("Bergen")) {
            holder.imageCiudad.setImageResource(R.drawable.citybergen);
        } else if (cityCode.equals("Tromso")) {
            holder.imageCiudad.setImageResource(R.drawable.citytromso);
        } else if (cityCode.equals("Londres")) {
            holder.imageCiudad.setImageResource(R.drawable.citylondres);
        } else if (cityCode.equals("Manchester")) {
            holder.imageCiudad.setImageResource(R.drawable.citymanchester);
        } else if (cityCode.equals("Liverpool")) {
            holder.imageCiudad.setImageResource(R.drawable.cityliverpool);
        } else if (cityCode.equals("Amsterdam")) {
            holder.imageCiudad.setImageResource(R.drawable.cityamsterdam);
        } else if (cityCode.equals("Rotterdam")) {
            holder.imageCiudad.setImageResource(R.drawable.cityrotterdam);
        } else if (cityCode.equals("Utrecht")) {
            holder.imageCiudad.setImageResource(R.drawable.cityutrecht);
        }

        holder.nombreCiudad.setText(ciudad.getNombre());
        holder.descripcionCiudad.setText(ciudad.getDescripcion());
        holder.like.setImageResource(R.drawable.buttonlike);
        holder.like.setOnTouchListener(new View.OnTouchListener() {
            private final GestureDetector gestureDetector = new GestureDetector(holder.itemView.getContext(),
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onDoubleTap(MotionEvent e) {
                            holder.like.setImageResource(R.drawable.buttonlikered);   return true;
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
        return listaCiudades.size();
    }
}

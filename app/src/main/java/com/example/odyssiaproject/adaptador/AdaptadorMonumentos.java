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
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.negocio.GestorMonumentos;
import com.example.odyssiaproject.negocio.GestorPromociones;

import java.util.List;

public class AdaptadorMonumentos extends RecyclerView.Adapter<AdaptadorMonumentos.ViewHolder> {

    private List<Monumentos> listaMonumentos;
    private Monumentos m;
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
         m = listaMonumentos.get(position);
        if (m == null) {
            holder.imagenMonumento.setImageResource(R.drawable.imgpromotion);
            holder.tvNamePlace.setText("Nombre no disponible");
            holder.tvPricePlace.setText("Precio no disponible");
            holder.tvTimePlace.setText("Horario no disponible");
            return;
        }
        GestorMonumentos nM = new GestorMonumentos();
        String resultadoImagen = nM.imagenMonumento(m);
        if (resultadoImagen.equals("Plaza Mayor")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentplazamayor);
        } else if (resultadoImagen.equals("Puerta de Alcalá")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentpuertaalcala);
        } else if (resultadoImagen.equals("Parque de El Retiro")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentelretiro);
        } else if (resultadoImagen.equals("Basílica de la Sagrada Familia")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentsagradafamilia);
        } else if (resultadoImagen.equals("Parque Güell")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentparqueguell);
        } else if (resultadoImagen.equals("Casa Batlló")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentcasabatllo);
        } else if (resultadoImagen.equals("Catedral de Sevilla y La Giralda")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentcatedralgiralda);
        } else if (resultadoImagen.equals("Real Alcázar de Sevilla")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentrealalcazar);
        } else if (resultadoImagen.equals("Plaza de España")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentplazaespana);
        } else if (resultadoImagen.equals("Catedral de Nuestra Señora")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentcatedralnuestrasenora);
        } else if (resultadoImagen.equals("Museo Plantin-Moretus")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentmuseoplantin);
        } else if (resultadoImagen.equals("Castillo de Steen")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentcastillosteen);
        } else if (resultadoImagen.equals("Campanario de Brujas")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentcampanariobrujas);
        } else if (resultadoImagen.equals("Basílica de la Santa Sangre")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentbasilicasantasangre);
        } else if (resultadoImagen.equals("Hospital de San Juan y Museo Memling")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentmuseomemling);
        } else if (resultadoImagen.equals("Atomium")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentatomium);
        } else if (resultadoImagen.equals("Manneken Pis")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentmannekenpis);
        } else if (resultadoImagen.equals("Palacio Real de Bruselas")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentpalaciorealbruselas);
        } else if (resultadoImagen.equals("Basílica de Notre-Dame de Fourvière")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentbasilicanotredamefourviere);
        } else if (resultadoImagen.equals("Teatros Romanos de Fourvière")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentteatroromanofourviere);
        } else if (resultadoImagen.equals("Parque de la Tête d'Or")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentparquetetedor);
        } else if (resultadoImagen.equals("Basílica de Notre-Dame de la Garde")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentbasilicanotredamegarde);
        } else if (resultadoImagen.equals("MUCEM")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentmucem);
        } else if (resultadoImagen.equals("Fuerte de San Juan")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentfuertesanjuan);
        } else if (resultadoImagen.equals("Torre Eiffel")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumenttorreeiffel);
        } else if (resultadoImagen.equals("Catedral de Notre-Dame")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentcatedralnotredame);
        } else if (resultadoImagen.equals("Arco de Triunfo")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentarcotriunfo);
        } else if (resultadoImagen.equals("Acrópolis de Atenas")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentacropolisatenas);
        } else if (resultadoImagen.equals("Museo de la Acrópolis")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentmuseoacropolis);
        } else if (resultadoImagen.equals("Ágora Antigua")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentagoraantigua);
        } else if (resultadoImagen.equals("Torre Blanca")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumenttorreblanca);
        } else if (resultadoImagen.equals("Arco de Galerio")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentarcogalerio);
        } else if (resultadoImagen.equals("Rotonda de San Jorge")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentrotondasanjorge);
        } else if (resultadoImagen.equals("Museo Arqueológico de Thera")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentmuseoarqueologicothera);
        } else if (resultadoImagen.equals("Sitio Arqueológico de Akrotiri")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentsitioarqueologicoakrotiri);
        } else if (resultadoImagen.equals("Castillo de Oia")) {
            holder.imagenMonumento.setImageResource(R.drawable.monumentcastillooia);
        }
        holder.tvNamePlace.setText(m.getNombre());
        holder.tvPricePlace.setText("Precio: " + m.getPrecio());
        holder.tvTimePlace.setText("Horario: " + m.getHorario());

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

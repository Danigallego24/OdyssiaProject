package com.example.odyssiaproject.ui.city;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.adaptador.AdaptadorCiudades;
import com.example.odyssiaproject.adaptador.AdaptadorPromociones;
import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.persistencia.api.ApiService;
import com.example.odyssiaproject.persistencia.api.RetrofitClient;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CityFragment extends Fragment {

    private RecyclerView recyclerViewPromociones;
    private RecyclerView recyclerViewCiudades;

    private List<Ciudad> listaCiudades= new ArrayList<>();
    private AdaptadorPromociones adaptadorPromociones;
    private AdaptadorCiudades adaptadorCiudades;
    private Handler handler = new Handler();
    private int scrollSpeed = 10;
    private Pais pais;

    // Retrofit API para Firestore
    private ApiService apiService;

    private final Runnable scrollRunnable = new Runnable() {

        @Override
        public void run() {
            recyclerViewPromociones.smoothScrollBy(scrollSpeed, 0);

            if (!recyclerViewPromociones.canScrollHorizontally(1)) {
                recyclerViewPromociones.scrollToPosition(0);
            }

            handler.postDelayed(this, 50);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_city, container, false);

        // Configurar RecyclerView de Promociones
        recyclerViewPromociones = root.findViewById(R.id.rwPromotions);
        recyclerViewPromociones.setHasFixedSize(true);
        recyclerViewPromociones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Obtener lista de promociones desde el Singleton
        List<Promociones> listaPromociones = ListaPromocionesSingelton.getInstance().getListaPromociones();
        if(listaPromociones == null){
            listaPromociones = new ArrayList<>(); // evitamos null pointer
        }

        adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
        recyclerViewPromociones.setAdapter(adaptadorPromociones);

        // Comprobar si la lista de promociones tiene elementos antes de asignar el adaptador
        if (listaPromociones.isEmpty()) {
            adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
            recyclerViewPromociones.setAdapter(adaptadorPromociones);
        } else {
            Log.d("CityFragment", "Lista de promociones está vacía.");
        }

        handler.postDelayed(scrollRunnable, 1000);

        // Configurar RecyclerView de Países
        recyclerViewCiudades = root.findViewById(R.id.rwCities);
        recyclerViewCiudades.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewCiudades.setHasFixedSize(true);


        Log.d("CityFragment", "Tamaño de la lista de ciudades: " + listaCiudades.size());

        if (getArguments() != null) {
            String nombrePais = getArguments().getString("pais");
            if (nombrePais != null) {
                pais = new Pais();
                pais.setNombre(nombrePais);
                // Si tienes más campos, inicialízalos aquí
                Log.d("CityFragment", "País inicializado: " + pais.getNombre());
            } else {
                Log.e("CityFragment", "El argumento 'pais' es null");
            }
        } else {
            Log.e("CityFragment", "No se recibieron argumentos");
        }

        apiService = RetrofitClient.getApiService();
        loadCities();

        return root;
    }

    private void loadCities() {

        if (pais == null) {
            Log.e("CityFragment", "El objeto 'pais' es null, no se puede cargar las ciudades.");
            return;
        }

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Acceder a la colección "paises" y encontrar el documento que coincida con el país
        db.collection("paises")
                .whereEqualTo("nombre", pais.getNombre()) // Filtrar por nombre del país
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        for (QueryDocumentSnapshot paisDoc : task.getResult()) {
                            String paisId = paisDoc.getId(); // Obtener el ID del documento del país

                            // Ahora accedemos a la subcolección "listaCiudades" dentro de este país
                            db.collection("paises")
                                    .document(paisId)
                                    .collection("listaCiudades")
                                    .get()
                                    .addOnCompleteListener(cityTask -> {
                                        if (cityTask.isSuccessful()) {
                                            listaCiudades.clear();
                                            for (QueryDocumentSnapshot cityDoc : cityTask.getResult()) {
                                                // Obtener los datos de la ciudad
                                                String nombreCiudad = cityDoc.getString("nombre");
                                                Log.d("NOMBRE", "NOMBRE DE CIUDAD: " + nombreCiudad);
                                                String descripcion = cityDoc.getString("descripcion");
                                                Log.d("DESCRIPCION", "DESCRIPCION DE CIUDAD: " + descripcion);
                                                String imagenUrl = cityDoc.getString("imagen");

                                                // Crear un objeto Ciudad (debes tener una clase Ciudad en tu proyecto)
                                                Ciudad ciudad = new Ciudad(nombreCiudad, descripcion, imagenUrl);
                                                listaCiudades.add(ciudad);
                                            }

                                            // Actualizar el adaptador
                                            if (adaptadorCiudades == null) {
                                                adaptadorCiudades = new AdaptadorCiudades(listaCiudades);
                                                recyclerViewCiudades.setAdapter(adaptadorCiudades);
                                            } else {
                                                adaptadorCiudades.notifyDataSetChanged();
                                            }
                                        } else {
                                            Log.e("CityFragment", "Error al obtener ciudades.", cityTask.getException());
                                        }
                                    });
                        }
                    } else {
                        Log.e("CityFragment", "País no encontrado.", task.getException());
                    }
                });
    }

}

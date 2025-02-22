package com.example.odyssiaproject.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.adaptador.AdaptadorPaises;
import com.example.odyssiaproject.adaptador.AdaptadorPromociones;
import com.example.odyssiaproject.entidad.FirestoreDocument;
import com.example.odyssiaproject.entidad.FirestoreResponse;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.persistencia.api.ApiService;
import com.example.odyssiaproject.persistencia.api.RetrofitClient;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewPromociones;
    private RecyclerView recyclerViewPaises;

    private List<Pais> listaPaises = new ArrayList<>();
    private AdaptadorPromociones adaptadorPromociones;
    private AdaptadorPaises adaptadorPaises;
    private Handler handler = new Handler();
    private int scrollSpeed = 10;

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
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Configurar RecyclerView de Promociones
        recyclerViewPromociones = root.findViewById(R.id.rwPromotions);
        recyclerViewPromociones.setHasFixedSize(true);
        recyclerViewPromociones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Obtener lista de promociones desde el Singleton
        ListaPromocionesSingelton.getInstance().inicializar();
        List<Promociones> listaPromociones = ListaPromocionesSingelton.getInstance().getListaPromociones();

        // Comprobar si la lista de promociones tiene elementos antes de asignar el adaptador
        if (listaPromociones != null && !listaPromociones.isEmpty()) {
            adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
            recyclerViewPromociones.setAdapter(adaptadorPromociones);
        } else {
            Log.d("HomeFragment", "Lista de promociones está vacía.");
        }

        // Iniciar el desplazamiento automático en el RecyclerView
        handler.postDelayed(scrollRunnable, 1000);

        // Configurar RecyclerView de Países
        recyclerViewPaises = root.findViewById(R.id.rwCountries);
        recyclerViewPaises.setHasFixedSize(true);
        recyclerViewPaises.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Obtener lista de países desde el Singleton
        ListaPaisesSingelton.getInstance().inicializar();
        List<Pais> listaPaises = ListaPaisesSingelton.getInstance().getListaPaises();
        Log.d("HomeFragment", "Tamaño de la lista de países: " + listaPaises.size());

        // Comprobar si la lista de países tiene elementos antes de asignar el adaptador
        if (listaPaises != null && !listaPaises.isEmpty()) {
            adaptadorPaises = new AdaptadorPaises(listaPaises);
            recyclerViewPaises.setAdapter(adaptadorPaises);
        } else {
            Log.d("HomeFragment", "Lista de países está vacía.");
        }

        apiService = RetrofitClient.getApiService();
        loadCountries();

        return root;
    }

    private void loadCountries() {
        // Reemplaza "TU_PROJECT_ID" por el ID de tu proyecto de Firebase
        Call<JsonObject> call = apiService.getPaises(); // Ahora devuelve JsonObject

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("MainActivity", "Respuesta Firestore: " + response.body().toString());
                } else {
                    Log.e("MainActivity", "Error al obtener países: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("MainActivity", "Fallo en la petición: " + t.getMessage());
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (adaptadorPromociones != null) {
            adaptadorPromociones.notifyDataSetChanged();
        }
        if (adaptadorPaises != null) {
            adaptadorPaises.notifyDataSetChanged();
        }
    }
}

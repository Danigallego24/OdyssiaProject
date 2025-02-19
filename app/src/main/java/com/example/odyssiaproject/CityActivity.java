package com.example.odyssiaproject;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.adaptador.AdaptadorCiudades;
import com.example.odyssiaproject.adaptador.AdaptadorPromociones;
import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCiudades;
    private AdaptadorCiudades ciudadesAdapter;
    private AdaptadorPromociones adaptadorPromociones;
    private RecyclerView recyclerViewPromociones;
    private Handler handler = new Handler();
    private int scrollSpeed = 10;
    private List<Ciudad> ciudadesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cityPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerViewPromociones = findViewById(R.id.rwPromotions);
        recyclerViewPromociones.setHasFixedSize(true);
        recyclerViewPromociones.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        List<Promociones> listaPromociones = ListaPromocionesSingelton.getInstance().getListaPromociones();
        adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
        recyclerViewPromociones.setAdapter(adaptadorPromociones);
        handler.postDelayed(scrollRunnable, 1000);

        String paisSeleccionado = getIntent().getStringExtra("pais");
        Log.i("CityActivity", "País recibido: " + paisSeleccionado);
        ciudadesList = new ArrayList<>();
        Pais pais = ListaPaisesSingelton.getInstance().getPaisByName(paisSeleccionado);
        ciudadesList = pais.getListaCiudades();
        recyclerViewCiudades = findViewById(R.id.rwCities);
        recyclerViewCiudades.setHasFixedSize(true);
        recyclerViewCiudades.setLayoutManager(
                new GridLayoutManager(this, 2));

        ciudadesAdapter = new AdaptadorCiudades(ciudadesList);
        recyclerViewCiudades.setAdapter(ciudadesAdapter);
        for (Ciudad ciudad : ciudadesList) {
            Log.i("DEBUG", "Ciudad: " + ciudad.getNombre());
        }
        Log.i("DEBUG", "Ciudades encontradas: " + ciudadesList.size());


    }
    private Runnable scrollRunnable = new Runnable() {
        @Override
        public void run() {
            recyclerViewPromociones.smoothScrollBy(scrollSpeed, 0);

            if (!recyclerViewPromociones.canScrollHorizontally(1)) {
                recyclerViewPromociones.scrollToPosition(0);
            }

            handler.postDelayed(this, 50);
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        adaptadorPromociones.notifyDataSetChanged();
        ciudadesAdapter.notifyDataSetChanged();
    }

}

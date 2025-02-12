package com.example.odyssiaproject;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.adaptador.AdaptadorPaises;
import com.example.odyssiaproject.adaptador.AdaptadorPromociones;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.singelton.ListaCiudadesSingelton;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPromociones;
    private RecyclerView recyclerViewPaises;
    private AdaptadorPromociones adaptadorPromociones;
    private AdaptadorPaises adaptadorPaises;
    private Handler handler = new Handler();
    private int scrollSpeed = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerViewPromociones = findViewById(R.id.rwPromotions);
        recyclerViewPromociones.setHasFixedSize(true);
        recyclerViewPromociones.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        ListaPromocionesSingelton.getInstance().inicializar();
        List<Promociones> listaPromociones = ListaPromocionesSingelton.getInstance().getListaPromociones();
        adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
        recyclerViewPromociones.setAdapter(adaptadorPromociones);
        handler.postDelayed(scrollRunnable, 1000);



        recyclerViewPaises = findViewById(R.id.rwCountries);
        recyclerViewPaises.setHasFixedSize(true);
        recyclerViewPaises.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        ListaCiudadesSingelton.getInstance().inicializar();
        ListaPaisesSingelton.getInstance().inicializar();
        List<Pais> listaPaises = ListaPaisesSingelton.getInstance().getListaPaises();
        adaptadorPaises = new AdaptadorPaises(listaPaises);
        recyclerViewPaises.setAdapter(adaptadorPaises);

    }
    @Override
    protected void onResume() {
        super.onResume();
        adaptadorPromociones.notifyDataSetChanged();
        adaptadorPaises.notifyDataSetChanged();
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


}
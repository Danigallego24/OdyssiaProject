package com.example.odyssiaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
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
import com.example.odyssiaproject.ui.ajustes.ConfigFragment;
import com.example.odyssiaproject.ui.favs.FavsFragment;
import com.example.odyssiaproject.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private RecyclerView recyclerViewCiudades;
    private AdaptadorCiudades ciudadesAdapter;
    private AdaptadorPromociones adaptadorPromociones;
    private RecyclerView recyclerViewPromociones;
    private Handler handler = new Handler();
    private int scrollSpeed = 10;
    private List<Ciudad> ciudadesList;
    private Toolbar toolbar;
    private ImageButton btnMenu;
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        drawerLayout = findViewById(R.id.navBarDrawer);
        navigationView = findViewById(R.id.navBarView);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            // Aquí puedes manejar los clics en los elementos del menú lateral
            drawerLayout.closeDrawer(GravityCompat.START); // Cierra el menú al hacer clic
            return true;
        });

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.cityPage, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.navInicio);
        }

        btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log para verificar que el clic se está registrando
                Log.d("CityActivity", "Botón de menú presionado");

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
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

        String paisSeleccionado = getIntent().getStringExtra("pais");
        ciudadesList = new ArrayList<>();
        Pais pais = ListaPaisesSingelton.getInstance().getPaisByName(paisSeleccionado);
        if (pais != null && pais.getListaCiudades() != null) {
            ciudadesList = pais.getListaCiudades();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewCiudades = findViewById(R.id.rwCities);
        recyclerViewCiudades.setHasFixedSize(true);
        recyclerViewCiudades.setLayoutManager(gridLayoutManager);

        ciudadesAdapter = new AdaptadorCiudades(ciudadesList);
        recyclerViewCiudades.setAdapter(ciudadesAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        Log.d("NAVIGATION", "Item seleccionado: " + item.getItemId());

        if (item.getItemId() == R.id.navInicio) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.navFavoritos) {
            Log.d("NAVIGATION", "Cargando FragmentFavs...");
            fragment = new FavsFragment();
        } else if (item.getItemId() == R.id.navConfiguracion) {
            Log.d("NAVIGATION", "Cargando ConfigFragment...");
            fragment = new ConfigFragment();
        }

        if (fragment != null) {
            Log.d("NAVIGATION", "Llamando a loadFragment...");
            loadFragment(fragment);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.cityPage);
        if (currentFragment != null && currentFragment.getClass() == fragment.getClass()) {
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.cityPage, fragment).commit();
    }

    // Para manejar la apertura y cierre del Drawer desde el botón de la barra de acción
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

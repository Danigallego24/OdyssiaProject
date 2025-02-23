package com.example.odyssiaproject;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.odyssiaproject.ui.ajustes.ConfigFragment;
import com.example.odyssiaproject.ui.favs.FavsFragment;
import com.example.odyssiaproject.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;


public class CityActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
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
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
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

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        Log.d("NAVIGATION", "Item seleccionado: " + item.getItemId());

        if (item.getItemId() == R.id.navInicio) {
            Log.d("NAVIGATION", "Cargando HomeFragment...");
            loadFragment(new HomeFragment());
        } else if (item.getItemId() == R.id.navFavoritos) {
            Log.d("NAVIGATION", "Cargando FavsFragment...");
            loadFragment(new FavsFragment());
        } else if (item.getItemId() == R.id.navConfiguracion) {
            Log.d("NAVIGATION", "Cargando ConfigFragment...");
            loadFragment(new ConfigFragment());
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);  // Reemplaza el contenedor
        transaction.commit();
    }

    // Para manejar la apertura y cierre del Drawer desde el botón de la barra de acción
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

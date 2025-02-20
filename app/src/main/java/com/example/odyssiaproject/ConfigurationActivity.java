package com.example.odyssiaproject;

import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;

    @Override
    public void setContentView(int layoutResID) {
        // Inflamos el layout base que contiene el DrawerLayout
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_configuration, null);
        // Buscamos el contenedor principal
        FrameLayout frameLayout = fullView.findViewById(R.id.content_frame);
        // Inflamos el layout específico de la Activity dentro del contenedor
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(fullView);

        // Obtenemos referencia al DrawerLayout para usarlo en cualquier Activity que herede de BaseActivity
        drawerLayout = fullView.findViewById(R.id.navBarDrawer);
    }

    // Método para abrir el menú lateral
    public void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
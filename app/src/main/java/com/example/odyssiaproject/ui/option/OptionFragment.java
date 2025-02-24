package com.example.odyssiaproject.ui.option;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.adaptador.AdaptadorMonumentos;
import com.example.odyssiaproject.adaptador.AdaptadorPromociones;
import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.Monumentos;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.singelton.ListaMonumentosSingelton;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class OptionFragment extends Fragment {

    private RecyclerView recyclerViewPromociones;
    private RecyclerView recyclerViewOpciones;
    private List<Monumentos> listaMonumentos = new ArrayList<>();
    private AdaptadorPromociones adaptadorPromociones;
    private AdaptadorMonumentos adaptadorMonumentos;
    private Handler handler = new Handler();
    private int scrollSpeed = 10;

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
        View root = inflater.inflate(R.layout.fragment_option, container, false);

        DrawerLayout drawerLayout = getActivity().findViewById(R.id.navBarDrawer);
        NavigationView navigationView = drawerLayout.findViewById(R.id.navBarView);
        if (navigationView != null) {
            // Acceder a la vista del encabezado dentro del NavigationView
            View headerView = navigationView.getHeaderView(0);
            TextView userEmailTextView = headerView.findViewById(R.id.twUsuario);

            // Obtener el correo del usuario desde Firebase
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if (currentUser != null) {
                String userEmail = currentUser.getEmail();
                userEmailTextView.setText(userEmail);  // Establecer el correo en el TextView
            } else {
                // Si no hay usuario autenticado, manejar la situación
                userEmailTextView.setText("Usuario no autenticado");
            }
        }

        // Configurar RecyclerView de Promociones
        recyclerViewPromociones = root.findViewById(R.id.rwPromotions);
        recyclerViewPromociones.setHasFixedSize(true);
        recyclerViewPromociones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Obtener lista de promociones desde el Singleton
        List<Promociones> listaPromociones = ListaPromocionesSingelton.getInstance().getListaPromociones();
        if(listaPromociones == null){
            listaPromociones = new ArrayList<>(); // evitamois null pointer
        }

        adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
        recyclerViewPromociones.setAdapter(adaptadorPromociones);

        // Comprobar si la lista de promociones tiene elementos antes de asignar el adaptador
        if (listaPromociones != null && !listaPromociones.isEmpty()) {
            adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
            recyclerViewPromociones.setAdapter(adaptadorPromociones);
        } else {
            Log.d("OptionFragment", "Lista de promociones está vacía.");
        }

        handler.postDelayed(scrollRunnable, 1000);


        // Configurar RecyclerView de Países
        recyclerViewOpciones = root.findViewById(R.id.rwOptions);
        recyclerViewOpciones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewOpciones.setHasFixedSize(true);

        // Obtener lista de países desde el Singleton
        listaMonumentos = ListaMonumentosSingelton.getInstance().getListaMonumentos();
        if(listaMonumentos == null){
            listaMonumentos = new ArrayList<>(); // evitamois null pointer
        }

        if (getArguments() != null) {
            String nombreCiudad = getArguments().getString("ciudad");
            if (nombreCiudad != null) {
                Ciudad ciudad = new Ciudad();
                ciudad.setNombre(nombreCiudad);
                // Si tienes más campos, inicialízalos aquí
                Log.d("OPtionFragment", "Ciudadd inicializado: " + ciudad.getNombre());
            } else {
                Log.e("OPtionFragment", "El argumento 'ciudad' es null");
            }
        } else {
            Log.e("OPtionFragment", "No se recibieron argumentos");
        }

        adaptadorMonumentos = new AdaptadorMonumentos(listaMonumentos);
        recyclerViewOpciones.setAdapter(adaptadorMonumentos);

        // Comprobar si la lista de promociones tiene elementos antes de asignar el adaptador
        if (listaMonumentos != null && !listaMonumentos.isEmpty()) {
            adaptadorMonumentos = new AdaptadorMonumentos(listaMonumentos);
            recyclerViewOpciones.setAdapter(adaptadorMonumentos);
        } else {
            Log.d("OptionFragment", "Lista de monumentos está vacía.");
        }

        return root;
    }
}

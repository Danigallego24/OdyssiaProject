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
import com.example.odyssiaproject.adaptador.AdaptadorCiudades;
import com.example.odyssiaproject.adaptador.AdaptadorMonumentos;
import com.example.odyssiaproject.adaptador.AdaptadorPromociones;
import com.example.odyssiaproject.entidad.Monumentos;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.negocio.GestorMonumentos;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class OptionFragment extends Fragment {

    private RecyclerView recyclerViewPromociones;
    private RecyclerView recyclerViewMonumentos;
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

    // Método de fábrica para crear una nueva instancia pasando el nombre de la ciudad
    public static OptionFragment newInstance(String ciudad) {
        OptionFragment fragment = new OptionFragment();
        Bundle args = new Bundle();
        args.putString("ciudad", ciudad);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_option, container, false);

        // Configuración del encabezado del NavigationView para mostrar el correo del usuario
        DrawerLayout drawerLayout = getActivity().findViewById(R.id.navBarDrawer);
        NavigationView navigationView = drawerLayout.findViewById(R.id.navBarView);
        if (navigationView != null) {
            View headerView = navigationView.getHeaderView(0);
            TextView userEmailTextView = headerView.findViewById(R.id.twUsuario);
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                userEmailTextView.setText(currentUser.getEmail());
            } else {
                userEmailTextView.setText("Usuario no autenticado");
            }
        }

        // Configurar RecyclerView de Promociones (horizontal)
        recyclerViewPromociones = root.findViewById(R.id.rwPromotions);
        recyclerViewPromociones.setHasFixedSize(true);
        recyclerViewPromociones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<Promociones> listaPromociones = ListaPromocionesSingelton.getInstance().getListaPromociones();
        if (listaPromociones == null) {
            listaPromociones = new ArrayList<>();
        }
        adaptadorPromociones = new AdaptadorPromociones(listaPromociones);
        recyclerViewPromociones.setAdapter(adaptadorPromociones);

        handler.postDelayed(scrollRunnable, 1000);

        // Configurar RecyclerView de Monumentos (vertical)
        recyclerViewMonumentos = root.findViewById(R.id.rwOptions);
        recyclerViewMonumentos.setHasFixedSize(true);
        recyclerViewMonumentos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Recuperar el nombre de la ciudad enviado como argumento y filtrar los monumentos
        String nombreCiudad = "";
        if (getArguments() != null) {
            nombreCiudad = getArguments().getString("ciudad", "");
            Log.d("OptionFragment", "Ciudad recibida: " + nombreCiudad);
        } else {
            Log.e("OptionFragment", "No se recibieron argumentos");
        }

        List<Monumentos> listaMonumentos;
        if (!nombreCiudad.isEmpty()) {
            GestorMonumentos gestor = new GestorMonumentos();
            listaMonumentos = gestor.obtenerMonumentosPorCiudad(nombreCiudad);
        } else {
            // Si no se recibe ciudad, se muestran todos los monumentos
            listaMonumentos = new ArrayList<>();
        }

        adaptadorMonumentos = new AdaptadorMonumentos(listaMonumentos);
        recyclerViewMonumentos.setAdapter(adaptadorMonumentos);

        return root;
    }
}

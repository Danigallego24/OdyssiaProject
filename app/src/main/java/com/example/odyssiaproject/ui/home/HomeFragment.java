package com.example.odyssiaproject.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odyssiaproject.R;
import com.example.odyssiaproject.adaptador.AdaptadorPaises;
import com.example.odyssiaproject.adaptador.AdaptadorPromociones;
import com.example.odyssiaproject.entidad.Pais;
import com.example.odyssiaproject.entidad.Promociones;
import com.example.odyssiaproject.persistencia.api.ApiService;
import com.example.odyssiaproject.persistencia.api.RetrofitClient;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


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
            Log.d("HomeFragment", "Lista de promociones está vacía.");
        }

        handler.postDelayed(scrollRunnable, 1000);

        // Configurar RecyclerView de Países
        recyclerViewPaises = root.findViewById(R.id.rwCountries);
        recyclerViewPaises.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewPaises.setHasFixedSize(true);

        // Obtener lista de países desde el Singleton
        listaPaises = ListaPaisesSingelton.getInstance().getListaPaises();
        Log.d("HomeFragment", "Tamaño de la lista de países: " + listaPaises.size());

        apiService = RetrofitClient.getApiService();
        loadCountries();

        return root;
    }

    private void loadCountries() {

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("paises")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaPaises.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String nombre = document.getString("nombre"); // Obtener el nombre
                            String imagen = document.getString("imagen"); // Obtener la imagen

                            // Verificar que el nombre e imagen no sean nulos
                            if (nombre != null && imagen != null) {
                                listaPaises.add(new Pais(nombre, imagen));
                            } else {
                                Log.w("HomeFragment", "Nombre o imagen nulos en el documento: " + document.getId());
                            }
                        }

                        if (adaptadorPaises == null) {
                            adaptadorPaises = new AdaptadorPaises(listaPaises);
                            recyclerViewPaises.setAdapter(adaptadorPaises);
                        } else {
                            adaptadorPaises.notifyDataSetChanged();
                        }
                    }else {
                        Log.e("HomeFragment", "Error getting documents.", task.getException());
                        // Aquí podrías manejar el error, por ejemplo, mostrar un mensaje de error.
                    }
                });
    }
}

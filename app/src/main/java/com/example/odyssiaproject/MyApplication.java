package com.example.odyssiaproject;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;
import com.example.odyssiaproject.singelton.ListaCiudadesSingelton;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;

/**
 * Clase MyApplication que extiende de Application.
 * Se encarga de inicializar Firebase y las listas singleton al iniciar la aplicación.
 */
public class MyApplication extends Application {
    /**
     * Método que se ejecuta cuando la aplicación se inicia.
     * Se utiliza para inicializar Firebase y cargar las listas de datos en los Singletons.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        ListaPromocionesSingelton.getInstance().inicializar();
        ListaPaisesSingelton.getInstance().inicializar();
    }
}
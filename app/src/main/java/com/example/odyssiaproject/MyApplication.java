package com.example.odyssiaproject;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.example.odyssiaproject.singelton.ListaPromocionesSingelton;
import com.example.odyssiaproject.singelton.ListaCiudadesSingelton;
import com.example.odyssiaproject.singelton.ListaPaisesSingelton;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        ListaPromocionesSingelton.getInstance().inicializar();
        ListaCiudadesSingelton.getInstance().inicializar();
        ListaPaisesSingelton.getInstance().inicializar();
    }
}
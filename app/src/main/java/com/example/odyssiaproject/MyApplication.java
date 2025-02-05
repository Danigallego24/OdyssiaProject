package com.example.odyssiaproject;

import android.app.Application;
import com.google.firebase.FirebaseApp;

//Clase que utilizamos para inicializar el firebase

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}

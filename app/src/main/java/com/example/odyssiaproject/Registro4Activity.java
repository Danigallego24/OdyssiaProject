package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Registro4Activity extends AppCompatActivity {

    private ImageButton btnLogin; // Botón para ir al inicio de sesión
    private FirebaseAuth auth; // Instancia de FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        btnLogin = findViewById(R.id.btnContinuar);
        auth = FirebaseAuth.getInstance(); // Inicializar FirebaseAuth

        // Configuramos el botón para cerrar sesión y redirigir a LogIn
        btnLogin.setOnClickListener(view -> cerrarSesionYRedirigir());
    }
    private void cerrarSesionYRedirigir() {
        auth.signOut(); // Cierra sesión en Firebase
        Intent intent = new Intent(Registro4Activity.this, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Evita volver atrás
        startActivity(intent);
        finish();

    }
}

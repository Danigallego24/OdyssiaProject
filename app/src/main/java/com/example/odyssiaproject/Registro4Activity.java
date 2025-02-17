package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class Registro4Activity extends AppCompatActivity {

    private TextView tvMensaje;
    private ImageButton btnLogin;  // Botón para ir al inicio de sesión

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        btnLogin = findViewById(R.id.btnContinuar);

        tvMensaje.setText("Te has registrado correctamente");

        // Configuramos el botón para que, al pulsarlo, abra la pantalla de inicio de sesión
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Registro4Activity.this, LogIn.class);
            startActivity(intent);
            finish();
        });
    }
}

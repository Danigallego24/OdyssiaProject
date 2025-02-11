package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Registro4Activity extends AppCompatActivity {

    private TextView tvMensaje;
    private Button btnLogin;  // Botón para ir al inicio de sesión

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        btnLogin = findViewById(R.id.btnLogin);

        tvMensaje.setText("Te has registrado correctamente");

        // Configuramos el botón para que, al pulsarlo, abra la pantalla de inicio de sesión
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Registro4Activity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Opcional: cerrar Registro4Activity para que no vuelva atrás
        });
    }
}

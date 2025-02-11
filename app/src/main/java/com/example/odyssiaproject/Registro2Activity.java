package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Registro2Activity extends AppCompatActivity {

    private EditText etCorreo, etContrasenia;
    private ImageButton btnSiguiente;
    private String nombre, apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        etCorreo = findViewById(R.id.userField);
        etContrasenia = findViewById(R.id.passField);
        btnSiguiente = findViewById(R.id.buttonNext);

        // Recoger datos de Registro1Activity
        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        apellido = intent.getStringExtra("apellido");

        btnSiguiente.setOnClickListener(view -> {
            String correo = etCorreo.getText().toString().trim();
            String contrasenia = etContrasenia.getText().toString().trim();
            Intent intent2 = new Intent(Registro2Activity.this, Registro3Activity.class);
            intent2.putExtra("nombre", nombre);
            intent2.putExtra("apellido", apellido);
            intent2.putExtra("correo", correo);
            intent2.putExtra("contrasenia", contrasenia);
            startActivity(intent2);
        });
    }
}

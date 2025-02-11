package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Registro1Activity extends AppCompatActivity {

    private EditText etNombre, etApellido;
    private ImageButton btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombre = findViewById(R.id.nameField);
        etApellido = findViewById(R.id.surnameField);
        btnSiguiente = findViewById(R.id.buttonNext);

        btnSiguiente.setOnClickListener(view -> {
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();
            Intent intent = new Intent(Registro1Activity.this, Registro2Activity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellido", apellido);
            startActivity(intent);
        });
    }
}


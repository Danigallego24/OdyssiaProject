package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Registro1Activity extends AppCompatActivity {

    private EditText etNombre, etApellido;
    private ImageButton btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicialización de vistas
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        btnContinuar = findViewById(R.id.btnContinuar);

        // Configurar listener para el botón "Continuar"
        btnContinuar.setOnClickListener(view -> {
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();

            // Validación de campos vacíos
            if (nombre.isEmpty() || apellido.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pasar los datos a la siguiente actividad
            Intent intent = new Intent(Registro1Activity.this, Registro2Activity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellido", apellido);
            startActivity(intent);
        });
    }
}

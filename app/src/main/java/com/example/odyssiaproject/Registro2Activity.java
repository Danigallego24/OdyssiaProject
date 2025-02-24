package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

        // Inicializar vistas
        etCorreo = findViewById(R.id.etEMail);
        etContrasenia = findViewById(R.id.etContrasenia);
        btnSiguiente = findViewById(R.id.btnContinuar);

        // Recoger datos de la actividad anterior
        Intent intent = getIntent();
        if (intent != null) {
            nombre = intent.getStringExtra("nombre");
            apellido = intent.getStringExtra("apellido");
        }

        // Configurar listener del botón
        btnSiguiente.setOnClickListener(view -> validarDatosYContinuar());
    }

    private void validarDatosYContinuar() {
        String correo = etCorreo.getText().toString().trim();
        String contrasenia = etContrasenia.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            etCorreo.setError("El correo es obligatorio");
            etCorreo.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Correo inválido");
            etCorreo.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(contrasenia)) {
            etContrasenia.setError("La contraseña es obligatoria");
            etContrasenia.requestFocus();
            return;
        }

        if (contrasenia.length() < 6) {
            etContrasenia.setError("La contraseña debe tener al menos 6 caracteres");
            etContrasenia.requestFocus();
            return;
        }

        // Si todo está bien, pasar a la siguiente actividad
        Intent intent2 = new Intent(Registro2Activity.this, Registro3Activity.class);
        intent2.putExtra("nombre", nombre);
        intent2.putExtra("apellido", apellido);
        intent2.putExtra("correo", correo);
        intent2.putExtra("contrasenia", contrasenia);
        startActivity(intent2);
    }
}

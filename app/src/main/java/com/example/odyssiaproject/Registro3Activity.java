package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.odyssiaproject.entidad.Usuario;
import com.example.odyssiaproject.entidad.Sexo;
import com.example.odyssiaproject.negocio.GestorUsuario;

public class Registro3Activity extends AppCompatActivity {

    private EditText etFechaNacimiento, etNacionalidad;
    private ImageButton btnRegistrar;
    private String nombre, apellido, correo, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        etFechaNacimiento = findViewById(R.id.dateField);
        etNacionalidad = findViewById(R.id.nationalityField);
        btnRegistrar = findViewById(R.id.buttonNext);

        // Recoger datos de Registro2Activity
        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        apellido = intent.getStringExtra("apellido");
        correo = intent.getStringExtra("correo");
        contrasenia = intent.getStringExtra("contrasenia");

        btnRegistrar.setOnClickListener(view -> {
            String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
            String nacionalidad = etNacionalidad.getText().toString().trim();


            Usuario usuario = new Usuario( nombre, apellido, fechaNacimiento, nacionalidad, contrasenia, correo);

            GestorUsuario gestor = new GestorUsuario();
            gestor.registrar(usuario, new GestorUsuario.OnRegistroListener() {
                @Override
                public void onSuccess(com.google.firebase.auth.FirebaseUser user) {
                    Toast.makeText(Registro3Activity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    // Pasar a la pantalla final de confirmación
                    Intent intentFinal = new Intent(Registro3Activity.this, Registro4Activity.class);
                    startActivity(intentFinal);
                    finish();
                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(Registro3Activity.this, "Error: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}

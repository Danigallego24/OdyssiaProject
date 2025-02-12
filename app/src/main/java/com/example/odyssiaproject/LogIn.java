package com.example.odyssiaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.odyssiaproject.entidad.Usuario;
import com.example.odyssiaproject.negocio.GestorUsuario;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        GestorUsuario gestorUsuario = new GestorUsuario();
        EditText correoUser = findViewById(R.id.etUsuario);
        EditText pass = findViewById(R.id.etContrasenia);
        ImageButton buttonRegister = findViewById(R.id.btnRegistro);
        Button buttonRecover = findViewById(R.id.btnRecuperar);
        ImageButton buttonNext = findViewById(R.id.btnInicio);

        buttonNext.setOnClickListener(v -> {

            String correo = correoUser.getText().toString().trim();
            String contrasenia = pass.getText().toString().trim();

            Usuario usuario = new Usuario(correo, contrasenia);

            gestorUsuario.iniciarSesion(usuario, new GestorUsuario.OnLoginListener() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    Toast.makeText(LogIn.this, "Inicio de sesión exitoso: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    // Redirigir a la actividad principal
                    startActivity(new Intent(LogIn.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(LogIn.this, "Error en el inicio de sesión: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LogIn.this, Registro1Activity.class);
                    startActivity(intent);
                }
            });

            buttonRecover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LogIn.this, RecoverPassActivity.class);
                    startActivity(intent);
                }
            });
        });
    }
}
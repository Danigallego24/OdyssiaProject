package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.odyssiaproject.entidad.Usuario;
import com.example.odyssiaproject.entidad.Sexo;
import com.example.odyssiaproject.negocio.GestorUsuario;
import com.google.firebase.auth.FirebaseUser;

public class Registro3Activity extends AppCompatActivity {

    private EditText etFechaNacimiento, etNacionalidad;
    private ImageButton btnRegistrar;
    private Spinner spinnerSexo;
    private String nombre, apellido, correo, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        // Asigna las vistas usando los IDs correctos
        etFechaNacimiento = findViewById(R.id.etNacimiento);       // Campo para la fecha de nacimiento
        etNacionalidad = findViewById(R.id.etNacionalidad);         // Campo para la nacionalidad
        spinnerSexo = findViewById(R.id.spinnerGender);              // Spinner para seleccionar el sexo
        btnRegistrar = findViewById(R.id.btnContinuar);              // Botón para continuar

        // Configura el ArrayAdapter para el Spinner usando los valores del enum Sexo
        ArrayAdapter<Sexo> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Sexo.values() // Obtiene los valores del enum
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);

        // Recoge los datos enviados desde la actividad anterior (Registro2Activity)
        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        apellido = intent.getStringExtra("apellido");
        correo = intent.getStringExtra("correo");
        contrasenia = intent.getStringExtra("contrasenia");

        btnRegistrar.setOnClickListener(view -> {
            // Lee los datos ingresados en esta pantalla
            String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
            String nacionalidad = etNacionalidad.getText().toString().trim();

            // Obtén directamente el valor seleccionado del Spinner (ya es del tipo Sexo)
            Sexo sexo = (Sexo) spinnerSexo.getSelectedItem();

            // Crea el objeto Usuario con todos los datos recogidos
            Usuario usuario = new Usuario(0, nombre, apellido, fechaNacimiento, nacionalidad, contrasenia, correo, sexo);

            // Registra el usuario a través del GestorUsuario (que utiliza Firestore y verifica duplicados)
            GestorUsuario gestor = new GestorUsuario();
            gestor.registrar(usuario, new GestorUsuario.OnRegistroListener() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    Toast.makeText(Registro3Activity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    // Inicia la siguiente actividad y cierra esta
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

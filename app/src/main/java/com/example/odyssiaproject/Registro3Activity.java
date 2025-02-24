package com.example.odyssiaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    private GestorUsuario gestor; // Gestor de usuario para registro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        // Inicialización de vistas
        etFechaNacimiento = findViewById(R.id.etNacimiento);
        etNacionalidad = findViewById(R.id.etNacionalidad);
        spinnerSexo = findViewById(R.id.spinnerGender);
        btnRegistrar = findViewById(R.id.btnContinuar);

        gestor = new GestorUsuario(); // Inicializa el gestor

        // Configuración del Spinner con valores del enum Sexo
        ArrayAdapter<Sexo> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Sexo.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);

        // Recoger datos de la actividad anterior (Registro2Activity)
        obtenerDatosIntent();

        btnRegistrar.setOnClickListener(view -> registrarUsuario());
    }

    private void obtenerDatosIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            nombre = intent.getStringExtra("nombre");
            apellido = intent.getStringExtra("apellido");
            correo = intent.getStringExtra("correo");
            contrasenia = intent.getStringExtra("contrasenia");
        }

        // Validación básica para evitar valores nulos
        if (nombre == null || apellido == null || correo == null || contrasenia == null) {
            Toast.makeText(this, "Error al recibir datos. Vuelve a intentarlo.", Toast.LENGTH_SHORT).show();
            finish(); // Cierra la actividad si los datos son inválidos
        }
    }

    private void registrarUsuario() {
        // Obtener valores ingresados
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
        String nacionalidad = etNacionalidad.getText().toString().trim();
        Sexo sexo = (Sexo) spinnerSexo.getSelectedItem();

        // Validaciones antes del registro
        if (fechaNacimiento.isEmpty() || nacionalidad.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!fechaNacimiento.matches("\\d{2}/\\d{2}/\\d{4}")) {
            Toast.makeText(this, "Formato de fecha inválido. Usa dd/MM/yyyy", Toast.LENGTH_SHORT).show();
            return;
        }

        // Creación del objeto Usuario
        Usuario usuario = new Usuario(0, nombre, apellido, fechaNacimiento, nacionalidad, contrasenia, correo, sexo);

        // Inicia el registro del usuario en Firebase a través del GestorUsuario
        gestor.registrar(usuario, new GestorUsuario.OnRegistroListener() {
            @Override
            public void onSuccess(FirebaseUser user) {
                guardarCorreoEnPreferencias();
                Dialogos.showLoading(Registro3Activity.this, "Registrando Usuario...");
                irASiguienteActividad();
            }

            @Override
            public void onFailure(Exception exception) {
                String mensajeError = obtenerMensajeErrorFirebase(exception);
                Dialogos.showErrorRegister(Registro3Activity.this, mensajeError);
            }
        });
    }
    /**
     * Guarda el correo electrónico del usuario en SharedPreferences.
     * Esto permite que el correo quede almacenado localmente en el dispositivo,
     * para poder recuperarlo más adelante sin necesidad de volver a ingresarlo.
     */
    private void guardarCorreoEnPreferencias() {
        SharedPreferences preferences = getSharedPreferences("usuario", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("correo", correo);
        editor.apply();
    }

    /**
     * Navega a la siguiente actividad después de un registro exitoso.
     * Se inicia Registro4Activity y se finaliza la actividad actual para que
     * el usuario no pueda regresar con el botón "atrás".
     */
    private void irASiguienteActividad() {
        Intent intentFinal = new Intent(Registro3Activity.this, Registro4Activity.class);
        startActivity(intentFinal);
        finish(); // Cierra esta actividad para evitar que el usuario regrese con "atrás"
    }

    private String obtenerMensajeErrorFirebase(Exception exception) {
        if (exception == null || exception.getMessage() == null) {
            return "Ocurrió un error desconocido.";
        }

        String mensaje = exception.getMessage();
        Log.e("RegistroError", mensaje); // Log para depuración

        if (mensaje.contains("Password should be at least 6 characters")) {
            return "La contraseña debe tener al menos 6 caracteres.";
        } else if (mensaje.contains("The email address is already in use")) {
            return "El correo ya está en uso.";
        }
        return mensaje;
    }
}

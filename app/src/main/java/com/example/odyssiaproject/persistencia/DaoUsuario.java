package com.example.odyssiaproject.persistencia;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.odyssiaproject.entidad.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class DaoUsuario {
    private static final String TAG = "DaoUsuario";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public DaoUsuario() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    // Iniciar sesión
    public void iniciarSesion(String correo, String contrasenia, final OnLoginListener listener) {
        mAuth.signInWithEmailAndPassword(correo, contrasenia)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        listener.onSuccess(user);
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }

    public interface OnLoginListener {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
    }

    // Registrar usuario con verificación de duplicados en Firestore
    public void registrarUsuario(final Usuario usuario, final OnRegistroListener listener) {
        db.collection("usuario").whereEqualTo("correo", usuario.getCorreo()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        listener.onFailure(new Exception("El correo ya está en uso."));
                    } else {
                        crearUsuarioEnAuth(usuario, listener);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    private void crearUsuarioEnAuth(final Usuario usuario, final OnRegistroListener listener) {
        mAuth.createUserWithEmailAndPassword(usuario.getCorreo(), usuario.getContrasenia())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            guardarUsuarioEnFirestore(firebaseUser, usuario, listener);
                        } else {
                            listener.onFailure(new Exception("No se pudo obtener el usuario después del registro."));
                        }
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }

    private void guardarUsuarioEnFirestore(FirebaseUser firebaseUser, Usuario usuario, OnRegistroListener listener) {
        String uid = firebaseUser.getUid();
        Map<String, Object> userData = new HashMap<>();
        userData.put("uid", uid);
        userData.put("contrasenia", usuario.getContrasenia());
        userData.put("nombre", usuario.getNombre());
        userData.put("apellido", usuario.getApellido());
        userData.put("fechaNacimiento", usuario.getFechaNacimiento());
        userData.put("nacionalidad", usuario.getNacionalidad());
        userData.put("correo", usuario.getCorreo());
        userData.put("sexo", usuario.getSexo().toString());

        db.collection("usuario").document(uid).set(userData)
                .addOnSuccessListener(unused -> {
                    Log.d(TAG, "Usuario registrado con éxito en Firestore");
                    listener.onSuccess(firebaseUser);
                })
                .addOnFailureListener(listener::onFailure);
    }

    public interface OnRegistroListener {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
    }

    // Recuperar contraseña
    public void recuperarContrasenia(String email, final OnRecuperacionListener listener) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }
    public interface OnRecuperacionListener {
        void onSuccess();
        void onFailure(Exception exception);
    }
}

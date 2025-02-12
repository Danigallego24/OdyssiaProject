package com.example.odyssiaproject.persistencia;
import androidx.annotation.NonNull;

import com.example.odyssiaproject.entidad.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DaoUsuario {

        private FirebaseAuth mAuth;

        public DaoUsuario() {
            mAuth = FirebaseAuth.getInstance();
        }

        public void iniciarSesion(String correo, String contrasenia, final OnLoginListener listener) {
            mAuth.signInWithEmailAndPassword(correo, contrasenia)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                listener.onSuccess(user);
                            } else {
                                listener.onFailure(task.getException());
                            }
                        }
                    });
        }

        public interface OnLoginListener {
            void onSuccess(FirebaseUser user);
            void onFailure(Exception exception);
        }

    public void registrarUsuario(final Usuario usuario, final OnRegistroListener listener) {
        // Se crea el usuario en Firebase Authentication
        mAuth.createUserWithEmailAndPassword(usuario.getCorreo(), usuario.getContrasenia())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Obtiene el usuario recién creado
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            // Obtiene el UID del usuario para usarlo como clave en la base de datos
                            String uid = firebaseUser.getUid();

                            // Referencia al nodo "usuarios" y al subnodo con el UID
                            DatabaseReference reference = FirebaseDatabase.getInstance()
                                    .getReference("usuarios")
                                    .child(uid);

                            // Guarda el objeto 'usuario' en la base de datos
                            reference.setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> taskDB) {
                                    if (taskDB.isSuccessful()) {
                                        // Registro exitoso y datos guardados correctamente
                                        listener.onSuccess(firebaseUser);
                                    } else {
                                        // Error al guardar en la base de datos
                                        listener.onFailure(taskDB.getException());
                                    }
                                }
                            });
                        } else {
                            // Error al crear el usuario en Firebase Authentication
                            listener.onFailure(task.getException());
                        }
                    }
                });
    }

    // Interfaz para manejar el callback del registro
    public interface OnRegistroListener {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
    }

    public void recuperarContrasenia(String email, final OnRecuperacionListener listener) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            listener.onSuccess();
                        } else {
                            listener.onFailure(task.getException());
                        }
                    }
                });
    }

    public interface OnRecuperacionListener {
        void onSuccess();
        void onFailure(Exception exception);
    }
}




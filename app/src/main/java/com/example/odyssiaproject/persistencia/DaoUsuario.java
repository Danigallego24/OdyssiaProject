package com.example.odyssiaproject.persistencia;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



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
    }


package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Usuario;
import com.example.odyssiaproject.persistencia.DaoUsuario;
import com.google.firebase.auth.FirebaseUser;

public class GestorUsuario {

        private DaoUsuario usuarioDao;

        public GestorUsuario() {
            usuarioDao = new DaoUsuario();
        }

        public void iniciarSesion(Usuario usuario, final OnLoginListener listener) {
            // Validaciones adicionales (por ejemplo, formato de correo)
            if (usuario.getCorreo().isEmpty() || usuario.getContrasenia().isEmpty()) {
                listener.onFailure(new Exception("Correo y contraseña son obligatorios"));
                return;
            }

            // Llamar al DAO para iniciar sesión
            usuarioDao.iniciarSesion(usuario.getCorreo(), usuario.getContrasenia(), new DaoUsuario.OnLoginListener() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    listener.onSuccess(user);
                }

                @Override
                public void onFailure(Exception exception) {
                    listener.onFailure(exception);
                }
            });
        }

    public interface OnLoginListener {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
        }
    }
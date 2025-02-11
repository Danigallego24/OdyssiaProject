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

    public void registrar(Usuario usuario, final OnRegistroListener listener) {
        // Validaciones básicas (por ejemplo, que los campos no estén vacíos)
        if (usuario.getNombre().isEmpty() || usuario.getApellido().isEmpty() ||
                usuario.getCorreo().isEmpty() || usuario.getContrasenia().isEmpty()) {
            listener.onFailure(new Exception("Campos obligatorios vacíos"));
            return;
        }
        usuarioDao.registrarUsuario(usuario, new DaoUsuario.OnRegistroListener() {
            @Override
            public void onSuccess(com.google.firebase.auth.FirebaseUser user) {
                listener.onSuccess(user);
            }

            @Override
            public void onFailure(Exception exception) {
                listener.onFailure(exception);
            }
        });
    }

    public interface OnRegistroListener {
        void onSuccess(com.google.firebase.auth.FirebaseUser user);
        void onFailure(Exception exception);
    }

    public void recuperarContrasenia(String email, final OnRecuperacionListener listener) {
        // Validación en la capa de negocio
        if(email == null || email.trim().isEmpty()){
            listener.onFailure(new Exception("Debes ingresar un correo electrónico."));
            return;
        }

        // Si la validación es correcta, delega en el DAO
        usuarioDao.recuperarContrasenia(email, new DaoUsuario.OnRecuperacionListener() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Exception exception) {
                listener.onFailure(exception);
            }
        });
    }

    // Interfaz para el callback en la capa de negocio
    public interface OnRecuperacionListener {
        void onSuccess();
        void onFailure(Exception exception);
    }
    }
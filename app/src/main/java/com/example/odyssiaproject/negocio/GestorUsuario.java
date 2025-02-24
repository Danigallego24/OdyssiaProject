package com.example.odyssiaproject.negocio;

import com.example.odyssiaproject.entidad.Usuario;
import com.example.odyssiaproject.persistencia.DaoUsuario;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class GestorUsuario {

    private DaoUsuario usuarioDao;

    public GestorUsuario() {
        usuarioDao = new DaoUsuario();
    }

    // Método para iniciar sesión: valida que correo y contraseña no estén vacíos
    public void iniciarSesion(Usuario usuario, final OnLoginListener listener) {
        if (usuario.getCorreo().isEmpty() || usuario.getContrasenia().isEmpty()) {
            listener.onFailure(new Exception("Correo y contraseña son obligatorios"));
            return;
        }
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

    // Interfaz para el callback del inicio de sesión
    public interface OnLoginListener {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
    }

    // Método para registrar un usuario
    public void registrar(Usuario usuario, final OnRegistroListener listener) {
        // Validaciones básicas de campos obligatorios
        if (usuario.getNombre().isEmpty() ||
                usuario.getApellido().isEmpty() ||
                usuario.getCorreo().isEmpty() ||
                usuario.getContrasenia().isEmpty()) {
            listener.onFailure(new Exception("Campos obligatorios vacíos"));
            return;
        }

        if (!usuario.getFechaNacimiento().matches("\\d{2}/\\d{2}/\\d{4}")) {
            listener.onFailure(new Exception("Formato de fecha inválido. Debe ser dd/MM/yyyy"));
            return;
        }

        // Delegamos en el DAO para registrar el usuario
        usuarioDao.registrarUsuario(usuario, new DaoUsuario.OnRegistroListener() {
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

    // Interfaz para el callback del registro
    public interface OnRegistroListener {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
    }

    // Método para recuperar contraseña, con validación previa del correo
    public void recuperarContrasenia(String email, final OnRecuperacionListener listener) {
        if (email == null || email.trim().isEmpty()){
            listener.onFailure(new Exception("Debes ingresar un correo electrónico."));
            return;
        }
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

    // Interfaz para el callback de recuperación de contraseña
    public interface OnRecuperacionListener {
        void onSuccess();
        void onFailure(Exception exception);
    }

    public void actualizarPassword(String uid, String nuevaPassword, OnUpdatePasswordListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(uid)
                .update("contrasenia", nuevaPassword) // Cambia "contrasenia" por el nombre del campo correspondiente
                .addOnSuccessListener(aVoid -> listener.onSuccess())
                .addOnFailureListener(e -> listener.onFailure(e));
    }

    public interface OnUpdatePasswordListener {
        void onSuccess();
        void onFailure(Exception e);
    }

}

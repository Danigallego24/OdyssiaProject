package com.example.odyssiaproject.ui.ajustes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PerfilActivity extends DialogFragment {

    private OnNombreCambiadoListener listener;

    public interface OnNombreCambiadoListener {
        void onNombreCambiado(String nuevoNombre);
    }

    public void setOnNombreCambiadoListener(OnNombreCambiadoListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Editar Nombre de Perfil");

        final EditText input = new EditText(requireActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Ingrese nuevo nombre");

        builder.setView(input);
        builder.setPositiveButton("Guardar", (dialog, which) -> {
            if (listener != null) {
                listener.onNombreCambiado(input.getText().toString().trim());
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        return builder.create();
    }
}
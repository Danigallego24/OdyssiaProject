package com.example.odyssiaproject.ui.ajustes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CambioPasswordActivity extends DialogFragment {

    public interface OnPasswordCambiadaListener {
        void onPasswordCambiada(String nuevaPassword);
    }

    private OnPasswordCambiadaListener listener;

    public void setOnPasswordCambiadaListener(OnPasswordCambiadaListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Cambiar Contraseña");

        final EditText input = new EditText(requireActivity());
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setHint("Ingrese nueva contraseña");

        builder.setView(input);
        builder.setPositiveButton("Guardar", (dialog, which) -> {
            if (listener != null) {
                listener.onPasswordCambiada(input.getText().toString().trim());
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        return builder.create();
    }
}
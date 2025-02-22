package com.example.odyssiaproject.ui.ajustes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfigViewModel extends ViewModel {

    private final MutableLiveData<Boolean> notificacionesActivas;

    public ConfigViewModel() {
        notificacionesActivas = new MutableLiveData<>(false);
    }

    public LiveData<Boolean> getNotificacionesActivas() {
        return notificacionesActivas;
    }

    public void cambiarEstadoNotificaciones(boolean estado) {
        notificacionesActivas.setValue(estado);
    }
}

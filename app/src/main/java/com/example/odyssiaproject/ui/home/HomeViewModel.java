package com.example.odyssiaproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<String>();
        mText.setValue("Bienvenido al Inicio");
    }

    public LiveData<String> getText() {
        return mText;
    }
}


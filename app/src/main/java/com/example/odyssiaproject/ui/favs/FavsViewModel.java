package com.example.odyssiaproject.ui.favs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavsViewModel() {
        mText = new MutableLiveData<String>();
        mText.setValue("Bienvenido a la galeria de favoritos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

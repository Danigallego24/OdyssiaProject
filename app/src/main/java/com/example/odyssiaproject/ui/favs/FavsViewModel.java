package com.example.odyssiaproject.ui.favs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FavsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fav fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

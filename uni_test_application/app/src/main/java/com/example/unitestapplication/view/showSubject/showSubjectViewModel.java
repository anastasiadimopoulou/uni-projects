package com.example.unitestapplication.view.showSubject;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class showSubjectViewModel extends ViewModel {

    private showSubjectPresenter presenter;

    public showSubjectViewModel() {
        // Initialize the presenter here
        this.presenter = new showSubjectPresenter();
    }

    public showSubjectPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("SubjectSearchVM", "onCleared");
        // release resources
    }
}
package com.example.unitestapplication.view.Subject.Search;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.DAO.SubjectDAO ;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory ;

public class SubjectSearchViewModel extends ViewModel {

    private SubjectSearchPresenter presenter;

    public SubjectSearchViewModel() {
        presenter = new SubjectSearchPresenter();
        SubjectDAO subjectDAO = new SubjectDAOMemory();  // Ή το κατάλληλο DAO
        presenter.setBookDAO(subjectDAO);
    }

    public SubjectSearchPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}


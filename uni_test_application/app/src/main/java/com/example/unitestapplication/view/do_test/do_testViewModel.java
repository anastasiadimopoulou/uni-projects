package com.example.unitestapplication.view.do_test;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;

public class do_testViewModel extends ViewModel {

    private do_testPresenter presenter;

    public do_testPresenter getPresenter() {
        if (presenter == null) {
            presenter = new do_testPresenter();
        }
        return presenter;
    }

}



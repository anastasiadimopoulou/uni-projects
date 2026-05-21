package com.example.unitestapplication.view.Subject.Monitor.showsuccess;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.view.do_test.do_testPresenter;

public class showsuccessViewModel extends ViewModel {
    private showsuccessPresenter presenter;

    public showsuccessPresenter getPresenter() {
        if (presenter == null) {
            presenter = new showsuccessPresenter(); // Αρχικοποίηση του presenter αν δεν υπάρχει
        }
        return presenter;
    }

    public showsuccessViewModel(){
        do_testPresenter do_testPresenter = new do_testPresenter(new QuestionDAOmemory());
    }
}

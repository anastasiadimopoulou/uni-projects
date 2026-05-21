package com.example.unitestapplication.view.showScore;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.view.do_test.do_testPresenter;

public class showScoreViewModel extends ViewModel {
    private showScorePresenter presenter;

    public showScorePresenter getPresenter() {
        if (presenter == null) {
            presenter = new showScorePresenter();
        }
        return presenter;
    }

    public showScoreViewModel(){
        do_testPresenter do_testPresenter = new do_testPresenter(new QuestionDAOmemory());
    }
}

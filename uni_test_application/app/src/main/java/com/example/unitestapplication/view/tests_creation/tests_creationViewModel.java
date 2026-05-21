package com.example.unitestapplication.view.tests_creation;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;

public class tests_creationViewModel extends ViewModel {
    //PI
    private tests_creationPresenter test_creation_presenter=new tests_creationPresenter();

    public tests_creationPresenter getPresenter(){
        return test_creation_presenter;
    }

    public tests_creationViewModel(){
        test_creation_presenter = new tests_creationPresenter(new QuestionDAOmemory(), new SubjectDAOMemory(),new TestsDAOMemory());
    }
}

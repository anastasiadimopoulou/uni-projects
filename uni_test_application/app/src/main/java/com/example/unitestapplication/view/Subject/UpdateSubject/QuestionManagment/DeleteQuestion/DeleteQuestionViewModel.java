package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.Search.SubjectSearchPresenter;

public class DeleteQuestionViewModel extends ViewModel {
    private DeleteQuestionPresenter presenter;
    public DeleteQuestionViewModel()
    {
        presenter = new DeleteQuestionPresenter();
        QuestionDAO questionDAO = new QuestionDAOmemory();  // Ή το κατάλληλο DAO
        presenter.setQuestionDao(questionDAO);
    }
    public DeleteQuestionPresenter getPresenter() {
        return presenter;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("DeleteQuestionVM", "onCleared");
        // release resources
    }
}
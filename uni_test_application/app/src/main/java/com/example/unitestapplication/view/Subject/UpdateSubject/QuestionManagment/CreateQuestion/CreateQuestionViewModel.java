package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectPresenter;

public class CreateQuestionViewModel extends ViewModel {
    private CreateQuestionPresenter CreateQuestionPresenter;
    public CreateQuestionPresenter getPresenter(){return CreateQuestionPresenter;}
    public CreateQuestionViewModel(){CreateQuestionPresenter= new CreateQuestionPresenter(new UserDAOMemory(), new QuestionDAOmemory(), new SubjectDAOMemory());}

}

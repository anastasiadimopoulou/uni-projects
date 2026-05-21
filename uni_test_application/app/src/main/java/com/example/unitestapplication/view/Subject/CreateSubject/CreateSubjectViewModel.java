package com.example.unitestapplication.view.Subject.CreateSubject;


import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.DAO.StudentDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.StudentDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;


public class CreateSubjectViewModel extends ViewModel {
    private CreateSubjectPresenter CreateSubjectPresenter;
    public CreateSubjectPresenter getPresenter(){return CreateSubjectPresenter;}
    public CreateSubjectViewModel(){CreateSubjectPresenter= new CreateSubjectPresenter(new UserDAOMemory(), new QuestionDAOmemory(), new SubjectDAOMemory());}


    }

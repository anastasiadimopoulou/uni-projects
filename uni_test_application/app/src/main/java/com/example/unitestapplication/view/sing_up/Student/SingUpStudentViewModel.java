package com.example.unitestapplication.view.sing_up.Student;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.DAO.StudentDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.StudentDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;

public class SingUpStudentViewModel  extends  ViewModel{
    private SingUpStudentPresenter SingUpStudentPresenter;

    /**
     * GETS SING UP STUDENT PRESENTER
     * @return SingUpStudentPresenter
     */
    public SingUpStudentPresenter getPresenter(){return SingUpStudentPresenter;}

    /**
     * CONSTRUCTOR SING UP  STUDENT VIEW MODEL
     * creates SING UP STUDENT PRESENTER
     */
    public SingUpStudentViewModel(){SingUpStudentPresenter= new SingUpStudentPresenter(new UserDAOMemory(), new StudentDAOMemory(),new QuestionDAOmemory())
    ;}
}

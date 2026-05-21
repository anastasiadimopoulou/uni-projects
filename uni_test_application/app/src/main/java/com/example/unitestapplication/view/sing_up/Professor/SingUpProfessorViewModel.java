package com.example.unitestapplication.view.sing_up.Professor;
import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.memoryDAO.ProfessorDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;
import com.example.unitestapplication.view.log_in.LogInPresenter;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentView;

public class SingUpProfessorViewModel  extends ViewModel {
    private SingUpProfessorPresenter SingUpProfessorPresenter;

    /**
     * GET SING UP PROFESSOR PRESENTER
     * @return SingUpProfessorPresenter
     */
    public SingUpProfessorPresenter getPresenter(){return SingUpProfessorPresenter;}

    /**
     * CONSTRUCTOR SING UP PROFESSOR VIEW MODEL
     * creates SING UP PROFESSOR PRESENTER
     */
    public SingUpProfessorViewModel(){SingUpProfessorPresenter= new SingUpProfessorPresenter(new UserDAOMemory(),new ProfessorDAOMemory());}
}

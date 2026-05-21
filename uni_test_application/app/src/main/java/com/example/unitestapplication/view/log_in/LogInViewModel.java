package com.example.unitestapplication.view.log_in;

import androidx.lifecycle.ViewModel;

import com.example.unitestapplication.memoryDAO.ProfessorDAOMemory;
import com.example.unitestapplication.memoryDAO.StudentDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;

public class LogInViewModel  extends ViewModel {
    private LogInPresenter logInPresenter;

    /**
     * Gets LOG IN PRESENTER
     * @return LogInPresenter
     */
    public LogInPresenter getPresenter(){return logInPresenter;}

    /**
     * CONSTRUCTOR LOG IN VIEW MODEL
     * creates log in presenter
     *
     */
    public LogInViewModel(){logInPresenter= new LogInPresenter(new UserDAOMemory(),new ProfessorDAOMemory(),new StudentDAOMemory());}
}

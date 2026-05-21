package com.example.unitestapplication.view.showScore;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;
import com.example.unitestapplication.view.do_test.do_testView;

public class showScorePresenter {

    private TestsDAO testDAO;

    private showScoreView view;

    public showScorePresenter(){};


    public showScoreView  getView(){
        return view;
    }


    public void setView(showScoreView view){
        this.view=view;
    }

    public void goToBasic_student_menu()
    {

        view.openbasicstudentmenu();
    }
}

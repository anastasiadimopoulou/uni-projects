package com.example.unitestapplication.view.Subject.Monitor.showsuccess;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;
import com.example.unitestapplication.view.do_test.do_testView;

public class showsuccessPresenter {

    private TestsDAO testDAO;

    private showsuccessView view;

    public showsuccessPresenter(){};


    public TestsDAO getTestsDAO() {
        return testDAO;
    }

    public void setTestsDAO(TestsDAO testDAO) {
        this.testDAO = testDAO;
    }


    public showsuccessView  getView(){
        return view;
    }
/*
    public showsuccessPresenter(TestsDAOMemory testDAOmemory) {
        this.testDAO= testDAO;
    }*/

    public void setView(showsuccessView view){
        this.view=view;
    }

    public void goToBasic_professor_menu()
    {

        view.openbasicprofessormenu();
    }
}

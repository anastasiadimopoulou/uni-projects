package com.example.unitestapplication.view.do_test;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;

public class do_testPresenter {

    private do_testView view;

    private QuestionDAO questionDAO;

    public do_testPresenter(){};


    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }
    public do_testPresenter(QuestionDAOmemory questionDAOmemory) {
        this.questionDAO= questionDAO;
    }


    public void setView(do_testView view){
        this.view=view;
    }

    public do_testView  getView(){
        return view;
    }



}

package com.example.unitestapplication.view.showSubject;

import com.example.unitestapplication.DAO.SubjectDAO ;
import com.example.unitestapplication.domain.Subject ;
import com.example.unitestapplication.view.showSubject.showSubjectView;

import java.util.HashSet;
import java.util.Set;
public class showSubjectPresenter {
    private SubjectDAO subjectDAO;


    private showSubjectView view;

    public showSubjectPresenter() {
    }
    public SubjectDAO getBookDAO() {
        return subjectDAO;
    }

    public void setBookDAO(SubjectDAO bookDAO) {
        this.subjectDAO = bookDAO;
    }

    public void setView(showSubjectView view) {
        this.view = view;
    }


}
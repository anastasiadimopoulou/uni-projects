package com.example.unitestapplication.view.Subject.Search;

import android.util.Log;

import com.example.unitestapplication.DAO.SubjectDAO ;
import com.example.unitestapplication.domain.Subject ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class SubjectSearchPresenter {
    private SubjectDAO subjectDAO;
    private SubjectSearchView view;
    private ArrayList<Subject> searchResult = new ArrayList<>();
    public SubjectSearchPresenter() {
    }
    public SubjectDAO getBookDAO() {
        return subjectDAO;
    }

    public void setBookDAO(SubjectDAO bookDAO) {
        this.subjectDAO = bookDAO;
    }

    public void setView(SubjectSearchView view) {
        this.view = view;
    }
    public SubjectSearchView getSubjectSearchView(){return view;}


    /**
     * finds all subjects created by the same id using professors id
     * @param professorid
     * @return ArrayList<Subject>
     */

    public ArrayList<Subject> search( int professorid)
    {

        ArrayList<Subject> searchResult = new ArrayList<>();  // Δημιουργία νέας λίστας κάθε φορά
        for (Subject subject : this.getBookDAO().findAll()) {
            if (subject.getProfid() == professorid) {
                searchResult.add(subject);
            }
        }

        return searchResult;

    }


}

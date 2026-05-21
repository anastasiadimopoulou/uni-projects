package com.example.unitestapplication.view.Subject.UpdateSubject;
import com.example.unitestapplication.DAO.SubjectDAO ;
import com.example.unitestapplication.domain.Subject ;
import java.util.HashSet;
import java.util.Set;
public class SubjectUpdatePresenter {
    private SubjectDAO subjectDAO;
    private SubjectUpdateView view;

    public SubjectUpdatePresenter() {
    }

    public SubjectDAO getBookDAO() {
        return subjectDAO;
    }

    public void setBookDAO(SubjectDAO bookDAO) {
        this.subjectDAO = bookDAO;
    }

    public void setView(SubjectUpdateView view) {
        this.view = (SubjectUpdateView) view;
    }


}

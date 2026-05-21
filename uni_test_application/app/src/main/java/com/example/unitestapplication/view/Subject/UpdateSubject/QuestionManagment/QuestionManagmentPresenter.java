package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.view.Subject.UpdateSubject.SubjectUpdateView;
import java.util.HashSet;
import java.util.Set;
public class QuestionManagmentPresenter {
    private SubjectDAO subjectDAO;
    private SubjectUpdateView view;
    public QuestionManagmentPresenter() {
    }
    public SubjectDAO getBookDAO() {
        return subjectDAO;
    }
    public void setBookDAO(SubjectDAO subDAO) {
        this.subjectDAO = subDAO;
    }

    public void setView(SubjectUpdateView view) {
        this.view = (SubjectUpdateView) view;
    }

}

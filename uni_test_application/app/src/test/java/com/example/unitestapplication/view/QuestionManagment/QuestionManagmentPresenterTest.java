package com.example.unitestapplication.view.QuestionManagment;

import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.QuestionManagmentPresenter;
import com.example.unitestapplication.view.Subject.UpdateSubject.SubjectUpdateView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.Set;

public class QuestionManagmentPresenterTest {

    private QuestionManagmentPresenter presenter;
    private SubjectDAO subjectDAO;

    @Before
    public void setUp() {
        presenter = new QuestionManagmentPresenter();
        subjectDAO = new SubjectDAOMemory();
        presenter.setBookDAO(subjectDAO);

        subjectDAO.save(new Subject("Math", 1, 1,55));
        subjectDAO.save(new Subject("Physics", 2, 1,75));
        subjectDAO.save(new Subject("Chemistry", 3, 2,89));
    }

    @After
    public void tearDown() {
        subjectDAO.deleteAll();
        System.out.println("Cleaned up after test.");
    }

    @Test
    public void testSetAndGetBookDAO() {

        Assert.assertEquals(subjectDAO, presenter.getBookDAO());
    }

    @Test
    public void testSetView() {

        SubjectUpdateView view = new SubjectUpdateView() {
        };

        // Set the view
        presenter.setView(view);
    }

}

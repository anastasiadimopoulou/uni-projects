package com.example.unitestapplication.view.showSubject;

import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.showSubject.showSubjectPresenter;
import com.example.unitestapplication.view.showSubject.showSubjectView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class showSubjectPresenterTest {

    private showSubjectPresenter presenter;
    private SubjectDAO subjectDAO;

    @Before
    public void setUp() {
        presenter = new showSubjectPresenter();
        subjectDAO = new SubjectDAOMemory();
        presenter.setBookDAO(subjectDAO);

        subjectDAO.save(new Subject("Math", 1, 1,54));
        subjectDAO.save(new Subject("Physics", 2, 1,4));
        subjectDAO.save(new Subject("Chemistry", 3, 2,5));
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
        showSubjectView mockView = new showSubjectView() {
        };

        presenter.setView(mockView);


    }



}

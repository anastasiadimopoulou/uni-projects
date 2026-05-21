package com.example.unitestapplication.view.SearchSubject;

import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.Search.SubjectSearchPresenter;
import com.example.unitestapplication.view.Subject.Search.SubjectSearchView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

public class SearchSubjectPresenterTest {
    private SubjectSearchPresenter presenter;
    private SubjectDAO subjectDAO;
    private SubjectSearchView view;

    @Before
    public void setUp() {
        presenter = new SubjectSearchPresenter();
        subjectDAO = new SubjectDAOMemory();
        presenter.setBookDAO(subjectDAO);

        // Add sample subjects to the DAO
        subjectDAO.save(new Subject("Math", 1, 1,45));
        subjectDAO.save(new Subject("Physics", 2, 1,96));
        subjectDAO.save(new Subject("Chemistry", 3, 2,5));
    }
    @After
    public void tearDown() {
        // Clear the subjectDAO after each test to ensure isolation
        subjectDAO.deleteAll();
        System.out.println("Cleaned up after test.");
    }


    @Test
    public void testSearch() {

        ArrayList <Subject> result = presenter.search(96);

        for (Subject subject : result) {
            Assert.assertEquals(2, subject.getid());
        }
    }




    @Test
    public void testSetAndGetBookDAO() {
        SubjectDAO subjectDAO = new SubjectDAOMemory();

        // Set the DAO
        presenter.setBookDAO(subjectDAO);

        Assert.assertEquals(subjectDAO, presenter.getBookDAO());
    }

    @Test
    public void testSetView() {
        SubjectSearchView mockView = new SubjectSearchView() {
        };

        // Set the view
        presenter.setView(mockView);

       SubjectSearchView retrievedView = presenter.getSubjectSearchView(); // Assuming 'view' is made accessible for testing

        Assert.assertEquals(mockView, retrievedView);
    }
}

package com.example.unitestapplication.view.Listsubjects;

import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.Monitor.ListsubjectsPresenter;
import com.example.unitestapplication.view.Subject.Monitor.ListsubjectsView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;


public class ListsubjectsPresenterTest {

    private ListsubjectsPresenter presenter;
    private SubjectDAOMemory subjectDAOMemory;

    @Before
    public void setUp() {
        presenter = new ListsubjectsPresenter();
        subjectDAOMemory = new SubjectDAOMemory();
        presenter.setsubjectDAO(subjectDAOMemory);
    }
    @Test
    public void testSetView() {

        ListsubjectsView dummyView = new ListsubjectsView() {};
        ListsubjectsPresenter presenter = new ListsubjectsPresenter();
        presenter.setView(dummyView);
        assertEquals(dummyView, presenter.getView());  // Assuming getView() method exists
    }
    @Test
    public void testSetAndGetSubjectDAO() {
        assertNotNull(presenter.getBookDAO());
        assertEquals(subjectDAOMemory, presenter.getBookDAO());
    }

    @Test
    public void testSearchWithEmptyParameters() {
        subjectDAOMemory.save(new Subject("AI", 2, 2, 2));
        subjectDAOMemory.save(new Subject("Networks", 1, 1, 1));

        presenter.search("", 0);
        Set<Subject> result = presenter.getSearchResult();

        assertEquals(9, result.size());
    }

    @Test
    public void testSearchByName() {
        Subject maths2 = new Subject("Maths2", 1, 1, 1);
        subjectDAOMemory.save(maths2);
        subjectDAOMemory.save(new Subject("Science1", 2, 2, 2));

        presenter.search("Maths2", 1);
        Set<Subject> result = presenter.getSearchResult();

        assertEquals(1, result.size());
        assertTrue(result.contains(maths2));
    }


    @Test
    public void testSearchById() {
        Subject science = new Subject("Science", 2, 2, 2);
        Subject math = new Subject("Math", 1, 1, 1);

        subjectDAOMemory.save(math);
        subjectDAOMemory.save(science);

        presenter.search("", 1);
        Set<Subject> result = presenter.getSearchResult();

        assertEquals(1, result.size());
        assertTrue(result.contains(math));  // Changed to assert math is in the result
    }

    @Test
    public void testSearchByNameAndId() {
        Subject maths3 =  new Subject("Maths3",1,1,1);
        subjectDAOMemory.save(maths3);
        subjectDAOMemory.save(new Subject("Science", 2,2,2));

        presenter.search("Maths3", 1);
        Set<Subject> result = presenter.getSearchResult();

        assertEquals(1, result.size());
        assertTrue(result.contains(maths3));
    }
    @Test
    public void testSearchByNameAndIdNoMatch() {
        subjectDAOMemory.save(new Subject("Math", 1, 1, 1));
        subjectDAOMemory.save(new Subject("Science", 2, 2, 2));

        presenter.search("Math", 2);  // No subject with name "Math" and ID 2
        Set<Subject> result = presenter.getSearchResult();
        assertTrue(result.isEmpty());
    }


    @Test
    public void testSearchByNonExistentId() {
        subjectDAOMemory.save(new Subject("Math",1,1,1));

        presenter.search("", 999);
        Set<Subject> result = presenter.getSearchResult();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchByNonExistentName() {
        subjectDAOMemory.save(new Subject("Math",1,1,1));

        presenter.search("History", 0);
        Set<Subject> result = presenter.getSearchResult();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteAllSubjects() {
        subjectDAOMemory.save(new Subject("Math",1,1,1));
        subjectDAOMemory.save(new Subject("Science", 2,2,2));

        subjectDAOMemory.deleteAll();
        presenter.search("", 0);

        Set<Subject> result = presenter.getSearchResult();
        assertTrue(result.isEmpty());
    }

}

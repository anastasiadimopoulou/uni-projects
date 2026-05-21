package com.example.unitestapplication.view.do_test;

import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.view.do_test.do_testPresenter;
import com.example.unitestapplication.view.do_test.do_testView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class do_testPresenterTest {

    private do_testPresenter presenter;
    private QuestionDAOmemory questionDAO;
    private do_testView view;

    @Before
    public void setUp() {

        questionDAO = new QuestionDAOmemory();
        presenter = new do_testPresenter(questionDAO);
        view = new do_testView() {
            @Override
            public void showErrorMessage(String title, String message) {
                System.out.println("Error: " + title + " - " + message);
            }
        };
    }

    @Test
    public void testdo_testPresenter() {
        assertNotNull(presenter);
        assertNull(presenter.getView());
        assertNull(presenter.getQuestionDAO());
    }

    @Test
    public void testSetQuestionDAO() {
        presenter.setQuestionDAO(questionDAO);
        assertEquals( questionDAO, presenter.getQuestionDAO());
    }

    @Test
    public void testGetQuestionDAO() {
        presenter.setQuestionDAO(questionDAO);
        assertEquals(questionDAO, presenter.getQuestionDAO());
    }

    @Test
    public void testSetView() {

        presenter.setView(view);
        //assertNotNull("The view should not be null after being set.", presenter.getView());
        assertEquals( view, presenter.getView());
    }

    @Test
    public void testGetNULLView() {
        assertNull( presenter.getView());
    }



    @Test
    public void testGetView() {

        presenter.setView(view);
        do_testView testView = presenter.getView();
        assertSame( view, testView);
    }

    @Test
    public void test_do_testPresenter(){
        presenter = new do_testPresenter();
        assertNotNull(presenter);
    }
}

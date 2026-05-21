package com.example.unitestapplication.view.showsuccess;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;
import com.example.unitestapplication.view.Subject.Monitor.showsuccess.showsuccessPresenter;
import com.example.unitestapplication.view.Subject.Monitor.showsuccess.showsuccessView;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class showsuccessPresenterTest {

    private showsuccessPresenter presenter;
    private TestsDAOMemory testDAO;
    private DummyShowsuccessView dummyView;


    private class DummyShowsuccessView implements showsuccessView {
        private boolean menuOpened = false;

        @Override
        public void openbasicprofessormenu() {
            menuOpened = true;
        }

        public boolean isMenuOpened() {
            return menuOpened;
        }
    }

    @Before
    public void setUp() {
        testDAO = new TestsDAOMemory();
        presenter = new showsuccessPresenter();
        dummyView = new DummyShowsuccessView();
    }

    @Test
    public void testSetAndGetTestsDAO() {
        presenter.setTestsDAO(testDAO);
        assertEquals(testDAO, presenter.getTestsDAO());
    }

    @Test
    public void testSetAndGetView() {
        presenter.setView(dummyView);
        assertEquals(dummyView, presenter.getView());
    }

    @Test
    public void testGoToBasicProfessorMenu() {
        presenter.setView(dummyView);
        presenter.goToBasic_professor_menu();
        assertTrue(dummyView.isMenuOpened());
    }
    
    /*@Test
    public void testConstructorWithTestsDAOMemory() {
        TestsDAOMemory testDAOmemory = new TestsDAOMemory();
        showsuccessPresenter presenter = new showsuccessPresenter(testDAOmemory);

        assertEquals(testDAOmemory, presenter.getTestsDAO());
    }*/


}

package com.example.unitestapplication.view.showScore;

import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.view.do_test.do_testPresenter;
import com.example.unitestapplication.view.do_test.do_testView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class showScorePresenterTest {

    private showScorePresenter presenter; // Class under test
    private showScoreViewStub View;  // Concrete implementation of showScoreView for testing

    @Before
    public void setUp() {
        presenter = new showScorePresenter();   // Initialize the presenter
        View = new showScoreViewStub() ;

        presenter.setView( View);           // Set the test view in the presenter

    }

    @Test
    public void testSetAndGetView() {
        // Verify that the set view is correctly stored
        assertSame(View, presenter.getView());
    }
    @Test
    public void testgoToBasic_student_menu(){
        presenter.goToBasic_student_menu();
        assertEquals(1,View.GETgoToBasic_student_menuClick());
    }


   /* @Test
    public void testGoToBasicStudentMenu() {
        // Call the method under test
        presenter.goToBasic_student_menu();

        // Verify that the view's method was triggered
        assertTrue(testView.isBasicStudentMenuOpened, "The basic student menu should have been opened");
    }

    @Test
    public void testGoToBasicStudentMenuWithNoView() {
        // Set the view to null
        presenter.setView(null);

        // Calling the method should not throw an exception
        assertDoesNotThrow(() -> presenter.goToBasic_student_menu(), "The method should handle null views gracefully");
    }
 */

    

}

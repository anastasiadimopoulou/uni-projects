package com.example.unitestapplication.view.DeleteQuestion;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.CreateQuestion.CreateQuestionViewStub;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionPresenter;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionView;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion.DeleteQuestionPresenter;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion.DeleteQuestionView;

import java.util.Set;
import java.util.HashSet;

public class DeleteQuestionPresenterTest {
    private DeleteQuestionView view;
    private DeleteQuestionPresenter presenter;
    private QuestionDAO questionDAO;
    private CreateQuestionViewStub viewStub;


    @Before
    public void setUp() {
        presenter = new DeleteQuestionPresenter();
        questionDAO = new QuestionDAOmemory();  // Using the in-memory DAO for testing
        presenter.setQuestionDao(questionDAO);

        // Initialize the view with a simple stub implementation
        view = new DeleteQuestionViewStub();
        presenter.setView(view);  // Set the initialized view

        setUpSampleData();  // Initialize sample questions
    }


    @Test
    public void testSetView() {
        // Act
        presenter.setView(view);

        // Assert
        assertEquals("The view should be correctly set", view, presenter.getview());
    }
    @Test
    public void testGetView_ReturnsCorrectView() {
        // Arrange
        DeleteQuestionViewStub viewStub = new DeleteQuestionViewStub();
        presenter.setView( viewStub);

        // Act
        DeleteQuestionView result = presenter.getview();

        // Assert
        assertNotNull("getView should return a non-null view", result);
        assertSame("getView should return the same view that was set", viewStub, result);
    }

    private void setUpSampleData() {
        // Adding some sample questions to the in-memory database using the updated constructor
        questionDAO.save(new Question("Label1", "Math Question", "Choice1", "Choice2", "Choice3", "Choice4", "A", true,50));
        questionDAO.save(new Question("Label2", "Science Question", "Choice1", "Choice2", "Choice3", "Choice4", "A", true,50));
        questionDAO.save(new Question("Label3", "Math Question", "Choice1", "Choice2", "Choice3", "Choice4", "A", true,50));
    }

    @After
    public void tearDown() {
        // Clean up after each test by clearing the static list in the QuestionDAOmemory class
        questionDAO.deleteAll();  // Clear all questions in the DAO
    }

    @Test
    public void testSearch_withEmptyId() {
        presenter.search( 0);
        Set<Question> result = presenter.getSearchResult();

        // Expecting all questions from DAO since both description and id are empty
        assertEquals(0, result.size()); // Assuming DAO has 3 questions for this test
    }




   @Test
    public void testSearch_withNoMatchingResults() {
        Set<Question> result=presenter.search( 999);

        assertEquals(0, result.size());
    }


    @Test
    public void testIsEmpty_withNull() {
        assertTrue(presenter.isEmpty(null));
    }

    @Test
    public void testIsEmpty_withEmptyString() {
        assertTrue(presenter.isEmpty(""));
    }

    @Test
    public void testIsEmpty_withNonEmptyString() {
        assertFalse(presenter.isEmpty("Math"));
    }
    @Test
    public void testGetQuestionDAO_ReturnsCorrectDAO() {
        // Arrange: Set a mock QuestionDAO to the presenter
        presenter.setQuestionDao(questionDAO);

        // Act: Call getQuestionDAO
        QuestionDAO result = presenter.getQuestionDAO();

        // Assert: Verify that the correct QuestionDAO is returned
        assertNotNull("QuestionDAO should not be null", result);
        assertSame("getQuestionDAO should return the same instance that was set", questionDAO, result);
    }

    @Test
    public void testGetQuestionSearchView_ReturnsCorrectView() {
        // Arrange: Set a mock DeleteQuestionView to the presenter
        presenter.setView(view);

        // Act: Call getQuestionSearchView
        DeleteQuestionView result = presenter.getQuestionSearchView();

        // Assert: Verify that the correct DeleteQuestionView is returned
        assertNotNull("DeleteQuestionView should not be null", result);
        assertSame("getQuestionSearchView should return the same instance that was set", view, result);
    }

    // No need for Fake QuestionDAO anymore, we are directly using QuestionDAOmemory
}

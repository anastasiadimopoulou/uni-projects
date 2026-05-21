package com.example.unitestapplication.view.tests_creation;

import org.junit.Before;
import org.junit.Test;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class tests_creationPresenterTest {

    private tests_creationPresenter presenter;
    private tests_creationViewStub viewStub;

    private QuestionDAO questionDAO;
    private SubjectDAO subjectDAO;
    private TestsDAO testsDAO;


    @Before
    public void setUp() {
        // Initialize in-memory DAOs
        questionDAO = new QuestionDAOmemory();
        testsDAO = new TestsDAOMemory();
        subjectDAO = new SubjectDAOMemory();

        // Add some dummy subjects to the subjectDAO
        subjectDAO.save(new Subject("Math", 1, 1, 1));
        subjectDAO.save(new Subject("Science", 2, 2, 2));
        Question q1=new Question("easy", "the sky is", "blue", "red", "green", "pink", "A", true, 1);
        subjectDAO.findByid(1).get(0).addQuestion("easy", "the sky is", "blue", "red", "green", "pink", 1,"A", true,1);
        // Add 3 "easy" questions to the questionDAO
        questionDAO.save(new Question("easy", "the sky is", "blue", "red", "green", "pink", "A", true, 1));
        questionDAO.save(new Question("easy", "the water is", "blue", "red", "green", "pink", "A", true, 1));
        questionDAO.save(new Question("easy", "the grass is", "green", "blue", "red", "pink", "A", true, 1));

        // Add 2 "medium" questions
        questionDAO.save(new Question("medium", "the ocean is", "blue", "red", "green", "pink", "A", true, 1));
        questionDAO.save(new Question("medium", "the moon is", "blue", "red", "green", "pink", "A", true, 2));

        // Add 1 "hard" question
        questionDAO.save(new Question("hard", "the moon is", "blue", "red", "green", "pink", "A", true, 2));

        // Initialize the presenter and the view stub
        viewStub = new tests_creationViewStub();
        presenter = new tests_creationPresenter(questionDAO, (SubjectDAOMemory) subjectDAO, testsDAO);
        presenter.setView(viewStub);
    }
    @Test
    public void testVerification_MissingLevelAndQuantity() {
        viewStub.setlevel("");     // Empty level
        viewStub.setQuantity(0);   // Quantity set to 0

        int result = presenter.verification(1);

        assertEquals(-100, result);  // Expecting an invalid test ID (-100 means test creation failed)
        assertEquals("Error!", viewStub.getErrorTitle());
        assertEquals("Missing Fields", viewStub.getErrorMessage());
    }
    @Test
    public void testVerification_MissingLevel() {
        viewStub.setlevel("");     // Empty level
        viewStub.setQuantity(5);   // Valid quantity

        int result = presenter.verification(1);

        assertEquals(-100, result);
        assertEquals("Missing Fields.", viewStub.getErrorTitle());
        assertEquals("Insert level of difficulty", viewStub.getErrorMessage());
    }
    @Test
    public void testVerification_InvalidLevel() {
        viewStub.setlevel("extreme");  // Invalid level
        viewStub.setQuantity(5);       // Valid quantity

        int result = presenter.verification(1);

        assertEquals(-100, result);
        assertEquals("Wrong.", viewStub.getErrorTitle());
        assertEquals("Insert easy, medium or hard", viewStub.getErrorMessage());
    }

    @Test
    public void testVerification_InvalidQuantity() {
        viewStub.setlevel("easy");     // Valid level
        viewStub.setQuantity(0);       // Invalid quantity (<= 0)

        int result = presenter.verification(1);

        assertEquals(-100, result);
        assertEquals("Missing Fields.", viewStub.getErrorTitle());
        assertEquals("Insert amount of questions", viewStub.getErrorMessage());
    }
    @Test
    public void testGetIdCounter_DefaultValue() {
        // Verify that the initial value of idCounter is 0
        assertEquals(0, presenter.getidcounter());
    }
    @Test
    public void testDefaultConstructor() {
        // Verify that the presenter instance is not null
        presenter = new tests_creationPresenter();
        assertNotNull(presenter);

        // Verify that the view is null initially
        assertNull(presenter.getView());

        // Verify that idCounter is initialized to 0
        assertEquals(0, presenter.getidcounter());
    }
    @Test
    public void testGetIdCounter_AfterGeneratingUniqueId() {
        // Generate a few unique IDs and verify that idCounter increases accordingly
        presenter.generateUniqueId();
        presenter.generateUniqueId();
        presenter.generateUniqueId();

        // The idCounter should be 3 after three calls to generateUniqueId
        assertEquals(3, presenter.getidcounter());
    }
    @Test
    public void testCreateTests_NotEnoughQuestions() {
        // Setup the view with a quantity greater than available questions for the selected level
        viewStub.setQuantity(10);  // There are only 5 questions available in total
        viewStub.setlevel("easy");

        int subjectId = 1;  // Valid subject ID
        int testId = presenter.verification(subjectId);

        // Since there aren't enough questions for the "easy" level, the test creation should fail
        assertTrue(testId <= 0);  // Invalid test ID should be returned (non-positive)
        assertEquals("Invalid amount of questions", viewStub.getErrorTitle());
        assertEquals("Insert amount of questions", viewStub.getErrorMessage());
    }


    @Test

    public void testCreateTest_ValidWithEnoughQuestions() {
        // Set up valid quantity and level
        viewStub.setQuantity(1); // Requesting 3 "easy" questions
        viewStub.setlevel("easy");

        int subjectId = 1;  // Subject ID
        int testId = presenter.verification(subjectId);

        // Test should be created successfully
        assertNotEquals(-100, testId);
        assertEquals("Tests's Creation", viewStub.getSuccessTitle());
        assertEquals("Your test has been successfully created.", viewStub.getSuccessMessage());

        // Assert that the created test has exactly 3 questions
        List<Question> questions = testsDAO.findByid(testId).getListOfQuestions();
        assertEquals(1, questions.size());  // The test should have 3 questions
    }


    @Test
    public void testCreateTest_FixedLevel() {
        // Test if the level is set correctly
        viewStub.setlevel("medium");
        presenter.getView().setlevel("easy");

        // Assert that the level is set correctly
        assertEquals("easy", presenter.getView().getlevel());
    }

    @Test
    public void testCreateTests_Success_WithSystemTimeAsId() {
        // Set the view to create a test
        viewStub.setQuantity(1);
        viewStub.setlevel("hard");

        int subjectId = 1;  // Valid subject ID
        int testId = presenter.createTests(subjectId);

        // Ensure the test ID is a valid timestamp (positive number)
        assertTrue(testId > 0);
    }

    @Test
    public void testCreateTests_EmptyQuestionsList() {
        // Clear all questions in the DAO to simulate no questions available
        questionDAO.deleteAll();

        viewStub.setQuantity(1);  // Valid quantity
        viewStub.setlevel("medium");

        int subjectId = 1;  // Valid subject ID
        int testId = presenter.verification(subjectId);

        // No questions available, so test creation should fail
        assertTrue(testId <= 0);  // Invalid test ID should be returned (non-positive)
        assertEquals("Invalid amount of questions", viewStub.getErrorTitle());
        assertEquals("Insert amount of questions", viewStub.getErrorMessage());
    }

    // Add more tests for other methods as necessary
}

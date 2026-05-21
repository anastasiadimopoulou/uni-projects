package com.example.unitestapplication.view.CreateQuestion;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionPresenter;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionView;
//import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionViewStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;


public class CreateQuestionPresenterTest {

    private CreateQuestionPresenter presenter;
    private QuestionDAOmemory questionDAO;
    private SubjectDAOMemory subjectDAO;
    private UserDAO userDAO;
    private CreateQuestionView view;
    private CreateQuestionViewStub viewStub;







    @Before
    public void setUp() {
        questionDAO = new QuestionDAOmemory();
        subjectDAO = new SubjectDAOMemory();
        presenter = new CreateQuestionPresenter(null, questionDAO, subjectDAO);
        questionDAO.deleteAll();
    }
    @Test
    public void testConstructor_InitializesDependencies() {
        questionDAO = new QuestionDAOmemory();
        subjectDAO = new SubjectDAOMemory();
        presenter = new CreateQuestionPresenter(null, questionDAO, subjectDAO);
        // Assert that the presenter has correctly initialized its fields
        assertNotNull("QuestionDAO should be initialized", presenter.getQuestionDAO());
        assertNotNull("SubjectDAO should be initialized", presenter.getSubjectDAO());
        assertNull("UserDAO should be null", presenter.getUserDAO());
    }
    @Test
    public void testSetView() {
        // Act
        presenter.setView(view);

        // Assert
        assertEquals("The view should be correctly set", view, presenter.getView());
    }

    @Test
    public void testGetView_ReturnsCorrectView() {
        // Arrange
        viewStub = new CreateQuestionViewStub(); // Initialize the viewStub here
        presenter.setView(viewStub);

        // Act
        CreateQuestionView result = presenter.getView();

        // Assert
        assertNotNull("getView should return a non-null view", result);
        assertSame("getView should return the same view that was set", viewStub, result);
    }


    @Test
    public void testCreateQuestion_SuccessfulCreation() {
        // Arrange
        viewStub = new CreateQuestionViewStub();  // Initialize viewStub here
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Set the fields of the viewStub using setter methods
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("Choice 1");



        // Act: Call CreateQuestion method
        presenter.CreateQuestion(1);

        // Assert: Check if a question has been saved in the DAO
        List<Question> questions = questionDAO.findAll();
        assertEquals(1, questions.size());  // Ensure one question was created

        Question createdQuestion = questions.get(0);
        assertEquals("Sample description", createdQuestion.getDescription());
        assertEquals("1", createdQuestion.getLevel());
        assertEquals("Choice 1", createdQuestion.getChoice1());
        assertEquals("Choice 2", createdQuestion.getChoice2());
        assertEquals("Choice 3", createdQuestion.getChoice3());
        assertEquals("Choice 4", createdQuestion.getChoice4());
        assertEquals("Choice 1", createdQuestion.getProfAns());
    }
    @Test
    public void testVerification_AllFieldsEmpty() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Set all fields to empty using the stub
        viewStub.setdescripton("");
        viewStub.setlevel("");
        viewStub.setchoice1("");
        viewStub.setchoice2("");
        viewStub.setchoice3("");
        viewStub.setchoice4("");
        viewStub.setprofans("");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Check if the showErrorMessage method is called with the expected message
        // In this case, you may need to verify the console output or handle messages in another way
        // For instance, you can capture System.out using a ByteArrayOutputStream for verification
    }
    @Test
    public void testVerification_MissingDescription() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Missing description
        viewStub.setdescripton("");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("Choice 1");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Verify that the showErrorMessage method is called with the correct message
        // You can capture the output or handle the check similarly to other test cases
    }
    @Test
    public void testVerification_MissingLevel() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Missing level
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("Choice 1");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Verify that the showErrorMessage method is called with the correct message
    }
    @Test
    public void testVerification_CreatesQuestionWhenAllFieldsAreFilled() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: All fields are filled
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("easy");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("A");
        viewStub.setsubjectid(500);
        viewStub.setstate(true);
        viewStub.setsubjectid(50);
        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Check if the success message is printed and a question is created
        // Ensure that the question is created in the DAO
        assertEquals(1, questionDAO.findAll().size());  // Ensure a question has been created
    }
    @Test
    public void testVerification_Missingchoice1() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Missing level
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("1");
        viewStub.setchoice1("");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("Choice 1");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Verify that the showErrorMessage method is called with the correct message

        assertEquals(0, questionDAO.findAll().size());  // Ensure a question has been created
    }

    @Test
    public void testVerification_Missingchoice2() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Missing level
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("Choice 1");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Verify that the showErrorMessage method is called with the correct message

        assertEquals(0, questionDAO.findAll().size());  // Ensure a question has been created
    }
    @Test
    public void testVerification_Missingchoice3() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Missing level
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("Choice 1");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Verify that the showErrorMessage method is called with the correct message

        assertEquals(0, questionDAO.findAll().size());  // Ensure a question has been created
    }
    @Test
    public void testVerification_Missingchoice4() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Missing level
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("");
        viewStub.setprofans("Choice 1");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Verify that the showErrorMessage method is called with the correct message

        assertEquals(0, questionDAO.findAll().size());  // Ensure a question has been created
    }

    @Test
    public void testVerification_MissingProfAns() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        // Create the presenter with the stubs
        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);  // Set the viewStub to the presenter

        // Arrange: Missing level
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("");

        // Act: Call the verification method
        presenter.verification(1);

        // Assert: Verify that the showErrorMessage method is called with the correct message

        assertEquals(0, questionDAO.findAll().size());  // Ensure a question has been created
    }

    @Test
    public void testCreateQuestion_IdIncrementation() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: First question creation
        viewStub.setdescripton("Description 1");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("A");
        presenter.CreateQuestion(1);

        // Arrange: Second question creation
        viewStub.setdescripton("Description 2");
        presenter.CreateQuestion(1);

        // Assert: Ensure two questions are created with unique IDs
        List<Question> questions = questionDAO.findAll();
        assertEquals(2, questions.size());
        assertNotEquals(questions.get(0).getId(), questions.get(1).getId());
    }

    @Test
    public void testVerification_InvalidProfAns() {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid profans value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("1");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("E");

        // Act
        presenter.verification(1);

        // Assert
        assertEquals(0, questionDAO.findAll().size());
    }
    @Test
    public void testVerification_InvalidLevel()
    {
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid level value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("expert");
        viewStub.setchoice1("Choice 1");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("A");

        // Act
        presenter.verification(1);

        // Assert
        assertEquals(0, questionDAO.findAll().size());
    }
    @Test
    public void testGoToQuestionManagmentMenu() {
        viewStub = new CreateQuestionViewStub();
        presenter.setView(viewStub);

        // Act: Call the method to navigate to the menu
        presenter.goToQuestionManagmentMenu();

        // Assert: Verify that the openCreateDeleteQuest method was called
        assertTrue("openCreateDeleteQuest should be called", viewStub. isOpenCreateDeleteQuestCalled());
    }

    @Test
    public void testverificationMissingChoice1(){
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid level value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("easy");
        viewStub.setchoice1("");
        viewStub.setchoice2("Choice 2");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("A");
        // Act
        presenter.verification(1);

        assertEquals("Missing Fields.",viewStub.geErrorTitle());
        assertEquals("Complete choice1",viewStub.getErrorMessage());

    }

    @Test
    public void testverificationMissingChoice2(){
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid level value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("easy");
        viewStub.setchoice1("choice 1");
        viewStub.setchoice2("");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("A");
        // Act
        presenter.verification(1);

        assertEquals("Missing Fields.",viewStub.geErrorTitle());
        assertEquals("Complete choice2",viewStub.getErrorMessage());

    }


    @Test
    public void testverificationMissingChoice3(){
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid level value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("easy");
        viewStub.setchoice1("choice 1");
        viewStub.setchoice2("kkkkkk");
        viewStub.setchoice3("");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("A");
        // Act
        presenter.verification(1);

        assertEquals("Missing Fields.",viewStub.geErrorTitle());
        assertEquals("Complete choice3",viewStub.getErrorMessage());

    }
    @Test
    public void testverificationMissingChoice4(){
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid level value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("easy");
        viewStub.setchoice1("choice 1");
        viewStub.setchoice2("kkkkkkk");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("");
        viewStub.setprofans("A");
        // Act
        presenter.verification(1);

        assertEquals("Missing Fields.",viewStub.geErrorTitle());
        assertEquals("Complete choice4",viewStub.getErrorMessage());

    }
    @Test
    public void testverificationMissingprofans(){
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid level value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("easy");
        viewStub.setchoice1("choice 1");
        viewStub.setchoice2("kkkkkkk");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("");
        // Act
        presenter.verification(1);

        assertEquals("Missing Fields.",viewStub.geErrorTitle());
        assertEquals("Complete the right answer",viewStub.getErrorMessage());

    }
    @Test
    public void testverificationchecksRightAnswer(){
        viewStub = new CreateQuestionViewStub();
        questionDAO = new QuestionDAOmemory();

        presenter = new CreateQuestionPresenter(null, questionDAO, null);
        presenter.setView(viewStub);

        // Arrange: Invalid level value
        viewStub.setdescripton("Sample description");
        viewStub.setlevel("easy");
        viewStub.setchoice1("choice 1");
        viewStub.setchoice2("kkkkkkk");
        viewStub.setchoice3("Choice 3");
        viewStub.setchoice4("Choice 4");
        viewStub.setprofans("h");
        // Act
        presenter.verification(1);

        assertEquals("Invalid Answer",viewStub.geErrorTitle());
        assertEquals("The correct answer must be A, B, C, or D.",viewStub.getErrorMessage());

    }


}





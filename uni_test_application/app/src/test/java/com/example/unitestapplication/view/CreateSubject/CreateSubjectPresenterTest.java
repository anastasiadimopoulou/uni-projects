package com.example.unitestapplication.view.CreateSubject;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;
import com.example.unitestapplication.view.CreateSubject.CreateSubjectViewStub;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CreateSubjectPresenterTest {
    private CreateSubjectViewStub view;
    private SubjectDAOMemory subjectDAO;
    private QuestionDAOmemory questionDAO;
    private UserDAO userDAO;
    private CreateSubjectPresenter presenter;

    @Before
    public void setUp() {
        subjectDAO = new SubjectDAOMemory();
        questionDAO = new QuestionDAOmemory();
        userDAO =new UserDAOMemory();
        view = new CreateSubjectViewStub("", 0, 0,0);
        presenter = new CreateSubjectPresenter(userDAO, questionDAO, subjectDAO);
        presenter.setView(view);
    }

    @Test
    public void testSetAndGetView() {
        CreateSubjectViewStub res = (CreateSubjectViewStub) presenter.getView();
        Assert.assertEquals(view, res);
    }

    @Test
    public void testVerificationEmptyFields() {
        view.setname("");
        view.setid(0);
        view.setsemester(0);
        presenter.verification();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Missing Fields", view.getErrorMessage());
    }

    @Test
    public void testVerificationMissingName() {
        view.setname("");
        view.setid(1);
        view.setsemester(1);
        presenter.verification();
        Assert.assertEquals("Missing Fields.", view.getErrorTitle());
        Assert.assertEquals("Complete name", view.getErrorMessage());
    }

    @Test
    public void testVerificationMissingId() {
        view.setname("Math");
        view.setid(0);
        view.setsemester(1);
        presenter.verification();
        Assert.assertEquals("Missing Fields.", view.getErrorTitle());
        Assert.assertEquals("Complete academic ID", view.getErrorMessage());
    }

    @Test
    public void testVerificationMissingSemester()
    {
        view.setname("Math");
        view.setid(1);
        view.setsemester(0);
        presenter.verification();
        Assert.assertEquals("Missing Fields.", view.getErrorTitle());
        Assert.assertEquals("Complete academic semester", view.getErrorMessage());
    }

    @Test
    public void testCreateSubjectSuccess() {
        view.setname("Math");
        view.setid(1);
        view.setsemester(1);
        view.setsprofid(100);
        presenter.verification();
        boolean exists = subjectDAO.existsbyid(1, 100);
        //Subject savedSubject=new Subject();
        if (exists)
        {
           Subject savedSubject=subjectDAO.findByid(100).get(0);
            Assert.assertEquals("Math", savedSubject.getname());
            Assert.assertEquals(1, savedSubject.getid());
            Assert.assertEquals(1, savedSubject.getsemester());
            Assert.assertEquals(100,savedSubject.getProfid());
        }

    }

    @Test
    public void testNonUniqueId() {
        view.setname("Math");
        view.setid(1);
        view.setsemester(1);
        view.setsprofid(100);
        presenter.verification();
        view.setname("greeks");
        view.setid(100);
        view.setsemester(1);
        view.setsprofid(100);
        presenter.verification();
        Assert.assertEquals("Invalid id", view.getErrorTitle());
        Assert.assertEquals("Id already exists.", view.getErrorMessage());


    }

    @Test
    public void testGoToBasicProfessorMenu() {
        presenter.goToBasic_professor_menu();
        Assert.assertTrue(view.isBasicProfessorMenuOpened());
    }
}
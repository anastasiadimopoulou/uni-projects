package com.example.unitestapplication.view.log_in;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.unitestapplication.DAO.ProfessorDAO;
import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.StudentDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Professor;
import com.example.unitestapplication.domain.Student;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.domain.User;
import com.example.unitestapplication.memoryDAO.ProfessorDAOMemory;
import com.example.unitestapplication.memoryDAO.StudentDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class LogInPresenterTest {
        private UserDAO userDAO;
    private ProfessorDAO professorDAO;
    private StudentDAO studentDAO;
    private QuestionDAO questionDAO;
    private LogInViewStub view;



    @Test
    public void setgetViewTest(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO
        studentDAO = new StudentDAOMemory(); // Real implementation of StudentDAO
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
        studentDAO.save(user6);
        professorDAO.save(user7);

        view = new LogInViewStub("",""); // Real implementation of LogInView
        LogInPresenter presenter = new LogInPresenter(userDAO, professorDAO, studentDAO);
        presenter.setView(view);
        LogInViewStub res = (LogInViewStub)presenter.getView();
        //setUp();



        Assert.assertEquals(res,view);
    }
    @Test
    public  void VerificationemptyFieldsTest(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO
        studentDAO = new StudentDAOMemory(); // Real implementation of StudentDAO
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
        studentDAO.save(user6);
        professorDAO.save(user7);


        // Initialize the view and presenter with real objects
        view = new LogInViewStub("",""); // Real implementation of LogInView
        LogInPresenter presenter = new LogInPresenter(userDAO, professorDAO, studentDAO);
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.getErrorTitle(),"Error!");
        assertEquals("Missing Fields", view.getLastErrorMessage());







    }
    @Test
    public  void verificationinvalidUsernamePassword(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO
        studentDAO = new StudentDAOMemory(); // Real implementation of StudentDAO
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
        studentDAO.save(user6);
        professorDAO.save(user7);
        view = new LogInViewStub("pa","123456");
        LogInPresenter presenter = new LogInPresenter(userDAO, professorDAO, studentDAO);
        presenter.setView(view);
        presenter.verification();

        assertEquals("Login Error!",view.getErrorTitle());
        assertEquals("We couldn't find an account with that username and password. Please double-check and try again.",view.getLastErrorMessage());
    }
    @Test
    public  void verificationvalidUsernamePasswordStudent(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO
        studentDAO = new StudentDAOMemory(); // Real implementation of StudentDAO
        userDAO.save(user1);
        userDAO.save(user7);
        userDAO.save(user6);
        studentDAO.save(user6);
        professorDAO.save(user7);
        view = new LogInViewStub("pki","123456");
        LogInPresenter presenter = new LogInPresenter(userDAO, professorDAO, studentDAO);
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.getHomePageStudentClick(),1);}

    @Test
    public  void verificationvalidUsernamePasswordProfessor(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO
        studentDAO = new StudentDAOMemory(); // Real implementation of StudentDAO
        userDAO.save(user1);
        userDAO.save(user7);
        userDAO.save(user6);
        studentDAO.save(user6);
        professorDAO.save(user7);
        view = new LogInViewStub("kiki","123456");
        LogInPresenter presenter = new LogInPresenter(userDAO, professorDAO, studentDAO);
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.getHomePageProfessorClick(),1);}


    @Test
    public  void gotoSignUpStudentActivity(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO
        studentDAO = new StudentDAOMemory(); // Real implementation of StudentDAO
        userDAO.save(user1);
        userDAO.save(user7);
        userDAO.save(user6);
        studentDAO.save(user6);
        professorDAO.save(user7);
        view = new LogInViewStub("kiki","123456");
        LogInPresenter presenter = new LogInPresenter(userDAO, professorDAO, studentDAO);
        presenter.setView(view);
        presenter.goToSingupStudent();
        assertEquals(view.getSingUpStudentClick(),1);

    }
    @Test
    public  void gotoSignUpProfessorActivity(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO
        studentDAO = new StudentDAOMemory(); // Real implementation of StudentDAO
        userDAO.save(user1);
        userDAO.save(user7);
        userDAO.save(user6);
        studentDAO.save(user6);
        professorDAO.save(user7);
        view = new LogInViewStub("kiki","123456");
        LogInPresenter presenter = new LogInPresenter(userDAO, professorDAO, studentDAO);
        presenter.setView(view);
        presenter.goToSingupProfessor();
        assertEquals(view.getSingUpProfessorClick(),1);


    }





}

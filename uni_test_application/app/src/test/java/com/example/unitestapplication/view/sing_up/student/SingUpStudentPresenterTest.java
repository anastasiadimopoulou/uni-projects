package com.example.unitestapplication.view.sing_up.student;

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
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.StudentDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;
import com.example.unitestapplication.view.sing_up.Professor.SingUpProfessorPresenter;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentPresenter;
import com.example.unitestapplication.view.sing_up.professor.SingUpProfessorViewStub;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SingUpStudentPresenterTest {
    private SingUpStudentViewStub view;
    private UserDAO userDAO=null;
    private StudentDAO studentDAO;
    private QuestionDAO questionDAO;



    @Test
    public void SingUpStudentPresenterConstructor(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());

        userDAO = new UserDAOMemory(); //
        studentDAO = new StudentDAOMemory(); //
        questionDAO =new QuestionDAOmemory();
        userDAO.save(user1);
        userDAO.save(user6);
        studentDAO.save(user6);
        SingUpStudentViewStub view = new SingUpStudentViewStub("", "", "", "", "", "", "", -1);;
        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO,questionDAO);
        presenter.setView(view);
        assertEquals(presenter.getStudentDAO(),studentDAO);
        assertEquals(presenter.getUserDAO(),userDAO);
        assertEquals(presenter.getQuestionDAO(),questionDAO);


    }
    @Test
    public void setGetTest(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        studentDAO = new StudentDAOMemory(); //
        questionDAO =new QuestionDAOmemory();
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user6);
        studentDAO.save(user6);

        SingUpStudentViewStub view = new SingUpStudentViewStub("", "", "", "", "", "", "", -1);;
        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO,questionDAO);
        presenter.setView(view);




        Assert.assertEquals(presenter.getView(),view);

    }
    @Test
    public  void  GotologInTest(){
        User user1=new User("sia","markou","papaki","123456","IT","aueb","11-1-2000",123456);
        User user2=new User("maria","nikou","pa","1234568","IT","aueb","11-1-2090",23456);
        User user3=new User("sia","markou","phi","123456","IT","aueb","11-1-2000",1456);
        User user4=new User("sia","markou","pi","123456","IT","aueb","11-1-2000",156);
        User user5=new User("kamila","markou","papi","123456","IT","aueb","11-1-2000",16);
        User user6=new Student("sasa","markou","pki","123456","IT","aueb","11-1-2000",123,null,new ArrayList<Tests>());
        User user7=new Professor("ria","markou","kiki","123456","IT","aueb","11-1-2000",1,"epikouros");
        // Initialize the DAO objects with real instances
        userDAO = new UserDAOMemory(); // Real implementation of UserDAO
        studentDAO=new StudentDAOMemory();

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
        studentDAO.save(user6);

        SingUpStudentViewStub view = new SingUpStudentViewStub("", "", "", "", "", "", "", -1);;
        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO,questionDAO);
        presenter.setView(view);
        presenter.goToLogIn();



        Assert.assertEquals(view.getLoginClick(),1);


    }


    @Test
    public void VerificationmissingFieldsTest(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("", "", "", "", "", "", "", -1);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Error!", view.geErrorTitle());
        assertEquals("Missing Fields", view.getErrorMessage());

    }
    @Test
    public void Verificationmissingname(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("", "kkk", "kkkkk", "11", "d", "d", "dd", 888);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete name", view.getErrorMessage());

    }
    @Test
    public void Verificationmissingsurname(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kk", "", "kkkkk", "11", "d", "d", "dd", 888);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete surname", view.getErrorMessage());

    }
    @Test
    public void VerificationmissingUsername(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kkkk", "kkk", "", "11", "d", "d", "dd", 888);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete username", view.getErrorMessage());

    }
    @Test
    public void Verificationmissingbirthdate(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kk", "kkk", "kkkkk", "11", "d", "d", "", 888);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete birthday", view.getErrorMessage());

    }
    @Test
    public void VerificationmissingAcademicId(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kk", "kkk", "kkkkk", "11", "d", "d", "dd", -1);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete academic ID", view.getErrorMessage());

    }
    @Test
    public void Verificationmissigdepartment(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("k", "kkk", "kkkkk", "11", "", "d", "dd", 888);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete department", view.getErrorMessage());

    }
    @Test
    public void Verificationmissinguniversity(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("k", "kkk", "kkkkk", "11", "d", "", "dd", 888);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete university", view.getErrorMessage());

    }
    @Test
    public void Verificationmissingpassword(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kk", "kkk", "kkkkk", "", "d", "d", "dd", 888);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Missing Fields.", view.geErrorTitle());
        assertEquals("Complete password", view.getErrorMessage());

    }
    @Test
    public void VerificationFindByID(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kk", "kkk", "kkkkk", "11", "d", "d", "dd", 1);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Error!", view.geErrorTitle());
        assertEquals("Already exists account with this academic ID", view.getErrorMessage());

    }
    @Test
    public void VerificationFindByUsername(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kk", "kkk", "John", "11", "d", "d", "dd", 668909);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Error!", view.geErrorTitle());
        assertEquals("This username is already exists ,choose another", view.getErrorMessage());

    }
    @Test
    public void VerificationPasswordLength(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("giorgina", "kkk", "kdgdhgfjghdh", "11", "d", "d", "dd", 66);
        presenter.setView(view);

        presenter.verification();

        assertEquals("Error!", view.geErrorTitle());
        assertEquals("password must have at least 8 characters", view.getErrorMessage());

    }


    @Test
    public void CreatestudentAccountTest(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        studentDAO =new StudentDAOMemory();
        questionDAO=new QuestionDAOmemory();



        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        studentDAO.save(new Student("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, null,new ArrayList<Tests>()));

        SingUpStudentPresenter presenter = new SingUpStudentPresenter(userDAO, studentDAO, questionDAO);
        SingUpStudentViewStub view = new SingUpStudentViewStub("kk", "kkk", "kdgdhdh", "1777777770001", "d", "d", "dd", 789964348);
        presenter.setView(view);

        presenter.verification();
        assertEquals("Registration Successful",view.GetSuccessTitle());
        assertEquals("Your account has been successfully created. You can now log in and start using our services.",view.GetSuccessMessage());

    }

















}

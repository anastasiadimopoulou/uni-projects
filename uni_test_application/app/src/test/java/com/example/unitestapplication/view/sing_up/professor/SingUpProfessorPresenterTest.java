package com.example.unitestapplication.view.sing_up.professor;

import static org.junit.Assert.assertEquals;

import com.example.unitestapplication.DAO.ProfessorDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Professor;
import com.example.unitestapplication.domain.Student;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.domain.User;
import com.example.unitestapplication.memoryDAO.ProfessorDAOMemory;
import com.example.unitestapplication.memoryDAO.StudentDAOMemory;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;
import com.example.unitestapplication.view.log_in.LogInPresenter;
import com.example.unitestapplication.view.log_in.LogInViewStub;
import com.example.unitestapplication.view.sing_up.Professor.SingUpProfessorPresenter;
import com.example.unitestapplication.view.sing_up.Professor.SingUpProfessorView;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SingUpProfessorPresenterTest {
    private SingUpProfessorViewStub view;
    private UserDAO userDAO=null;
    private ProfessorDAO professorDAO;

   @Test
   public void SingUpProfessorPresenterConstructor(){
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
       userDAO.save(user1);
       userDAO.save(user7);
       userDAO.save(user6);
       professorDAO.save(user7);
       SingUpProfessorViewStub view = new SingUpProfessorViewStub("", "", "", "", "", "", "", -1, "");;
       SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
       assertEquals(presenter.getProfessorDAO(),professorDAO);
       assertEquals(presenter.getUserDAO(),userDAO);


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
       professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO

       userDAO.save(user1);
       userDAO.save(user2);
       userDAO.save(user3);
       professorDAO.save(user7);

       SingUpProfessorViewStub view = new SingUpProfessorViewStub("", "", "", "", "", "", "", -1, "");; // Real implementation of LogInView
       SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
       presenter.setView(view);
       SingUpProfessorViewStub res = (SingUpProfessorViewStub)presenter.getView();
       //setUp();



       Assert.assertEquals(res,view);

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
       professorDAO = new ProfessorDAOMemory(); // Real implementation of ProfessorDAO

       userDAO.save(user1);
       userDAO.save(user2);
       userDAO.save(user3);
       professorDAO.save(user7);

       SingUpProfessorViewStub view = new SingUpProfessorViewStub("", "", "", "", "", "", "", -1, ""); // Real implementation of LogInView
       SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
       presenter.setView(view);
       presenter.goToLogIn();



       Assert.assertEquals(view.getLoginClick(),1);


   }

    @Test
    public void VerificationmissingFieldsTest(){
    userDAO = new UserDAOMemory();  // Real DAO implementation
    professorDAO = new ProfessorDAOMemory();  // Real DAO implementation


        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

     SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("", "", "", "", "", "", "", -1, "");
        presenter.setView(view);

        presenter.verification();

        assertEquals("Error!", view.geErrorTitle());
        assertEquals("Missing Fields", view.getErrorMessage());

   }




    public void testVerification_existingProfessorId() {
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("Jane", "Doe", "kiki", "ps12377777", "Cs", "University X", "12-12-2002", 2, "p");
        presenter.setView(view);

        presenter.verification();

        assertEquals("Error!", view.geErrorTitle());
        assertEquals("Already exists account with this professor ID", view.getErrorMessage());
    }
    @Test
    public void verificationTestMissingname() {
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("", "Doe", "kiki", "ps12377777", "Cs", "University X", "12-12-2002", 2, "p");
        presenter.setView(view);
        presenter.verification();
    assertEquals(view.geErrorTitle(),"Missing Fields.");
   assertEquals(view.getErrorMessage(),"Complete name");
        }


        @Test
        public void VerificationTestMissingsurname(){
            userDAO = new UserDAOMemory();  // Real DAO implementation
            professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
            userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
            professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

            SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
            SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "", "kiki", "ps12377777", "Cs", "University X", "12-12-2002", 2, "p");
            presenter.setView(view);
            presenter.verification();
            assertEquals(view.geErrorTitle(),"Missing Fields.");
            assertEquals(view.getErrorMessage(),"Complete surname");

        }



    @Test
    public void VerificationTestmissingusername(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("kiki", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("giota", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "", "ps12377777", "Cs", "University X", "12-12-2002", 2, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Missing Fields.");
        assertEquals(view.getErrorMessage(),"Complete username");

    }
    @Test
    public void VerificationTestMissingpassword(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkkk", "kiki", "", "Cs", "University X", "12-12-2002", 2, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals("Missing Fields.",view.geErrorTitle());
        assertEquals("Complete password",view.getErrorMessage());

    }
    @Test
    public void VerificationTestMissingdepartment(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "kiki", "ps12377777", "", "University X", "12-12-2002", 2, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Missing Fields.");
        assertEquals(view.getErrorMessage(),"Complete department");

    }



    @Test
    public void VerificationTestMissinguniversity(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "kiki", "ps12377777", "IT", "", "12-12-2002", 2, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Missing Fields.");
        assertEquals(view.getErrorMessage(),"Complete university");

    }
    @Test
    public void VerificationTestMissingId(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "kiki", "ps12377777", "economics", "University X", "12-12-2002", -1, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Missing Fields.");
        assertEquals(view.getErrorMessage(),"Complete academic ID");

    }
    @Test
    public void VerificationTestMissingBirthDate(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "kiki", "ps12377777", "IT", "University X", "", 2, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Missing Fields.");
        assertEquals(view.getErrorMessage(),"Complete birthday");

    }
    @Test
    public void VerificationTestMissingPosition(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "kiki", "ps12377777", "It", "University X", "12-12-2002", 2, "");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Missing Fields.");
        assertEquals(view.getErrorMessage(),"Complete Academic position");

    }
    @Test
    public void VerificationTestFindByID(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "kiki", "ps12377777", "It", "University X", "12-12-2002", 2, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Error!");
        assertEquals(view.getErrorMessage(),"Already exists account with this professor ID");

    }
    @Test
    public void VerificationTestFindByUSername(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new Professor("professoruser", "p", "kiki", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "kiki", "ps12377777", "It", "University X", "12-12-2002", 3, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Error!");
        assertEquals(view.getErrorMessage(),"This username is already exists ,choose another");

    }
    @Test
    public void VerificationTestPasswordLength(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("kkk", "kkk", "user45", "ps1", "It", "University X", "12-12-2002", 7, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals(view.geErrorTitle(),"Error!");
        assertEquals(view.getErrorMessage(),"password must have at least 8 characters");

    }
    @Test
    public void CreateProfessorAccountTest(){
        userDAO = new UserDAOMemory();  // Real DAO implementation
        professorDAO = new ProfessorDAOMemory();  // Real DAO implementation
        userDAO.save(new User("testuser", "p", "John", "Doe", "CS", "University X", "1990-01-01", 1));
        professorDAO.save(new Professor("professoruser", "p", "Jane", "Doe", "CS", "University X", "1985-05-05", 2, "Professor"));

        SingUpProfessorPresenter presenter = new SingUpProfessorPresenter(userDAO, professorDAO);
        SingUpProfessorViewStub view = new SingUpProfessorViewStub("maria", "markou", "mariamakrou", "1234567890987", "It", "University X", "12-12-2002", 3434567, "p");
        presenter.setView(view);
        presenter.verification();
        assertEquals("Registration Successful",view.GetSuccessTitle());
        assertEquals("Your account has been successfully created. You can now log in and start using our services.",view.GetSuccessMessage());

    }


}

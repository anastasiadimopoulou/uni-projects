package com.example.unitestapplication.view.sing_up.Student;

import android.os.Handler;
import android.os.Looper;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.StudentDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Student;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.domain.User;
import com.example.unitestapplication.memoryDAO.UserDAOMemory;
import com.example.unitestapplication.view.log_in.LogInView;

import java.util.ArrayList;

public class SingUpStudentPresenter {
    private SingUpStudentView view;
    private  UserDAO userDAO = null;
    private StudentDAO studentDAO;
    private QuestionDAO questionDAO;

    /**
     * CONSTRUCTOR SING UP STUDENT PRESENTER
     * @param userDAO
     * @param studentDAO
     * @param questionDAO
     */
    public SingUpStudentPresenter(UserDAO userDAO,StudentDAO studentDAO,QuestionDAO questionDAO){
        this.studentDAO=studentDAO;
        this.userDAO=userDAO;
        this.questionDAO=questionDAO;
    }

    /**
     * GETS STUDENT DAO
     * @return StudentDAO
     */

    public StudentDAO getStudentDAO(){
        return  studentDAO;
    }

    /**
     * GETS QUESTION DAO
     * @return QuestionDAO
     */
    public QuestionDAO getQuestionDAO(){
        return questionDAO;
    }

    /**
     * GETS USER DAO
     * @return UserDAO
     */
    public UserDAO getUserDAO(){
        return  userDAO;
    }

    /**
     * SETS  AS VIEW THE SING UP STUDENT VIEW
     * @param view
     */

    public void setView(SingUpStudentView view) {
        this.view = view;
    }

    /**
     * GETS SING UP STUDENT VIEW
     * @return SingUpStudentView
     */

    public SingUpStudentView getView() {
        return view;
    }

    /**
     * OPENS LOG IN ACTIVITY
     */
    public void goToLogIn(){
        view.openLogInActivity();
    }

    /**
     * extracts all the fields from UI and creates Student Account and goes to log in page again
     */
     public void  CreateStudentAccount(){
         String username= view.extractStudentUsername();
         String password= view.extractStudentPassword();
         String name= view.extractStudentName();
         String surname= view.extractStudentSurname();
         String birthdate= view.extractStudentBirthDate();
         int academicId= view.extractStudentAcademicId();
         String university= view.extractStudentUniversity();
         String department = view.extractStudentDepartment();
         User newuser= new Student(name,surname,username,password,department,university,birthdate,academicId,questionDAO.findAll(),new ArrayList<Tests>());
         userDAO.save(newuser);
         Student newstudent= new Student(name,surname,username,password,department,university,birthdate,academicId,questionDAO.findAll(), new ArrayList<Tests>());
         studentDAO.save(newstudent);
         view.showSuccessSingUpMessage("Registration Successful","Your account has been successfully created. You can now log in and start using our services.");

         view.openLogInActivity();



     }

    /**
     * Checks if user has filled all the fields and if something is missing shows error message then checks if user already  exist with this id  and username and show error message
     *  if user not exists already creates student account
     */
    public void verification(){
        String username= view.extractStudentUsername();
        String password= view.extractStudentPassword();
        String name= view.extractStudentName();
        String surname= view.extractStudentSurname();
        String birthdate= view.extractStudentBirthDate();
        int academicId= view.extractStudentAcademicId();
        String university= view.extractStudentUniversity();
        String department = view.extractStudentDepartment();

        if (password.isEmpty()&& username.isEmpty()&& name.isEmpty()&&surname.isEmpty()&&birthdate.isEmpty()&&academicId==-1&& university.isEmpty()&& department.isEmpty()){
            view.showErrorMessage("Error!","Missing Fields");

        }

        else if( name.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete name");
        }
        else  if (surname.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete surname");

        }else if(birthdate.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete birthday");
        }else if (academicId==-1){
            view.showErrorMessage("Missing Fields.","Complete academic ID");
        }else if (department.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete department");

        } else if (university.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete university");

        }
        else if (username.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete username");

        }else if ( password.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete password");

        }else if (userDAO.findByID(academicId)!=null){
            view.showErrorMessage("Error!","Already exists account with this academic ID");


        }else if(userDAO.findByUsername(username)!=null){
            view.showErrorMessage("Error!","This username is already exists ,choose another");

        }else if (password.length()<8){
            view.showErrorMessage("Error!","password must have at least 8 characters");


        }else{

        CreateStudentAccount();}

        }



    }



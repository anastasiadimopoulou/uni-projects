package com.example.unitestapplication.view.sing_up.Professor;

import android.os.Handler;
import android.os.Looper;

import com.example.unitestapplication.DAO.ProfessorDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Professor;
import com.example.unitestapplication.domain.Student;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.domain.User;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentView;

import java.util.ArrayList;

public class SingUpProfessorPresenter {
    private SingUpProfessorView view;
    private UserDAO userDAO=null;
    private ProfessorDAO professorDAO;

    /**
     * CONSTRUCTOR SING UP PROFESSOR PRESENTER
     * @param userdao
     * @param professorDAO
     */
    public SingUpProfessorPresenter(UserDAO userdao,ProfessorDAO professorDAO){
        this.professorDAO=professorDAO;
        this.userDAO=userdao;

    }

    /**
     * GET USER DAO
     * @return UserDAO
     */
    public UserDAO getUserDAO(){
        return userDAO;
    }

    /**
     * GET PROFESSOR DAO
     * @return ProfessorDAO
     */
    public ProfessorDAO getProfessorDAO(){
        return  professorDAO;
    }

    /**
     * SET SING UP PROFESSOR VIEW
     * @param view
     */
    public void setView(SingUpProfessorView view) {
        this.view = view;
    }

    /**
     * GET SING UP PROFESSOR VIEW
     * @return SingUpProfessorView
     */

    public SingUpProfessorView getView() {
        return view;
    }

    /**
     * Opens LOG IN ACTIVITY
     */
    public void goToLogIn(){
        view.openLogInActivity();
    }

    /**
     * extracts all the fields from UI and creates Professor Account and goes to log in page again
     */
    public void  CreateProfessorAccount(){
        String username= view.extractProfessorUsername();
        String password= view.extractProfessorPassword();
        String name= view.extractProfessorName();
        String surname= view.extractProfessorSurname();
        String birthdate= view.extractProfessorBirthDate();
        int Id= view.extractProfessorId();
        String university= view.extractProfessorUniversity();
        String department = view.extractProfessorDepartment();
        String position= view.extractProfessorAcademicPosition();
        User newuser= new Professor(name,surname,username,password,department,university,birthdate,Id,position);
        userDAO.save(newuser);
        Professor newprofessor= new Professor(name,surname,username,password,department,university,birthdate,Id,position);
        professorDAO.save(newprofessor);
        view.showSuccessSingUpMessage("Registration Successful","Your account has been successfully created. You can now log in and start using our services.");

        view.openLogInActivity();



    }

    /**
     * Checks if user has filled all the fields and if something is missing shows error message then checks if user already  exist with this id  and username and show error message
     * if user not exists already creates professor account
     */
    public void verification(){
        String username= view.extractProfessorUsername();
        String password= view.extractProfessorPassword();
        String name= view.extractProfessorName();
        String surname= view.extractProfessorSurname();
        String birthdate= view.extractProfessorBirthDate();
        int Id= view.extractProfessorId();
        String university= view.extractProfessorUniversity();
        String department = view.extractProfessorDepartment();
        String position= view.extractProfessorAcademicPosition();


        if (password.isEmpty()&& username.isEmpty()&& name.isEmpty()&&surname.isEmpty()&&birthdate.isEmpty()&& Id==-1&& university.isEmpty()&& department.isEmpty()&&position.isEmpty()){
            view.showErrorMessage("Error!","Missing Fields");

        }

        else if( name.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete name");
        }
        else  if (surname.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete surname");

        }else if(birthdate.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete birthday");
        }else if (Id==-1){
            view.showErrorMessage("Missing Fields.","Complete academic ID");
        }else if (department.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete department");

        } else if (university.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete university");

        }else if(position.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete Academic position");

        }
        else if (username.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete username");

        }else if ( password.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete password");

        }
        else if (userDAO.findByID(Id)!=null){
                view.showErrorMessage("Error!","Already exists account with this professor ID");


            }else if(userDAO.findByUsername(username)!=null){
                view.showErrorMessage("Error!","This username is already exists ,choose another");

            }else if (password.length()<8){
                view.showErrorMessage("Error!","password must have at least 8 characters");


            }
       else{ CreateProfessorAccount();}

        }

}

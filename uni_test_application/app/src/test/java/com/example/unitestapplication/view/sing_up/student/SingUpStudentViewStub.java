package com.example.unitestapplication.view.sing_up.student;

import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentPresenter;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentView;

import java.util.ArrayList;

public class SingUpStudentViewStub  implements SingUpStudentView {
    private String password,username,name,surname,birthdate,department,university,errortitle,errormessage
            ,successtitle,successmessage;
    private  int id,loginClick;
    private SingUpStudentPresenter presenter;

    public  SingUpStudentViewStub(String name, String surname, String username, String password, String department, String university, String birthdate,int id){
        this.id=id;
        this.university=university;
        this.username=username;
        this.surname=surname;
        this.department=department;
        this.name=name;
        this.password=password;
        this.birthdate=birthdate;
    }
    @Override
    public String extractStudentPassword() {
        return password;
    }

    @Override
    public String extractStudentUsername() {
        return username;
    }

    @Override
    public String extractStudentName() {
        return name;
    }

    @Override
    public String extractStudentSurname() {
        return surname;
    }

    @Override
    public String extractStudentBirthDate() {
        return birthdate;
    }

    @Override
    public int extractStudentAcademicId() {
        return id;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errormessage=message;
        errortitle=title;

    }

    @Override
    public void showSuccessSingUpMessage(String title, String message) {
        successmessage=message;
        successtitle=title;

    }

    @Override
    public void openLogInActivity() {
        loginClick++;

    }

    @Override
    public String extractStudentDepartment() {
        return department;
    }

    @Override
    public String extractStudentUniversity() {
        return university;
    }

    public int getLoginClick() {
        return loginClick;
    }


    public String geErrorTitle() {
        return  errortitle;
    }

    public String getErrorMessage() {
        return errormessage;
    }

    public  String GetSuccessTitle(){
        return  successtitle;
    }
    public  String  GetSuccessMessage(){
        return successmessage;
    }

}

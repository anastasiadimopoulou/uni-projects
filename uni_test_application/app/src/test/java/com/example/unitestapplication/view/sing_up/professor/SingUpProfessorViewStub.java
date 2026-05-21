package com.example.unitestapplication.view.sing_up.professor;

import com.example.unitestapplication.view.sing_up.Professor.SingUpProfessorPresenter;
import com.example.unitestapplication.view.sing_up.Professor.SingUpProfessorView;

public class SingUpProfessorViewStub implements SingUpProfessorView {
    private SingUpProfessorPresenter presenter;
    private String password,username,name,surname,birthdate,department,university,position,errortitle,errormessage
            ,successtitle,successmessage;
    private  int id,loginClick;
    public  SingUpProfessorViewStub(String name,String surname,String username,String password,String department,String university,String birthdate,int id,String position){
        this.id=id;
        this.university=university;
        this.username=username;
        this.surname=surname;
        this.department=department;
        this.name=name;
        this.position=position;
        this.password=password;
        this.birthdate=birthdate;
    }
    @Override
    public void openLogInActivity() {
        loginClick++;

    }
    public  int getLoginClick(){
        return loginClick;
    }

    @Override
    public String extractProfessorPassword() {
        return password;
    }

    @Override
    public String extractProfessorUsername() {
        return username;
    }

    @Override
    public String extractProfessorName() {
        return name;
    }

    @Override
    public String extractProfessorSurname() {
        return surname;
    }

    @Override
    public String extractProfessorBirthDate() {
        return birthdate;
    }

    @Override
    public int extractProfessorId() {
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
    public String extractProfessorDepartment() {
        return department;
    }

    @Override
    public String extractProfessorUniversity() {
        return university;
    }

    @Override
    public String extractProfessorAcademicPosition() {
        return position;
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

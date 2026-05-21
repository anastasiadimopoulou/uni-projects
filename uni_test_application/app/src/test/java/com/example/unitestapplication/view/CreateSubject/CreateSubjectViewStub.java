package com.example.unitestapplication.view.CreateSubject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectView;
//import org.testng.annotations.Test;

public class CreateSubjectViewStub implements CreateSubjectView {

    private String name,errorTitle,errorMessage,successTitle,successMessage;
    private int id,semester,  profid;
    private boolean basicProfessorMenuOpened;


    public CreateSubjectViewStub(String name,int id,int semester,int profid)
    {
        this.name=name;
        this.id=id;
        this.semester=semester;
        this.profid=profid;
    }
    @Override
    public String getname() {
        return name;
    }

    @Override
    public int getid() {
        return id;
    }

    @Override
    public int getsemester() {
        return semester;
    }

    @Override
    public int getprofid() {
        return profid;
    }

    @Override
    public void showErrorMessage(String Title, String Message) {
        errorTitle= Title;
        errorMessage=Message;

    }

    public String getErrorTitle(){
        return errorTitle;
    }


    @Override
    public void showsuccessCreateSubject(String Title, String Message) {
        successTitle= Title;
        successMessage=Message;
    }

    @Override
    public void setname(String name) {
        this.name=name;
    }

    @Override
    public void setid(int id) {
        this.id=id;
    }

    @Override
    public void setsemester(int semester) {
        this.semester=semester;
    }

    @Override
    public void setsprofid(int profid) {
        this.profid=profid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }



    @Override
    public void openbasicprofessormenu() {
        this.basicProfessorMenuOpened = true;
    }

    public boolean isBasicProfessorMenuOpened() {
        return basicProfessorMenuOpened;
    }



}

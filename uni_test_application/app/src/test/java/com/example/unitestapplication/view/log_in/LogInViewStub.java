package com.example.unitestapplication.view.log_in;

public class LogInViewStub implements LogInView {
    private String password,username ,errorTitle,errorMessage;
    private LogInPresenter presenter;
    private  int singUpProfessorClick,singUpStudentClick,homePageStudentClick,homePageProfessorClick;
    public LogInViewStub(String username,String password){
        this.password=password;
        this.username=username;
    }
    @Override
    public String extractPassword() {
        return password;
    }

    @Override
    public String extractUsername() {
        return username;
    }

    @Override
    public void showErrorMessage(String title, String message) {
    errorTitle= title;
    errorMessage=message;
    }
    public String getErrorTitle(){
        return errorTitle;
    }
    public  String getLastErrorMessage(){
        return  errorMessage;
    }

    @Override
    public void openSignupProfessorActivity() {
        singUpProfessorClick++;


    }
    public  int getSingUpProfessorClick(){
        return singUpProfessorClick;
    }
    public  int getSingUpStudentClick(){
        return  singUpStudentClick;
    }

    @Override
    public void openSignupStudentActivity() {
        singUpStudentClick++;

    }
    public int getHomePageStudentClick(){
        return homePageStudentClick;
    }
    public  int getHomePageProfessorClick(){
        return  homePageProfessorClick;
    }

    @Override
    public void openStudentHomePageActivity(int stid) {
        homePageStudentClick++;

    }

    @Override
    public void openProfessorHomePageActivity(int id) {
        homePageProfessorClick++;

    }
}

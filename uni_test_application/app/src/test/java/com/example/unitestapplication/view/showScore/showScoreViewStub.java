package com.example.unitestapplication.view.showScore;

public class showScoreViewStub implements showScoreView {
    private  int id,goToBasic_student_menuclick;
    public  int GETgoToBasic_student_menuClick(){
        return goToBasic_student_menuclick;
    }



    @Override
    public void openbasicstudentmenu() {
        goToBasic_student_menuclick++;

    }
}

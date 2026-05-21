package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion;

import android.content.Context;

public interface CreateQuestionView {
    public String getdescription();
    public String getlevel();
    public String getchoice1();
    public String getchoice2();
    public String getchoice3();
    public String getchoice4();
    public String getprofans();
    public void showErrorMessage(String Title,String Message);
    public void showsuccessCreateQuestion(String Title,String Message);

    public void setdescripton(String d);
    public void setlevel(String l);
    public void setchoice1(String ch1);
    public void setchoice2(String ch2);
    public void setchoice3(String ch3);
    public void setchoice4(String ch4);
    public void setprofans(String pans);

    public void openCreateDeleteQuest();

}

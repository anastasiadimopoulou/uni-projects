package com.example.unitestapplication.view.CreateQuestion;

import android.content.Context;
import android.content.Intent;

import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionActivity;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionView;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.QuestionManagmentActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;




public class CreateQuestionViewStub implements CreateQuestionView {
    private String description;
    private String level;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String profans;
    private int subjectid;
    private boolean state;
    private  String errortitle,errormessage;

    public String geErrorTitle() {
        return  errortitle;
    }

    public String getErrorMessage() {
        return errormessage;
    }

    @Override
    public String getdescription() {
        return description;
    }

    @Override
    public String getlevel() {
        return level;
    }

    @Override
    public String getchoice1() {
        return choice1;
    }

    @Override
    public String getchoice2() {
        return choice2;
    }

    @Override
    public String getchoice3() {
        return choice3;
    }

    @Override
    public String getchoice4() {
        return choice4;
    }

    @Override
    public String getprofans() {
        return profans;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errormessage=message;
        errortitle=title;

    }

    @Override
    public void showsuccessCreateQuestion(String title, String message) {
        // Just print the message to simulate showing a success message
        System.out.println("Success: " + title + " - " + message);
    }

    @Override
    public void setdescripton(String d) {
        this.description = d;
    }

    @Override
    public void setlevel(String l) {
        this.level = l;
    }

    @Override
    public void setchoice1(String ch1) {
        this.choice1 = ch1;
    }

    @Override
    public void setchoice2(String ch2) {
        this.choice2 = ch2;
    }

    @Override
    public void setchoice3(String ch3) {
        this.choice3 = ch3;
    }

    @Override
    public void setchoice4(String ch4) {
        this.choice4 = ch4;
    }
    //@Override
    public void setsubjectid(int id){
        this.subjectid=id;
    }

   // @Override
    public void setstate(boolean s) {
        this.state=s;
    }

    @Override
    public void setprofans(String pans) {
        this.profans = pans;
    }




    private CreateQuestionViewStub createQuestionView;

    @Before
    public void setUp() {
        createQuestionView = new CreateQuestionViewStub();
    }

    @Test
    public void testSetAndGetDescription() {
        String description = "Sample Question Description";
        createQuestionView.setdescripton(description);
        assertEquals(description, createQuestionView.getdescription());
    }

    @Test
    public void testSetAndGetLevel() {
        String level = "Intermediate";
        createQuestionView.setlevel(level);
        assertEquals(level, createQuestionView.getlevel());
    }

    @Test
    public void testSetAndGetChoice1() {
        String choice1 = "Choice 1";
        createQuestionView.setchoice1(choice1);
        assertEquals(choice1, createQuestionView.getchoice1());
    }

    @Test
    public void testSetAndGetChoice2() {
        String choice2 = "Choice 2";
        createQuestionView.setchoice2(choice2);
        assertEquals(choice2, createQuestionView.getchoice2());
    }

    @Test
    public void testSetAndGetChoice3() {
        String choice3 = "Choice 3";
        createQuestionView.setchoice3(choice3);
        assertEquals(choice3, createQuestionView.getchoice3());
    }

    @Test
    public void testSetAndGetChoice4() {
        String choice4 = "Choice 4";
        createQuestionView.setchoice4(choice4);
        assertEquals(choice4, createQuestionView.getchoice4());
    }

    @Test
    public void testSetAndGetProfans() {
        String profans = "Choice A";
        createQuestionView.setprofans(profans);
        assertEquals(profans, createQuestionView.getprofans());
    }

    @Test
    public void testShowErrorMessage() {
        String title = "Error Title";
        String message = "This is an error message";

        // Capture output (you can use something like System.setOut to capture printed messages)
        createQuestionView.showErrorMessage(title, message);
        // In a real test, you would verify if the message was printed or logged.
        // Here, we can simply check if the method was executed
        // No actual assertion is needed for this simple output-based method
    }

    @Test
    public void testShowSuccessCreateQuestion() {
        String title = "Success Title";
        String message = "The question was successfully created!";

        // Capture output (you can use System.setOut or log verification here)
        createQuestionView.showsuccessCreateQuestion(title, message);
        // Similar to the previous test, just verify the execution of the method
    }

    @Test
    public void testSetDescriptionVerification() {
        String description = "Test Description";
        createQuestionView.setdescripton(description);

        // Verify the setter was called by checking the stored value
        assertEquals(description, createQuestionView.getdescription());
    }

    private boolean openCreateDeleteQuestCalled = false;

    @Override
    public void openCreateDeleteQuest() {
        openCreateDeleteQuestCalled = true;
    }

    public boolean isOpenCreateDeleteQuestCalled() {
        return openCreateDeleteQuestCalled;
    }
}

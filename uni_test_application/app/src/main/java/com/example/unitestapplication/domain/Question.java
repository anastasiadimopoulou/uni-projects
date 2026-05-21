package com.example.unitestapplication.domain;

import java.util.Objects;
public class Question
{
    private String level;
    private String description;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    //private int id;
    private String prof_ans;
    public boolean state;
    private static int idCounter = 1; // Static counter for unique IDs
    private final int id;
    private int subjectid;



    private Student_ans st_ans;

    public Question(String l,String d,String ch1,String ch2,String ch3,String ch4,String prof_ans,boolean s,int subjectid)
    {
        this.level=l;
        this.description=d;
        this.choice1=ch1;
        this.choice2=ch2;
        this.choice3=ch3;
        this.choice4=ch4;
        this.id = idCounter++;
        this.prof_ans=prof_ans;
        this.state=s;
        this.subjectid=subjectid;
    }
    public int getSubjectid(){return subjectid;}

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public int getId() {
        return id;
    }

    public String getProfAns() {
        return prof_ans;
    }

    public boolean getState() {
        return state;
    }

    public String setLevel(String level) {
        if (level=="1" || level=="2" || level=="3"){
            this.level = level;
        } else {
            this.level="0";
        }
        return this.level;
    }

    public void setDescription(String description)
    {
        if (description == "")
        {
            System.out.println("Description is invalid (null).");
            this.description ="";
        }
        this.description = description;
    }

    public void setChoice1(String choice1)
    {
        if(choice1==null)
        {
            System.out.println("Choice1 has never filled.");
            this.choice1=null;
        }
        this.choice1 = choice1;

    }

    public void setChoice2(String choice2)
    {
        if (choice2 == null) {
            System.out.println("Choice2 has never filled.");
            this.choice2 = null;
        } else {
            this.choice2 = choice2;
        }
    }

    public void setChoice3(String choice3) {
        if (choice3 == null) {
            System.out.println("Choice3 has never filled.");
            this.choice3 = null;
        } else {
            this.choice3 = choice3;
        }
    }


    public void setChoice4(String choice4) {
        if (choice4 == null) {
            System.out.println("Choice4 has never filled.");
            this.choice4 = null;
        } else {
            this.choice4 = choice4;
        }
    }
    public void setsubjectid(int id){
        this.subjectid=id;
    }



    public void setProfAns(String prof_ans) {
        if (Objects.equals(prof_ans, "A") || Objects.equals(prof_ans, "B") || Objects.equals(prof_ans, "C") || Objects.equals(prof_ans, "D")) {
            this.prof_ans = prof_ans;
        } else {
            this.prof_ans = "";
        }
    }

    public void setState(boolean state)
    {
        this.state = state;
    }

    public String toString()
    {

        return "Q: " + this.description + "\n" +
                "A. " + this.choice1 + "\n" +
                "B. " + this.choice2 + "\n" +
                "C. " + this.choice3 + "\n" +
                "D. " + this.choice4;
    }

    public boolean checkAns(Question q,Student_ans studans)
    {
        if(Objects.equals(q.getProfAns(), studans.getNumber_of_choice()))
        {

            setState(true);
            return true;
        }
        else {

            q.setState(false);
            return false;
        }
    }

}
package com.example.unitestapplication.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.unitestapplication.domain.Tests;
public class Student extends User {

    ArrayList <Question> ListOfQuestionsPerStudent = new ArrayList<Question>();
    ArrayList <Tests>TestList = new ArrayList<Tests>();

    public Student(String f, String l, String usr, String pass, String dep, String uni , String bd, int id, ArrayList<Question> ListOfQuestionsPerStudent, ArrayList<Tests> TestList){
        super(f,l,usr,pass,dep,uni ,bd,id);
        this.ListOfQuestionsPerStudent=ListOfQuestionsPerStudent;
        this.TestList=TestList;
    }

    public List<Tests> getTests(){
        return TestList;
    }

    public List<Question> getListOfQuestionPerStudent(){
        return ListOfQuestionsPerStudent;
    }

    /**
     * Creates test and add it to test list od the student
     * @param q
     * @param s
     * @param l
     * @param id
     * @param ListOfQuestions
     */


    public void createTest(int q, int s, String l,int id, ArrayList<Question> ListOfQuestions){
        Tests test1 = new Tests(q,s,l,id, ListOfQuestions);
        TestList.add(test1);
    }
        


}

package com.example.unitestapplication.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

import com.example.unitestapplication.domain.Student;


public class StudentTest {

    @Test
    public void testConstuctor(){
        ArrayList<Question> ListOfQuestionsPerStudent = new ArrayList<Question>();
        ArrayList <Tests>TestList = new ArrayList<Tests>();
        Student s=new Student("jack", "smith", "smith1", "jack01", "IT", "aueb" , "11-11-2005" ,1105,ListOfQuestionsPerStudent,TestList );
        assertEquals("jack",s.getFirst_name());
        assertEquals("smith",s.getLast_name());
        assertEquals("jack01",s.getPassword());
        assertEquals("smith1",s.getUsername());
        assertEquals("aueb",s.getUniversity());
        assertEquals("IT",s.getDepartment());
        assertEquals("11-11-2005",s.getDate_of_birth());
        assertEquals(1105,s.getId());
        assertEquals(TestList,s.getTests());
        assertEquals(ListOfQuestionsPerStudent,s.getListOfQuestionPerStudent());
    }

    @Test
    public void testcreateTest(){
        ArrayList<Question> ListOfQuestionsPerStudent = new ArrayList<Question>();
        ArrayList <Tests>TestList = new ArrayList<Tests>();

        Student s=new Student("jack", "smith", "smith1", "jack01", "IT", "aueb" , "11-11-2005" ,1105,ListOfQuestionsPerStudent,TestList );

        ArrayList<Question> q11 = new ArrayList<Question>();

        s.createTest(10,0,"3",14,q11);
        assertEquals(1,s.getTests().size());
    }

    @Test
    public void testgetTests(){
        ArrayList<Question> ListOfQuestionsPerStudent = new ArrayList<Question>();
        ArrayList <Tests>TestList = new ArrayList<Tests>();
        Student s=new Student("jack", "smith", "smith1", "jack01", "IT", "aueb" , "11-11-2005" ,1105,ListOfQuestionsPerStudent,TestList );
        Question q1 = new Question("1", "o ouranos einai :", "blue", "red", "green", "black", "A", true, 50);
        Question q2 = new Question("3", "to aspro alogo tou kolokotroni einai:", "aspro", "blsack", "blue", "green",  "A", true,50);
        Question q3 = new Question("3", "to nero brazei stoys :", "100", "50", "20", "80", "A", true,50);
        Question q4 = new Question("3", "1+1:", "2", "1", "5", "3", "A", true,50);
        Question q5 = new Question("3", "2+2 :", "4", "50", "20", "80", "A", true,50);
        //ArrayList<Question> QuestionListStudent = new ArrayList<Question>();
        ListOfQuestionsPerStudent.add(q1);
        ListOfQuestionsPerStudent.add(q2);
        ListOfQuestionsPerStudent.add(q3);
        ListOfQuestionsPerStudent.add(q4);
        ListOfQuestionsPerStudent.add(q5);
        Tests test1 = new Tests(1, 5, "2",14,ListOfQuestionsPerStudent);
        Tests test2 = new Tests(1, 5, "2",15,ListOfQuestionsPerStudent);
        assertEquals(TestList,s.getTests());
    }

    @Test
    public void testgetListOfQuestionPerStudent(){

        ArrayList<Question> ListOfQuestionsPerStudent = new ArrayList<Question>();
        ArrayList <Tests>TestList = new ArrayList<Tests>();
        Student s=new Student("jack", "smith", "smith1", "jack01", "IT", "aueb" , "11-11-2005" ,1105,ListOfQuestionsPerStudent,TestList );
        Question q1 = new Question("1", "o ouranos einai :", "blue", "red", "green", "black", "A", true,50);
        Question q2 = new Question("3", "to aspro alogo tou kolokotroni einai:", "aspro", "blsack", "blue", "green", "A", true,50);
        Question q3 = new Question("3", "to nero brazei stoys :", "100", "50", "20", "80", "A", true,50);
        Question q4 = new Question("3", "1+1:", "2", "1", "5", "3", "A", true,50);
        Question q5 = new Question("3", "2+2 :", "4", "50", "20", "80", "A", true,50);
        //ArrayList<Question> QuestionListStudent = new ArrayList<Question>();
        ListOfQuestionsPerStudent.add(q1);
        ListOfQuestionsPerStudent.add(q2);
        ListOfQuestionsPerStudent.add(q3);
        ListOfQuestionsPerStudent.add(q4);
        ListOfQuestionsPerStudent.add(q5);
        //Tests test1 = new Tests(1, 5, "2",14,ListOfQuestionsPerStudent);
        //Tests test2 = new Tests(1, 5, "2",15,ListOfQuestionsPerStudent);
        assertEquals(ListOfQuestionsPerStudent,s.getListOfQuestionPerStudent());
    }
}

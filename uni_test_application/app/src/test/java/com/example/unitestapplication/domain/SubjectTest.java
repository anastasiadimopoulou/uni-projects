package com.example.unitestapplication.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SubjectTest {
    private Subject subject;

    @Before
    public void setUp() {

        subject = new Subject("AI", 1, 5, 456);

    }
    @Test
    public void testConstructorwithPar() {
        Subject subject = new Subject("AI", 1, 5,456);
        assertEquals("AI", subject.getname());
        assertEquals(1, subject.getid());
        assertEquals(5, subject.getsemester());
    }

    //-------------GETTERS------------------------
    @Test
    public void  subjectgetname() {
        Subject sub = new Subject("AI", 1, 5,45);
        assertEquals("AI",sub.getname());
    }

    @Test
    public void  subjectgetprofId() {
        Subject sub = new Subject("AI", 1, 5,45);
        assertEquals(45,sub.getProfid());
    }

    @Test
    public void  subjectgetid() {
        Subject sub = new Subject("AI", 1, 5,54);
        assertEquals(1,sub.getid());
    }

    @Test
    public void  subjectgetsemester() {
        Subject sub = new Subject("AI", 1, 5,54);
        assertEquals(5,sub.getsemester());
    }

    //---------------------SETTERS VALID-------------------------
    @Test
    public void testSetName() {
        Subject sub = new Subject("AI", 1, 5,54);
        sub.setname("AI");
        assertEquals("AI", sub.getname());
    }

    @Test
    public void testSetIdValid() {
        Subject sub = new Subject("AI", 1, 5,5);
        sub.setid(1);
        assertEquals(1,sub.getid());
    }

    @Test
    public void testSetIdInvalid() {
        Subject sub = new Subject("AI", 1, 5,5);
        sub.setid(-100);
        assertEquals(0,sub.getid());
    }

    @Test
    public void testEqualsValid() {
        Subject sub1 = new Subject("AI", 1, 5,45);
        Subject sub2 = new Subject("AI", 1, 5,45);
        assertTrue(sub1.equals(sub2));
    }

    @Test
    public void testEqualsInvalid() {
        Subject subject1 = new Subject("AI", 1, 5,45);
        Subject subject2 = new Subject("Logic", 2, 5,45);
        assertFalse(subject1.equals(subject2));
    }

    @Test
    public void testEqualsnull() {
        Subject subject1 = new Subject();
        assertFalse(subject1.equals(null));
    }

    @Test
    public void testToString() {
        Subject subject = new Subject("Biology", 10, 2,45);
        String expected = "Subject { Name='Biology', ID=10, Semester=2 }";
        assertEquals(expected, subject.toString());
    }

    @Test
    public  void testaddquestion(){
        Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");
        p.create_subject("AI",124,5,2323);

        Subject sub=p.getSubjects().get(0);
        sub.addQuestion("1", "The sky is:", "Blue", "Red", "Green", "Black",1, "A", true,50);
        assertEquals("1",p.getSubjects().get(0).getQuestions().get(0).getLevel());
        assertEquals("The sky is:",p.getSubjects().get(0).getQuestions().get(0).getDescription());
        assertEquals("Blue",p.getSubjects().get(0).getQuestions().get(0).getChoice1());
        assertEquals("Red",p.getSubjects().get(0).getQuestions().get(0).getChoice2());
        assertEquals("Green",p.getSubjects().get(0).getQuestions().get(0).getChoice3());
        assertEquals("Black",p.getSubjects().get(0).getQuestions().get(0).getChoice4());
        assertEquals("A",p.getSubjects().get(0).getQuestions().get(0).getProfAns());
        assertEquals(1,p.getSubjects().get(0).getQuestions().size());
    }

    @Test
    public  void testDeleteQuestion(){
        Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");
        p.create_subject("Logic",12,5,2323);
        Subject sub=p.getSubjects().get(0);
        sub.addQuestion("1", "The sky is:", "Blue", "Red", "Green", "Black",1, "A", true,124);
        sub.deleteQuestion(sub.getQuestions().get(0).getId());
        assertEquals(0,p.getSubjects().get(0).getQuestions().size());
    }
}

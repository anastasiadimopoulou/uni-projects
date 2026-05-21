package com.example.unitestapplication.domain;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.unitestapplication.domain.Professor;

import java.util.List;


public class ProfessorTest {

    private Professor professor;

    @Before
    public void setUp() {
        professor = new Professor("john", "smith", "johns13", "1234a", "IT", "aueb", "11-1-2000", 2323, "epikouros");
        // Creating a subject for testing
        professor.create_subject("AI", 124, 5, 2323);
        // Adding questions to the subject
        professor.update_subject(124, 1, "1", "The sky is:", "Blue", "Red", "Green", "Black", 1, "a", true);
        professor.update_subject(124, 1, "2", "What is the color of the sun?", "Yellow", "Red", "Blue", "Green", 2, "a", true);
    }
@Test
    public void testConstuctor(){
    Professor p=new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");
    assertEquals("john",p.getFirst_name());
    assertEquals("smith",p.getLast_name());
    assertEquals("1234a",p.getPassword());
    assertEquals("johns13",p.getUsername());
    assertEquals("aueb",p.getUniversity());
    assertEquals("IT",p.getDepartment());
    assertEquals("11-1-2000",p.getDate_of_birth());
    assertEquals("epikouros",p.getLevel());
    assertEquals(2323,p.getId());
}




@Test
    public void testgetlevel(){
    Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");
    String exp= "epikouros";
    String act=p.getLevel();
    assertEquals(exp,act);

}
@Test
 public void testsetlevel(){
    Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");
    p.setLevel("k");
    assertEquals("k",p.getLevel());


 }
    @Test
    public void testcreatesubject(){
        Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");


        p.create_subject("Math",123,1,2323);
        assertEquals(1,p.getSubjects().size());
    }
    @Test
    public void testdeletesubject(){

    Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");


    p.create_subject("Math",123,1,2323);
    p.create_subject("AI",124,5,2323);
        p.delete_subject(123);
        assertEquals(1,p.getSubjects().size());


    }

    @Test
    public void testupdate_subject_addquest(){
        Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323, "epikouros");
        p.create_subject("AI",124,5,2323);
        p.update_subject(124,1,"1", "The sky is:", "Blue", "Red", "Green", "Black", 1, "a", true);

        assertEquals(1,p.getSubjects().get(0).getQuestions().size());
}
    @Test
    public void testupdate_subject_delquest(){
        Professor p =new Professor("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,123, "epikouros");
        p.create_subject("AI",124,5,123);
        p.update_subject(123,1,"1", "The sky is:", "Blue", "Red", "Green", "Black", 1, "a", true);
        p.update_subject(123,2,"1", "The sky is:", "Blue", "Red", "Green", "Black", 1, "a", true);

        assertEquals(0,p.getSubjects().get(0).getQuestions().size());

    }


}


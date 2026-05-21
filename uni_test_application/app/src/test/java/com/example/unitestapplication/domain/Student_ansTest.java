package com.example.unitestapplication.domain;


import static org.junit.Assert.*;

import org.junit.Test;

import com.example.unitestapplication.domain.Student_ans;

public class Student_ansTest {
    @Test
    public void testConstructor(){
        Student_ans ans = new Student_ans("a");
        assertEquals("a",ans.getNumber_of_choice());

    }



    @Test
    public void testset_Number_of_choice(){
         Student_ans a =new Student_ans();
         a.setNumber_of_choice("a");
         assertEquals("a", a.getNumber_of_choice());

    }
    @Test
    public void testToString() {
        Student_ans ans = new Student_ans();
        ans.setNumber_of_choice("a");
        String expected = "Student_ans{number_of_choice='a'}";
        assertEquals(expected, ans.toString());

    }
    @Test
    public void testGet_Number_of_choice(){
        Student_ans ans =new Student_ans("a");
        String expected = "a";
        String actual = ans.getNumber_of_choice();
        assertEquals(expected,actual);
    }




}

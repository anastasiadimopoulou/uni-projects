package com.example.unitestapplication.domain;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestTest {

    private Tests test;
    private ArrayList<Question> questionList;

    @Before
    public void setUp() {
        Question q1 = new Question("1", "o ouranos einai :", "blue", "red", "green", "black", "A", true, 50);
        questionList = new ArrayList<>();
        questionList.add(q1);
        test = new Tests(1, 5, "2", 14, questionList);
    }

    @After
    public void tearDown() {
        test = null;
        questionList = null;
    }

    @Test
    public void testConstructorpar() {
        assertEquals(1, test.getQuantity());
        assertEquals(5, test.getScore());
        assertEquals("2", test.getLevel());
        assertEquals(14, test.getId());
    }

    @Test
    public void testgetScore() {
        assertEquals(5, test.getScore());
    }

    @Test
    public void testprintScore() {
        test.updateScore(test);
        int s = test.getScore();
        int a = test.getQuantity();
        assertEquals("1/1", test.printScore(s, a));
    }

    @Test
    public void testSetQuantityValid() {
        test.setQuantity(1);
        assertEquals(1, test.getQuantity());
    }

    @Test
    public void testSetQuantityInValid() {
        test.setQuantity(15);
        assertEquals(-1, test.getQuantity());
    }

    @Test
    public void testsetId() {
        test.setId(14);
        assertEquals(14, test.getId());
    }

    @Test
    public void testSetLevel1Valid() {
        test.setLevel("1");
        assertEquals("1", test.getLevel());
    }

    @Test
    public void testSetLevel2Valid() {
        test.setLevel("2");
        assertEquals("2", test.getLevel());
    }

    @Test
    public void testSetLevel3Valid() {
        test.setLevel("3");
        assertEquals("3", test.getLevel());
    }

    @Test
    public void testSetLevelInvalid() {
        test.setLevel("-10");
        assertEquals("0", test.getLevel());
    }

    @Test
    public void testSetScoreValid() {
        test.setScore(5);
        assertEquals(5, test.getScore());
    }

    @Test
    public void testGetScoreValid() {
        test.getScore();
        assertEquals(5, test.getScore());
    }

    @Test
    public void test_choose_random_all_questions() {
        Question q2 = new Question("1", "to aspro alogo tou kolokotroni einai:", "aspro", "blsack", "blue", "green", "A", true, 50);
        Question q3 = new Question("1", "to nero brazei stoys :", "100", "50", "20", "80", "A", true, 50);
        Question q4 = new Question("1", "1+1:", "2", "1", "5", "3", "A", true, 50);
        Question q5 = new Question("1", "2+2 :", "4", "50", "20", "80", "A", false, 50);
        ArrayList<Question> questionListStudent = new ArrayList<>();
        questionListStudent.add(questionList.get(0));
        questionListStudent.add(q2);
        questionListStudent.add(q3);
        questionListStudent.add(q4);
        questionListStudent.add(q5);
        int act = test.choose_random_all_questions(3, questionListStudent).size();
        assertEquals(3, act);
    }

    @Test
    public void test_updatescoreValid() {
        test.setQuestionState(2, questionList, false);
        int s = test.updateScore(test);
        assertEquals(1, s);
    }

    @Test
    public void test_setStatequestionValid() {
        int id = questionList.get(0).getId();
        boolean flag = false;
        test.setQuestionState(id, questionList, flag);
        assertFalse(questionList.get(0).getState());
    }

    @Test
    public void test_getListOfQuestions(){
        assertEquals(test.getListOfQuestions(),questionList);
    }
}

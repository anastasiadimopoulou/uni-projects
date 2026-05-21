package com.example.unitestapplication.domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class QuestionTest {


    @Test
    public void testParamConstructor()
    {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "a", true,50);
        assertEquals("1", q.getLevel());
        assertEquals("The sky is:", q.getDescription());
        assertEquals("Blue", q.getChoice1());
        assertEquals("Red", q.getChoice2());
        assertEquals("Green", q.getChoice3());
        assertEquals("Black", q.getChoice4());
        assertEquals("a", q.getProfAns());
        assertTrue(q.getState());
        assertEquals(50,q.getSubjectid());
    }


    @Test
    public void testSetLevelValid()
    {
        Question q= new Question("easy", "The sky is :", "blue", "red", "green", "black", "a", true,50);
        q.setLevel("1");
        assertEquals("1", q.getLevel());
        q.setLevel("2");
        assertEquals("2", q.getLevel());
        q.setLevel("3");
        assertEquals("3", q.getLevel());
        assertEquals(50,q.getSubjectid());
    }


    @Test
    public void testSetLevelInvalid()
    {
        Question q= new Question("1", "The sky is :", "blue", "red", "green", "black", "a", true,50);
        q.setLevel("4");
        assertEquals("0", q.getLevel());
    }


    @Test
    public void testSetDescriptionValid()
    {
        Question q= new Question("1", "The sky is :", "blue", "red", "green", "black", "a", true,50);
        q.setDescription("A valid description");
        assertEquals("A valid description", q.getDescription());
    }


    @Test
    public void testSetDescriptionInvalid()
    {
        Question q= new Question("1", "The sky is :", "blue", "red", "green", "black", "a", true,50);
        q.setDescription("");
        assertEquals("", q.getDescription());
    }


    @Test
    public void testSetChoice1()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black",  "a", true,50);
        q.setChoice1("red");
        assertEquals("red", q.getChoice1());
    }


    @Test
    public void testSetChoice1Null()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black",  "a", true,50);
        q.setChoice1(null);
        assertNull(q.getChoice1());
    }


    @Test
    public void testSetChoice2()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black", "a", true,50);
        q.setChoice2("blue");
        assertEquals("blue", q.getChoice2());
    }
    @Test
    public void testSetChoice2WithNull()
    {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "a", true,50);
        q.setChoice2(null);
        assertNull(q.getChoice2());
    }

    @Test
    public void testSetChoice3()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black", "a", true,50);
        q.setChoice3("Choice 3");
        assertEquals("Choice 3", q.getChoice3());
    }

    @Test
    public void testSetChoice3WithNull()
    {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black",  "a", true,50);
        q.setChoice3(null);
        assertNull(q.getChoice3());
    }
    @Test
    public void testSetChoice4()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black",  "a", true,50);
        q.setChoice4("Choice 4");
        assertEquals("Choice 4", q.getChoice4());
    }

    @Test
    public void testSetChoice4WithNull()
    {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black",  "a", true,50);
        q.setChoice4(null);
        assertNull(q.getChoice4());
    }


    @Test
    public void testSetProfAnsValid()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black",  "A", true,50);
        q.setProfAns("B");
        assertEquals("B", q.getProfAns());
    }

    @Test
    public void testSetProfAnsInvalid()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black",  "a", true,50);
        q.setProfAns("x");
        assertEquals("", q.getProfAns());
    }


    @Test
    public void testSetState()
    {
        Question q= new Question("1", "The sky is  :", "blue", "red", "green", "black",  "a", true,50);
        q.setState(true);
        assertTrue(q.getState());
        q.setState(false);
        assertFalse(q.getState());
    }

    @Test
    public void testCheckAnsCorrect()
    {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black",  "a", false,50);
        Student_ans studentAns = new Student_ans("a");
        assertTrue(q.checkAns(q, studentAns));
        assertTrue(q.getState());
    }


    @Test
    public void testCheckAnsIncorrect()
    {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black",  "A", false,50);
        Student_ans studentAns = new Student_ans("B");
        assertFalse(q.checkAns(q, studentAns));
        assertFalse(q.getState());
    }

    @Test
    public void testToString()
    {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black",  "a", true,50);
        String expected = "Q: The sky is:\nA. Blue\nB. Red\nC. Green\nD. Black";
        assertEquals(expected, q.toString());
    }

    @Test
    public void testSetSubjectid() {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "a", true, 50);
        q.setsubjectid(60);
        assertEquals(60, q.getSubjectid());
    }

    @Test
    public void testCheckAnsNullStudentAnswer() {
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "a", false, 50);
        Student_ans studentAns = new Student_ans();  // Simulating a null student answer
        assertFalse(q.checkAns(q, studentAns));  // We expect it to return false
        assertFalse(q.getState());  // The state should be false as answer is incorrect
    }


    @Test
    public void testConstructorWithEmptyDescription() {
        // Test for empty description in the constructor
        Question q = new Question("1", "", "Blue", "Red", "Green", "Black", "a", true, 50);
        assertEquals("", q.getDescription());  // Description should remain empty
    }

    @Test
    public void testConstructorWithNullChoice() {
        // Test for null choices in the constructor
        Question q = new Question("1", "The sky is:", null, null, null, null, "a", true, 50);
        assertNull(q.getChoice1());  // Choice1 should be null
        assertNull(q.getChoice2());  // Choice2 should be null
        assertNull(q.getChoice3());  // Choice3 should be null
        assertNull(q.getChoice4());  // Choice4 should be null
    }

    @Test
    public void testSetProfAnsInvalidCase() {
        // Test for an invalid case in the prof_ans (e.g., lowercase "a")
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "a", true, 50);
        q.setProfAns("p");  // Set an uppercase "A", which is invalid
        assertEquals("", q.getProfAns());  // Prof ans should be set to empty string due to invalid input
    }

    @Test
    public void testSetStateTransition() {
        // Test setting the state multiple times
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "a", false, 50);
        q.setState(true);
        assertTrue(q.getState());  // Ensure state is true after setting

        q.setState(false);
        assertFalse(q.getState());  // Ensure state is false after setting
    }

    @Test
    public void testToStringWithEmptyChoices() {
        // Test toString method when choices are empty
        Question q = new Question("1", "The sky is:", "", "", "", "", "a", true, 50);
        String expected = "Q: The sky is:\nA. \nB. \nC. \nD. ";
        assertEquals(expected, q.toString());  // Verify the string output when choices are empty
    }

    @Test
    public void testSetProfAnsValidation() {
        // Create a new Question instance
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "", true, 50);

        // Test setting valid answers
        q.setProfAns("A");
        assertEquals("A", q.getProfAns());  // Verify that 'A' is correctly set

        q.setProfAns("B");
        assertEquals("B", q.getProfAns());  // Verify that 'B' is correctly set

        q.setProfAns("C");
        assertEquals("C", q.getProfAns());  // Verify that 'C' is correctly set

        q.setProfAns("D");
        assertEquals("D", q.getProfAns());  // Verify that 'D' is correctly set
    }
    @Test
    public void testCheckAnsIncorrection() {
        // Create a Question object with a correct answer "A"
        Question q = new Question("1", "The sky is:", "Blue", "Red", "Green", "Black", "A", false, 50);

        // Create a Student_ans object with an incorrect answer "B"
        Student_ans studentAns = new Student_ans("B");

        // Call checkAns method with the Question and Student_ans objects
        boolean result = q.checkAns(q, studentAns);

        // Assert that the result is false because the answer is incorrect
        assertFalse(result);

        // Assert that the question's state is false, indicating that the answer was wrong
        assertFalse(q.getState());
    }
}

/*package com.example.unitestapplication.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Tests {


    public Tests(int quantity, int score, String level, int id, ArrayList<Question> ListOfQuestions) {
        this.quantity = quantity;
        this.score = score;
        this.level = level;
        this.id=id;
        this.ListOfQuestions = ListOfQuestions;

    }

    


    private int quantity;
    private int id;
    private String level;
    private int score;
    private ArrayList<Question> ListOfQuestions = new ArrayList<Question>();

    ArrayList<Student_ans> List_of_student_ans= new ArrayList<Student_ans>();

    public int getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ArrayList<Question> getListOfQuestions(){return ListOfQuestions;}
    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(String level) {
        if (Objects.equals(level, "1") || Objects.equals(level, "2") || Objects.equals(level, "3")){
            this.level = level;
        } else {
            this.level="0";
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {

        if (quantity<=ListOfQuestions.size()){
            this.quantity = quantity;
        } else {
            this.quantity= -1;
            System.out.println("Invalid amount please enter a number between 0 and "+ ListOfQuestions.size());
        }


    }

    public String printScore(int s, int amount){

        return s+"/"+amount;
    }



    public ArrayList<Question> choose_random_all_questions(int amount, List<Question> QuestionListStudent){
        int counter=0;
        Random random = new Random();
        ArrayList <Question> TempTest = new ArrayList<>();
        ArrayList <Question> helperW = new ArrayList<>();
        ArrayList <Question> helperR = new ArrayList<>();
        for (Question i : QuestionListStudent ){
            if (!i.getState()){
                helperW.add(i);
            } else if (i.getState()){
                helperR.add(i);
            }
        }

        while(counter<amount && (!helperW.isEmpty())){
            int numW= random.nextInt(helperW.size());
            TempTest.add(helperW.get(numW));
            helperW.remove(numW);
            counter++;
        }


        while(counter<amount && (!helperR.isEmpty())) {
            int numR= random.nextInt(helperR.size());
            TempTest.add(helperR.get(numR));
            helperR.remove(numR);
            counter++;
        }
        return TempTest;
    }




    

    public int updatescore(Tests test)
    {
        int right=0;
        for (Question i : test.ListOfQuestions ){
            if (i.getState()){
                right++;
            }
        }

        test.setScore(right);
        int fs=getScore();

        return fs;
    }

    public void setStatequestion(int id,List<Question> QuestionListStudent,boolean s){
        for (Question i : QuestionListStudent ){
            if (i.getId()==id){
                i.setState(s);
            }
        }


    }
    public void setListOfQuestions( ArrayList <Question>tests_questions)
    {
        this.ListOfQuestions=tests_questions;
    }









}
*/
package com.example.unitestapplication.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Tests {

    private int quantity;
    private int id;
    private String level;
    private int score;
    private ArrayList<Question> listOfQuestions = new ArrayList<>();


    public Tests(int quantity, int score, String level, int id, ArrayList<Question> listOfQuestions) {
        this.quantity = quantity;
        this.score = score;
        this.level = level;
        this.id = id;
        this.listOfQuestions = listOfQuestions;
    }

    public int getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ArrayList<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(String level) {
        if (Objects.equals(level, "1") || Objects.equals(level, "2") || Objects.equals(level, "3")) {
            this.level = level;
        } else {
            this.level = "0";  // Default to invalid level
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        if (quantity <= listOfQuestions.size()) {
            this.quantity = quantity;
        } else {
            this.quantity = -1;  // Invalid quantity
            System.out.println("Invalid amount, please enter a number between 0 and " + listOfQuestions.size());
        }
    }

    public String printScore(int s, int amount) {
        return s + "/" + amount;
    }

    public ArrayList<Question> choose_random_all_questions(int amount, List<Question> questionList) {
        int counter = 0;
        Random random = new Random();
        ArrayList<Question> tempTest = new ArrayList<>();
        ArrayList<Question> unselectedQuestions = new ArrayList<>();
        ArrayList<Question> selectedQuestions = new ArrayList<>();

        for (Question question : questionList) {
            if (!question.getState()) {
                unselectedQuestions.add(question);
            } else {
                selectedQuestions.add(question);
            }
        }

        while (counter < amount && !unselectedQuestions.isEmpty()) {
            int randomIndex = random.nextInt(unselectedQuestions.size());
            tempTest.add(unselectedQuestions.get(randomIndex));
            unselectedQuestions.remove(randomIndex);
            counter++;
        }

        while (counter < amount && !selectedQuestions.isEmpty()) {
            int randomIndex = random.nextInt(selectedQuestions.size());
            tempTest.add(selectedQuestions.get(randomIndex));
            selectedQuestions.remove(randomIndex);
            counter++;
        }

        return tempTest;
    }

    public int updateScore(Tests test) {
        int right = 0;
        for (Question question : test.listOfQuestions) {
            if (question.getState()) {
                right++;
            }
        }
        test.setScore(right);
        return getScore();
    }

    public void setQuestionState(int id, List<Question> questionList, boolean state) {
        for (Question question : questionList) {
            if (question.getId() == id) {
                question.setState(state);
            }
        }
    }
}

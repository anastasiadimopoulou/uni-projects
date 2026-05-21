
package com.example.unitestapplication.view.tests_creation;

import android.util.Log;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class tests_creationPresenter {

    private tests_creationView view;
    private SubjectDAO subjectDAO ;
    private TestsDAO testsDAO ;
    private QuestionDAO questionDAO;

    public tests_creationPresenter( QuestionDAO questionDAO, SubjectDAOMemory subjectDAO,TestsDAO testDAO) {
        this.subjectDAO = subjectDAO;
        this.questionDAO = questionDAO;
        this.testsDAO=testDAO;
    }
    public tests_creationPresenter(){}


    public void setView(tests_creationView view)
    {
        this.view = view;
    }

    public tests_creationView getView()
    {
        return view;
    }

    private int id =0;
    private int idCounter=0;
    public int getidcounter(){return idCounter;}

    public int generateUniqueId() {
        return ++idCounter;
    }

    /**
     * selects questions from dao, create test
     * @param subjectId
     * @return
     */
    public int createTests(int subjectId)
    {
        int quantity = view.getquantity();
        int score = 0;
        String level = view.getlevel();
        int newid = generateUniqueId();


        ArrayList<Question> questions = questionDAO.findAll();  // Παίρνεις τις ερωτήσεις από τη βάση δεδομένων
        ArrayList<Question> questions_persubject=questionDAO.findByid(subjectId);
        ArrayList<Question> questions_persubject_splevel=questionDAO.findByLevel(level,questions_persubject);

        Tests newtest = new Tests(quantity, score, level, newid, questions);

        ArrayList<Question> tests_questions=newtest.choose_random_all_questions(quantity,questions_persubject_splevel);
        int uniqueId = (int) System.currentTimeMillis();
        Tests newTest = new Tests(quantity, score, level, uniqueId,tests_questions);
        testsDAO.save(newTest);

        view.showsuccessCreateTest("Tests's Creation", "Your test has been successfully created.");

        return uniqueId;


    }

    /**
     * checks if all data are right (if not shows error message )and calls createTests(int id)
     * @param subjectId
     * @return int
     */

    public int verification(int subjectId){

        int testid=-100;
        String level= view.getlevel();
        int quantity=view.getquantity();
        if (level.isEmpty() && quantity==0){
            view.showErrorMessage("Error!","Missing Fields");
        }
        else if( level.isEmpty()) {
            view.showErrorMessage("Missing Fields.", "Insert level of difficulty");
        }else if(!level.equals("easy") && !level.equals("medium") && !level.equals("hard")) {
            view.showErrorMessage("Wrong.","Insert easy, medium or hard");
        }else if (quantity<=0){
            view.showErrorMessage("Missing Fields.","Insert amount of questions");
        }
        else{
            ArrayList<Question> questions_persubject=questionDAO.findByid(subjectId);
            List<Question> questions_persubject_splevel=questionDAO.findByLevel(level,questions_persubject);
            if (questions_persubject_splevel.size()>=quantity){
                testid=createTests(subjectId);
            }else {
                view.showErrorMessage("Invalid amount of questions","Insert amount of questions");
            }

        }
        return testid;


    }
}


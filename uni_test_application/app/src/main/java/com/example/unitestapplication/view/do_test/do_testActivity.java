package com.example.unitestapplication.view.do_test;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.R;

import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Student_ans;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;
import com.example.unitestapplication.view.showScore.showScoreActivity;
import com.example.unitestapplication.view.tests_creation.tests_creationActivity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class do_testActivity extends AppCompatActivity implements do_testView {

    private Button next;
    private Button answer_1;
    private Button answer_2;
    private Button answer_3;
    private Button answer_4;
    private QuestionDAO questionDAO;

    private do_testViewModel viewModel;
    private do_testPresenter presenter;

    private TestsDAO testDAO = new TestsDAOMemory();

    private AtomicInteger currentIndex = new AtomicInteger(0);
    private AtomicInteger score = new AtomicInteger(0); // Αρχικοποίηση σκορ
    private int currentTestId = -1; // Αρχικό ID τεστ

    private ArrayList<Question> listofquestions;
    private Question currentQuestion;


    /**
     * error message format
     * @param Title
     * @param Message
     */
    @Override
    public void showErrorMessage(String Title, String Message) {
        new AlertDialog.Builder(do_testActivity.this)
                .setCancelable(true)
                .setTitle(Title)
                .setMessage(Message)
                .setPositiveButton("OK", null).create().show();
    }
    Tests newtest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_test);

        // Δημιουργία του ViewModel και σύνδεση με τον Presenter
        viewModel = new ViewModelProvider(this).get(do_testViewModel.class);
        presenter = viewModel.getPresenter();
        presenter.setView(this);

        // Λήψη του test ID από το Intent
        int testid = getIntent().getIntExtra("testid", -1);
        newtest = testDAO.findByid(testid);
        if (newtest == null) {
            Log.e("do_testActivity", "Test not found for id: " + testid);
            finish();
            return;
        }
        listofquestions = newtest.getListOfQuestions();

        // Αρχικοποίηση των στοιχείων του UI
        Button nextButton = findViewById(R.id.next);
        TextView questionText = findViewById(R.id.txt_question);
        TextView answer1 = findViewById(R.id.answer_1);
        TextView answer2 = findViewById(R.id.answer_2);
        TextView answer3 = findViewById(R.id.answer_3);
        TextView answer4 = findViewById(R.id.answer_4);
        EditText students_ansEditText = findViewById(R.id.st_ans);

        // Εμφάνιση της πρώτης ερώτησης
        currentQuestion = listofquestions.get(currentIndex.get());
        questionText.setText(currentQuestion.getDescription());
        answer1.setText(currentQuestion.getChoice1());
        answer2.setText(currentQuestion.getChoice2());
        answer3.setText(currentQuestion.getChoice3());
        answer4.setText(currentQuestion.getChoice4());

        nextButton.setOnClickListener(v -> {
            String student_ans = students_ansEditText.getText().toString().trim();
            if (!student_ans.matches("[A-D]")) {
                Toast.makeText(this, "Please enter a valid answer (A, B, C, or D)", Toast.LENGTH_SHORT).show();
                return;
            }
            if (student_ans.isEmpty()) {
                Toast.makeText(this, "Please enter an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            // Έλεγχος αν η απάντηση είναι σωστή
            Student_ans stans = new Student_ans(student_ans);
            boolean isCorrect = currentQuestion.checkAns(currentQuestion, stans);

            if (isCorrect) {
                score.getAndIncrement(); // Αύξηση του σκορ αν είναι σωστή η απάντηση
            }

            currentIndex.getAndIncrement(); // Αύξηση του index για την επόμενη ερώτηση

            if (currentIndex.get() < listofquestions.size()) {
                // Εμφάνιση της επόμενης ερώτησης
                currentQuestion = listofquestions.get(currentIndex.get());
                questionText.setText(currentQuestion.getDescription());
                answer1.setText(currentQuestion.getChoice1());
                answer2.setText(currentQuestion.getChoice2());
                answer3.setText(currentQuestion.getChoice3());
                answer4.setText(currentQuestion.getChoice4());
                students_ansEditText.setText(""); // Καθαρισμός της απάντησης του φοιτητή
            } else {
                // Τέλος ερωτήσεων - εμφάνιση του σκορ
                newtest.setScore(score.get());
                Toast.makeText(getApplicationContext(), "End of questions", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(do_testActivity.this, showScoreActivity.class);
                intent1.putExtra("score", score.get()); // Περάστε το τελικό σκορ στο Intent
                intent1.putExtra("amountofquestions",newtest.getListOfQuestions().size());
                startActivity(intent1);
                finish();
            }
        });

        // Επαναφορά δεδομένων αν έχουμε διακοπή και επαναφορά της δραστηριότητας
        if (savedInstanceState != null) {
            currentTestId = savedInstanceState.getInt("currentTestId", -1);
            score.set(savedInstanceState.getInt("currentScore", 0));
            currentIndex.set(savedInstanceState.getInt("currentIndex", 0));
        }
    }



    /**
     * saves current data of the test
     */
    private void saveCurrentTestData() {
        Tests currentTest = new Tests(newtest.getQuantity(),newtest.getScore(),newtest.getLevel(), newtest.getId(), newtest.getListOfQuestions());
        testDAO.save(currentTest);
    }

    /**
     * save the state of the test
     * @param outState
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Αποθήκευση της κατάστασης του τεστ
        outState.putInt("currentTestId", currentTestId);
        outState.putInt("currentScore", score.get());
        outState.putInt("currentIndex", currentIndex.get());
    }


    /**
     * helps not loosing data when changing screens
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Επαναφορά δεδομένων όταν η δραστηριότητα επιστρέφει στο προσκήνιο
        if (savedInstanceState != null) {
            currentTestId = savedInstanceState.getInt("currentTestId", -1);
            score.set(savedInstanceState.getInt("currentScore", 0));
            currentIndex.set(savedInstanceState.getInt("currentIndex", 0));
        }
    }
}
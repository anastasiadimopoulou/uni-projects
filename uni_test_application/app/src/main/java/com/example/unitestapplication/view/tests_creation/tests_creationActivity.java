package com.example.unitestapplication.view.tests_creation;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;
import com.example.unitestapplication.view.do_test.do_testActivity;
import com.example.unitestapplication.view.log_in.LogInActivity;
import com.example.unitestapplication.view.showSubject.showSubjectActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class tests_creationActivity extends AppCompatActivity implements tests_creationView{


    private String level;

    private tests_creationPresenter presenter;

    private QuestionDAO questionDAO=new QuestionDAOmemory();
    TestsDAO testsDAO = (TestsDAO) new TestsDAOMemory();
    SubjectDAOMemory subjectDAO ;




    private tests_creationViewModel viewModel=new tests_creationViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tests_creation);
        presenter = new tests_creationPresenter(questionDAO, subjectDAO, testsDAO);
        presenter.setView(this);
        viewModel= new ViewModelProvider(this).get(tests_creationViewModel.class);
        viewModel.getPresenter().setView(this);
        int subjectId;
        questionDAO = new QuestionDAOmemory();
        subjectDAO = new SubjectDAOMemory();
        testsDAO = new TestsDAOMemory();

        // Λήψη δεδομένων από το Intent
        Intent intent = getIntent();
        if (intent != null) {
            subjectId = intent.getIntExtra("subjectid", -1); // Προσαρμογή σε default τιμή -1
        }
        else
        {
            subjectId=-1;
        }

        Button create_test= (Button) findViewById(R.id.create_test);

        create_test.setOnClickListener(v -> {

            this.fixlevel();
            int testid=-100;
            if (getlevel().isEmpty() || getquantity()==0){
                testid=viewModel.getPresenter().verification(subjectId);
            } else if (!getlevel().isEmpty() && getquantity()>=1){
                testid=viewModel.getPresenter().verification(subjectId);
                create_test.setEnabled(true);
            }
            int testidintent=testid;
            Intent intent1 = new Intent(tests_creationActivity.this, do_testActivity.class);
            intent1.putExtra("testid",  testidintent);
            startActivity(intent1);

        });

        Button btnBack = findViewById(R.id.button_tests_creation_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tests_creationActivity.this, showSubjectActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public int getquantity() {
        EditText Equantity = findViewById(R.id.edit_text_amount);
        int quantity =Integer.parseInt(Equantity.getText().toString().trim());
        return quantity;
    }

    public String getlevel(){
        EditText testLevelEditText = findViewById(R.id.edit_text_level);
        return testLevelEditText.getText().toString();
    }

    @Override
    public int getid() {
        return 0;
    }



    @Override
    public void fixlevel() {
        String level = getlevel();
        if (Objects.equals(level, "easy") || Objects.equals(level, "medium") || Objects.equals(level, "hard")) {
            this.level = level; // Αποθηκεύουμε το επίπεδο όπως είναι
        } else {
            this.level = ""; // Αν δεν είναι σωστό επίπεδο, το μηδενίζουμε
        }
    }



    @Override
    public void setlevel(String level) {
        ((EditText)findViewById(R.id.edit_text_level)).setText(level);
    }

    @Override
    public void setQuantity(int q) {
        ((android.widget.EditText)findViewById(R.id.edit_text_amount)).setSelection(q);
    }

    @Override
    public void showsuccessCreateTest(String Title, String Message) {
        new AlertDialog.Builder(tests_creationActivity.this)
                .setCancelable(true)
                .setTitle(Title)
                .setMessage(Message)
                .setPositiveButton("OK", null)
                .create()
                .show();

    }

    @Override
    public void showErrorMessage(String Title, String Message) {
        new AlertDialog.Builder(tests_creationActivity.this)
                .setCancelable(true)
                .setTitle(Title)
                .setMessage(Message)
                .setPositiveButton("OK", null).create().show();
    }

    //12/1
    int currentTestId= viewModel.getPresenter().getidcounter();

    /**
     * helps saving data when changing screens
     * @param outState Bundle in which to place your saved state.
     *
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentTestId", currentTestId);
    }

    /**
     * helps saving data when changing screens
     * @param savedInstanceState Bundle in which to place your saved state.
     *
     */

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            currentTestId = savedInstanceState.getInt("currentTestId", -1);
        }
    }

}



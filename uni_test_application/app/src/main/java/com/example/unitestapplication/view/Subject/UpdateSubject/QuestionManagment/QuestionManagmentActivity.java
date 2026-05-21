/*package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unitestapplication.MainActivity;
import com.example.unitestapplication.R;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionActivity;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion.DeleteQuestionActivity;

public class QuestionManagmentActivity extends AppCompatActivity implements QuestionManagmentView{
    private static final int CREATE_QUESTION_REQUEST_CODE = 1;*/

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question_managmemt);


        Button createQuestionButton = findViewById(R.id.button_create_question);
        createQuestionButton.setOnClickListener(v -> {
            // Δημιουργία Intent για τη μετάβαση στη CreateQuestionActivity
            //Intent intent = new Intent(QuestionManagmentActivity.this, CreateQuestionActivity.class);
            //startActivity(intent);  // Έναρξη της νέας δραστηριότητας
            Intent intent = new Intent(QuestionManagmentActivity.this, CreateQuestionActivity.class);
            startActivityForResult(intent, CREATE_QUESTION_REQUEST_CODE);
            finish();

        });


        Button deleteQuestionButton = findViewById(R.id.button_delete_question);
        deleteQuestionButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionManagmentActivity.this, DeleteQuestionActivity.class);
            startActivity(intent);
            finish();
        });

        //finish();
    }*/


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question_managmemt);
        Log.d("QuestionManagmentActivity", "Activity created");

        Button createQuestionButton = findViewById(R.id.button_create_question);
        createQuestionButton.setOnClickListener(v -> {
            Log.d("QuestionManagmentActivity", "Navigating to CreateQuestionActivity");
            Intent intent = new Intent(QuestionManagmentActivity.this, CreateQuestionActivity.class);
            startActivityForResult(intent, CREATE_QUESTION_REQUEST_CODE);
        });

        Button deleteQuestionButton = findViewById(R.id.button_delete_question);
        deleteQuestionButton.setOnClickListener(v -> {
            Log.d("QuestionManagmentActivity", "Navigating to DeleteQuestionActivity");
            Intent intent = new Intent(QuestionManagmentActivity.this, DeleteQuestionActivity.class);
            startActivity(intent); // Κατευθείαν στη DeleteQuestionActivity
        });
    }*/
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question_managmemt);
        Log.d("QuestionManagmentActivity", "Activity created");

        Button createQuestionButton = findViewById(R.id.button_create_question);
        createQuestionButton.setOnClickListener(v -> {
            Log.d("QuestionManagmentActivity", "Navigating to CreateQuestionActivity");
            Intent intent = new Intent(QuestionManagmentActivity.this, CreateQuestionActivity.class);
            startActivityForResult(intent, CREATE_QUESTION_REQUEST_CODE);
        });

        Button deleteQuestionButton = findViewById(R.id.button_delete_question);
        deleteQuestionButton.setOnClickListener(v -> {
            Log.d("QuestionManagmentActivity", "Navigating to DeleteQuestionActivity");
            Intent intent = new Intent(QuestionManagmentActivity.this, DeleteQuestionActivity.class);
            startActivity(intent); // Removed the finish() here to prevent closing this activity
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_QUESTION_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d("QuestionManagmentActivity", "Question created successfully.");
        }
    }


}
*/
package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unitestapplication.R;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionActivity;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion.DeleteQuestionActivity;

public class QuestionManagmentActivity extends AppCompatActivity implements QuestionManagmentView {

    private static final int CREATE_QUESTION_REQUEST_CODE = 1;
    private static final int DELETE_QUESTION_REQUEST_CODE = 2;  // Αν χρειαστεί να κάνεις κάτι με τα αποτελέσματα διαγραφής

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question_managmemt);

        int subjectId = getIntent().getIntExtra("subjectId", -1);

        if (subjectId != -1) {
        } else {
        }
        Button createQuestionButton = findViewById(R.id.button_create_question);
        createQuestionButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionManagmentActivity.this, CreateQuestionActivity.class);
            intent.putExtra("subjectId", subjectId); // Πρέπει να υπάρχει `getId()` στο Subject

            startActivity(intent);
        });

        Button deleteQuestionButton = findViewById(R.id.button_delete_question);
        deleteQuestionButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionManagmentActivity.this, DeleteQuestionActivity.class);
            intent.putExtra("subjectId", subjectId); // Πρέπει να υπάρχει `getId()` στο Subject
            startActivity(intent);
        });

        Button back = findViewById(R.id.button_question_managment_back);
        back.setOnClickListener(v -> {
            finish();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CREATE_QUESTION_REQUEST_CODE) {
                Log.d("QuestionManagmentActivity", "Question created successfully.");

            } else if (requestCode == DELETE_QUESTION_REQUEST_CODE) {
                Log.d("QuestionManagmentActivity", "Question deleted successfully.");

            }
        }
    }
}

package com.example.unitestapplication.view.Subject.CreateSubject;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.MainActivity;
import com.example.unitestapplication.R;
import android.text.TextWatcher;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectView;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectPresenter;



import androidx.appcompat.app.AppCompatActivity;

public class CreateSubjectActivity extends AppCompatActivity implements CreateSubjectView {
    private CreateSubjectViewModel viewModel;
    private  Button create_account_button;
    SubjectDAO subjectdao=new SubjectDAOMemory();
    int professorid;

    @Override
    public String getname(){
        return ((EditText)findViewById(R.id.edit_text_subject_name)).getText().toString().trim();

    }

    /**
     * getid with try catch Exception
     * @return
     */
    @Override
    public int getid() {
        try {
            String idText = ((EditText) findViewById(R.id.edit_text_subject_id)).getText().toString().trim();
            return Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input, returning 0 as default.");
            return 0;
        }
    }

    /**
     * getsemester with try catch Exception
     * @return
     */
    @Override
    public int getsemester() {
        try {
            String semesterText = ((EditText) findViewById(R.id.edit_text_subject_semester)).getText().toString().trim();
            return Integer.parseInt(semesterText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid semester input, returning 0 as default.");
            return 0;
        }
    }

    @Override
    public int getprofid() {
        return this.professorid;
    }


    @Override
    public void showErrorMessage(String Title, String Message) {
        {
            new AlertDialog.Builder(CreateSubjectActivity.this)
                    .setCancelable(true)
                    .setTitle(Title)
                    .setMessage(Message)
                    .setPositiveButton("OK", null).create().show();
        }
    }

    @Override
    public void showsuccessCreateSubject(String Title, String Message) {
        new AlertDialog.Builder(CreateSubjectActivity.this)
                .setCancelable(true)
                .setTitle(Title)
                .setMessage(Message)
                .setPositiveButton("OK", null)
                .create()
                .show();


    }



    public void setname(String name) {
        ((EditText)findViewById(R.id.edit_text_subject_name)).setText(name);
    }


    public void setid(int id) {
        ((android.widget.EditText)findViewById(R.id.edit_text_subject_id)).setSelection(id);
    }


    public void setsemester(int semester) {
        ((android.widget.EditText)findViewById(R.id.edit_text_subject_semester)).setSelection(semester);
    }

    @Override
    public void setsprofid(int profid) {
        this.professorid=profid;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);
        viewModel = new ViewModelProvider(this).get(CreateSubjectViewModel.class);
        viewModel.getPresenter().setView(this);
        professorid=getIntent().getIntExtra("professorid",-1);
        Log.e("Professor id in create activity ","Professor id in create activity "+professorid);


        Button create_subject_button= (Button) findViewById(R.id.button_create_subject);
       // create_subject_button.setOnClickListener(v->viewModel.getPresenter().verification());
        EditText subjectNameEditText = findViewById(R.id.edit_text_subject_name);
        EditText subjectidEditText = findViewById(R.id.edit_text_subject_id);
        EditText subjectSemesterEditText = findViewById(R.id.edit_text_subject_semester);

        create_subject_button.setOnClickListener(v -> {
            String name = subjectNameEditText.getText().toString();


            int semester=0;
            if (!subjectSemesterEditText.getText().toString().isEmpty())
            {
                semester=Integer.parseInt(subjectSemesterEditText.getText().toString().trim());
            }



            int id;
            if(subjectidEditText.getText().toString().isEmpty())
            {
                id=0;
            }
            else {
                id=Integer.parseInt(subjectidEditText.getText().toString());
            }

            // Έλεγχος εγκυρότητας εξάμηνου
            if (semester < 1 || semester > 8) {
                showErrorMessage("Invalid Semester", "Semester must be between 1 and 8.");
                return; // Διακοπή της διαδικασίας αν το εξάμηνο είναι εκτός εύρους
            }
            if (name.isEmpty()|| id==0 ||semester==0)
            {
                viewModel.getPresenter().verification();
                //create_subject_button.setEnabled(false);

            }

            else if (!name.isEmpty() && id!=0 && semester!=0){
                viewModel.getPresenter().verification();
                create_subject_button.setEnabled(true);


            }

            finish();  // Κλείσιμο της οθόνης δημιουργίας και επιστροφή στην αρχική οθόνη
        });

        Button back = findViewById(R.id.button_create_subject_back);
        back.setOnClickListener(v -> {
            finish();
        });


    }

    /**
     * starts professor menu
     */
    public void openbasicprofessormenu(){
        Intent intent = new Intent(CreateSubjectActivity.this, MainActivity.class);
        startActivity(intent);


    }



}

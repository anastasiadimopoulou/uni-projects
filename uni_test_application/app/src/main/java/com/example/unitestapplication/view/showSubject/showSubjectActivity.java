package com.example.unitestapplication.view.showSubject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.LoginActivity;
import com.example.unitestapplication.R;

import com.example.unitestapplication.domain.Subject ;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.log_in.LogInActivity;
import com.example.unitestapplication.view.tests_creation.tests_creationActivity;


import java.util.List;

public class showSubjectActivity extends AppCompatActivity implements com.example.unitestapplication.view.showSubject.showSubjectView,
        com.example.unitestapplication.view.showSubject.showSubjectRecyclerViewAdapter.ItemSelectionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_subject);
        com.example.unitestapplication.view.showSubject.showSubjectViewModel viewModel =
                new ViewModelProvider(this).get(com.example.unitestapplication.view.showSubject.showSubjectViewModel.class);
        viewModel.getPresenter().setView((com.example.unitestapplication.view.showSubject.showSubjectView) this);
        Button btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showSubjectActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }




    @Override
    protected void onResume() {
        super.onResume();

        // Ανάκτηση των μαθημάτων από το DAO
        SubjectDAO subjectDAO = new SubjectDAOMemory();
        List<Subject> subjectList = subjectDAO.findAll();  // Ανακτούμε τα δεδομένα από το DAO

        // Ενημέρωση του RecyclerView με τη νέα λίστα μαθημάτων
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ενημέρωση του Adapter με τα δεδομένα
        com.example.unitestapplication.view.showSubject.showSubjectRecyclerViewAdapter adapter = new com.example.unitestapplication.view.showSubject.showSubjectRecyclerViewAdapter(subjectList, this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * starts the next screen with all important data
     * @param subject
     */



    @Override
    public void selectsubject(Subject subject)

    {

        Intent intent = new Intent(this, tests_creationActivity.class);
        intent.putExtra("subjectid", subject.getid()); // Πρέπει να υπάρχει `getId()` στο Subject
        Log.e("SUBJECTID","SUBJECTID meta to intent"+subject.getid());
        startActivity(intent);
    }


}



package com.example.unitestapplication.view.Subject.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.R;

import com.example.unitestapplication.domain.Subject ;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;


import java.util.ArrayList;
import java.util.List;

public class SubjectSearchActivity extends AppCompatActivity implements SubjectSearchView,
        SubjectRecyclerViewAdapter.ItemSelectionListener {

    int professorid;
    SubjectSearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_search);

         viewModel =
                new ViewModelProvider(this).get(SubjectSearchViewModel.class);
        viewModel.getPresenter().setView(this);

        professorid=getIntent().getIntExtra("professorid",-1);
        Log.e("Professor id in subject search activity ","Professor id in subject search activity "+professorid);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(SubjectSearchViewModel.class);
        viewModel.getPresenter().setView(this);
        // Λήψη των μαθημάτων από το SubjectDAO
        List<Subject> subjectList = new ArrayList<>(viewModel.getPresenter().search(professorid));
        recyclerView.setAdapter(new SubjectRecyclerViewAdapter(subjectList, this));

        Button back = findViewById(R.id.button_delete_subject_back);
        back.setOnClickListener(v -> {
            finish();
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        List<Subject> subjectList = viewModel.getPresenter().search(professorid);

        // Εμφάνιση των μαθημάτων στη RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SubjectRecyclerViewAdapter adapter = new SubjectRecyclerViewAdapter(subjectList, this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * deletes subject from dao
     * refresh list of subject user can see
     * @param subject
     */
    @Override
    public void selectsubject(Subject subject) {
        SubjectDAO subjectDAO = new SubjectDAOMemory();
        subjectDAO.delete(subject);  // Διαγραφή από το DAO

        List<Subject> subjectList = subjectDAO.findByid(professorid);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SubjectRecyclerViewAdapter adapter = new SubjectRecyclerViewAdapter(subjectList, this);
        recyclerView.setAdapter(adapter);
    }




}

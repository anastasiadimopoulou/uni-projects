package com.example.unitestapplication.view.Subject.Monitor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Subject ;
import com.example.unitestapplication.domain.Tests;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.memoryDAO.TestsDAOMemory;
import com.example.unitestapplication.view.Subject.Monitor.showsuccess.showsuccessActivity;
import com.example.unitestapplication.view.Subject.Search.SubjectRecyclerViewAdapter;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.QuestionManagmentActivity;
import com.example.unitestapplication.view.do_test.do_testActivity;
import com.example.unitestapplication.view.showScore.showScoreActivity;

import java.util.ArrayList;
import java.util.List;


public class ListsubjectsActivity extends AppCompatActivity implements ListsubjectsView,SubjectRecyclerViewAdapter.ItemSelectionListener
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitorlist);

        int professorId = getIntent().getIntExtra("professorid", -1);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SubjectDAO subjectDAO = new SubjectDAOMemory();
        List<Subject> subjectList = subjectDAO.findByid(professorId);

        recyclerView.setAdapter(new SubjectRecyclerViewAdapter(subjectList, this));

        Button back = findViewById(R.id.button_monitor_back);
        back.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int professorId = getIntent().getIntExtra("professorid", -1);
        SubjectDAO subjectDAO = new SubjectDAOMemory();

        List<Subject> subjectList = subjectDAO.findByid(professorId);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SubjectRecyclerViewAdapter adapter = new SubjectRecyclerViewAdapter(subjectList, this);
        recyclerView.setAdapter(adapter);
    }

    TestsDAO testsdao;

    /**
     * selects subject from subject dao and updates totalscore , starts next screen
     * @param subject
     */
    @Override
    public void selectsubject(Subject subject) {
        int scoreall = 0;
        int scorecorrect = 0;
        testsdao = new TestsDAOMemory();

        // Λήψη όλων των τεστ
        List<Tests> allTests = testsdao.findAll();

        // Αναζήτηση τεστ για το συγκεκριμένο μάθημα
        List<Tests> subjectTests = new ArrayList<>();
        for (Tests test : allTests) {
            if (!test.getListOfQuestions().isEmpty() &&
                    test.getListOfQuestions().get(0).getSubjectid() == subject.getid()) {
                subjectTests.add(test);
                scoreall += test.getListOfQuestions().size();
                scorecorrect += test.getScore();
            }
        }

        Intent intent = new Intent(ListsubjectsActivity.this, showsuccessActivity.class);
        intent.putExtra("all", scoreall); // Περάστε το τελικό σκορ στο Intent
        intent.putExtra("correct", scorecorrect);
        startActivity(intent);
        finish();
    }




}

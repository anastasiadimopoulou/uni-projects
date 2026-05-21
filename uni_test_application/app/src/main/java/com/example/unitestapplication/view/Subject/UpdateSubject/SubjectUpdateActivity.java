package com.example.unitestapplication.view.Subject.UpdateSubject;
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
import com.example.unitestapplication.view.Subject.Search.SubjectRecyclerViewAdapter;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.QuestionManagmentActivity;
import java.util.ArrayList;
import java.util.List;


public class SubjectUpdateActivity extends AppCompatActivity implements SubjectUpdateView,SubjectRecyclerViewAdapter.ItemSelectionListener
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);
        SubjectUpdateViewModel viewModel =
                new ViewModelProvider(this).get(SubjectUpdateViewModel.class);
        viewModel.getPresenter().setView((SubjectUpdateView) this);
        int professorId = getIntent().getIntExtra("professorId", -1);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(SubjectUpdateViewModel.class);
        viewModel.getPresenter().setView((SubjectUpdateView) this);
        // Λήψη των μαθημάτων από το SubjectDAO
        List<Subject> subjectList = new ArrayList<>(viewModel.getPresenter().getBookDAO().findByid(professorId));
        recyclerView.setAdapter(new SubjectRecyclerViewAdapter(subjectList, this));

        Button back = findViewById(R.id.button_update_subject_back);
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

    /**
     * starts next screen with all important data
     * @param subject
     */

    @Override
    public void selectsubject(Subject subject)
    {
        Intent intent = new Intent(this, QuestionManagmentActivity.class);
        intent.putExtra("subjectId", subject.getid()); // Πρέπει να υπάρχει `getId()` στο Subject

        startActivity(intent);
    }

}

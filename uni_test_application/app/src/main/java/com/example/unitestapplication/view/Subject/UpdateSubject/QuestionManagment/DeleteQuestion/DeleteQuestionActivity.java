package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitestapplication.DAO.QuestionDAO;

import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Question;

import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.view.Subject.Search.SubjectRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeleteQuestionActivity extends AppCompatActivity implements DeleteQuestionView,
        QuestionRecyclerViewAdapter.ItemSelectionListener {

    List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_question);


        int subjectId = getIntent().getIntExtra("subjectId", -3);


        DeleteQuestionViewModel viewModel = new ViewModelProvider(this).get(DeleteQuestionViewModel.class);
        viewModel.getPresenter().setView(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        questionList=new ArrayList<>(viewModel.getPresenter().search(subjectId));
        recyclerView.setAdapter(new QuestionRecyclerViewAdapter(questionList, this));

        Button back = findViewById(R.id.button_delete_question_back);
        back.setOnClickListener(v -> {
            finish();
        });
    }





    @Override
    protected void onResume() {
        super.onResume();

        // Ενημέρωσε τη λίστα ερωτήσεων
        QuestionDAO questionDAO = new QuestionDAOmemory();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        QuestionRecyclerViewAdapter adapter = new QuestionRecyclerViewAdapter(questionList, this);
        recyclerView.setAdapter(adapter);

    }

    /**
     * deletes question given
     * refresh dao with questions
     * @param question
     */
    @Override
    public void selectquestion(Question question) {
        QuestionDAO questionDAO = new QuestionDAOmemory();
        questionDAO.delete(question);  // Διαγραφή από το DAO

        List<Question> questionList = questionDAO.findByid(question.getSubjectid());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        QuestionRecyclerViewAdapter adapter = new QuestionRecyclerViewAdapter(questionList, this);
        recyclerView.setAdapter(adapter);

    }
}

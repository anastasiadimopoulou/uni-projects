package com.example.unitestapplication.view.showScore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.unitestapplication.MainActivity;
import com.example.unitestapplication.R;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectActivity;
import com.example.unitestapplication.view.showScore.showScorePresenter;
import com.example.unitestapplication.view.showScore.showScoreView;
import com.example.unitestapplication.view.showScore.showScoreViewModel;
import com.example.unitestapplication.view.showSubject.showSubjectActivity;


public class showScoreActivity extends AppCompatActivity implements showScoreView {

    private showScoreViewModel viewModel;
    private showScorePresenter presenter = new showScorePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_score);

        viewModel = new ViewModelProvider(this).get(showScoreViewModel.class);
        int score = getIntent().getIntExtra("score", -1);
        int amountofquestions = getIntent().getIntExtra("amountofquestions",-1);

        presenter = viewModel.getPresenter();

        presenter.setView(this);

        TextView score1 = findViewById(R.id.score);
        //score1.setText(String.valueOf(score));
        score1.setText(String.valueOf(score+"/"+amountofquestions));

        Button back_button= (Button) findViewById(R.id.button_back_to_basic_student_menu);
        back_button.setOnClickListener(v->viewModel.getPresenter().goToBasic_student_menu());
    }

    /**
     * goes to student start screen
     */
    @Override
    public void openbasicstudentmenu() {
        Intent intent = new Intent(showScoreActivity.this, showSubjectActivity.class);
        startActivity(intent);


    }





}

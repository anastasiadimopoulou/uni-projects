package com.example.unitestapplication.view.Subject.Monitor.showsuccess;

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


public class showsuccessActivity extends AppCompatActivity implements showsuccessView {

    private showsuccessViewModel viewModel;
    private showsuccessPresenter presenter = new showsuccessPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_success);

        // Δημιουργία του ViewModel και σύνδεση με τον Presenter
        viewModel = new ViewModelProvider(this).get(showsuccessViewModel.class);
        int scoreall = getIntent().getIntExtra("all", -1);
        int scorecorrect = getIntent().getIntExtra("correct", -1);


        // Αρχικοποιήστε τον Presenter
        presenter = viewModel.getPresenter();
        // Κάντε set το View
        presenter.setView(this);

        TextView score = findViewById(R.id.success);
        score.setText(String.valueOf(scorecorrect+"/"+scoreall));

        Button back_button= (Button) findViewById(R.id.button_back_to_basic_professor_menu);
        back_button.setOnClickListener(v->viewModel.getPresenter().goToBasic_professor_menu());

    }

    @Override
    public void openbasicprofessormenu() {
        Intent intent = new Intent(showsuccessActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


    }





}

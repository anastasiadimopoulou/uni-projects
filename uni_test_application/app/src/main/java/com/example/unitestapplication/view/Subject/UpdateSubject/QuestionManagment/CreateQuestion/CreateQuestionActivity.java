package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.SubjectDAO;
import com. example. unitestapplication.domain.Question;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.unitestapplication.MainActivity;
import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectActivity;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectViewModel;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.QuestionManagmentActivity;
import com. example. unitestapplication. view. Subject. UpdateSubject. QuestionManagment.QuestionManagmentActivity;
public class CreateQuestionActivity extends AppCompatActivity implements CreateQuestionView {
    private CreateQuestionViewModel viewModel;
    public int  id=0;

    @Override
    public String getlevel(){
        return ((EditText)findViewById(R.id.quest_level)).getText().toString().trim();

    }
    @Override
    public String getdescription(){
        return ((EditText)findViewById(R.id.quest_description)).getText().toString().trim();

    }
    @Override
    public String getchoice1(){
        return ((EditText)findViewById(R.id.quest_choiceA)).getText().toString().trim();

    }
    @Override
    public String getchoice2(){
        return ((EditText)findViewById(R.id.quest_choiceB)).getText().toString().trim();

    }
    @Override
    public String getchoice3(){
        return ((EditText)findViewById(R.id.quest_choiceC)).getText().toString().trim();

    }
    @Override
    public String getchoice4(){
        return ((EditText)findViewById(R.id.quest_choiceD)).getText().toString().trim();

    }
    @Override
    public String getprofans(){
        return ((EditText)findViewById(R.id.quest_prof_ans)).getText().toString().trim();

    }


    @Override
    public void showErrorMessage(String Title, String Message) {
        {
            new AlertDialog.Builder(CreateQuestionActivity.this)
                    .setCancelable(true)
                    .setTitle(Title)
                    .setMessage(Message)
                    .setPositiveButton("OK", null).create().show();
        }
    }
    @Override
    public void showsuccessCreateQuestion(String Title, String Message) {
        new AlertDialog.Builder(CreateQuestionActivity.this)
                .setCancelable(true)
                .setTitle(Title)
                .setMessage(Message)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    /**
     * extracts level
     * @param l
     */
    @Override
    public void setlevel(String l) {
        ((EditText)findViewById(R.id.quest_level)).setText(l);
    }

    /**
     * extracts description
     * @param d
     */
    @Override
    public void  setdescripton(String d) {
        ((EditText)findViewById(R.id.quest_description)).setText(d);
    }

    /**
     * extracts choice 1
     * @param ch1
     */
    @Override
    public void  setchoice1(String ch1) {
        ((EditText)findViewById(R.id.quest_choiceA)).setText(ch1);
    }

    /**
     * extracts choice 2
     * @param ch2
     */
    @Override
    public void  setchoice2 (String ch2) {
        ((EditText)findViewById(R.id.quest_choiceB)).setText(ch2);
    }

    /**
     * extracts choice 3
     * @param ch3
     */
    @Override
    public void  setchoice3(String ch3) {
        ((EditText)findViewById(R.id.quest_choiceC)).setText(ch3);
    }

    /**
     * extracts choice 4
     * @param ch4
     */
    @Override
    public void  setchoice4(String ch4) {
        ((EditText)findViewById(R.id.quest_choiceD)).setText(ch4);
    }

    /**
     * extracts professors answer
     * @param pans
     */
    @Override
    public void  setprofans(String pans) {
        ((EditText)findViewById(R.id.quest_prof_ans)).setText(pans);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_question);

        viewModel = new ViewModelProvider(this).get(CreateQuestionViewModel.class);
        viewModel.getPresenter().setView(this);

        int subjectId = getIntent().getIntExtra("subjectId", -2);

        Button create_question_button = findViewById(R.id.button_create_quest);
        EditText questionlevelEditText = findViewById(R.id.quest_level);
        EditText questiondescriptionEditText = findViewById(R.id.quest_description);
        EditText questionch1EditText = findViewById(R.id.quest_choiceA);
        EditText questionch2EditText = findViewById(R.id.quest_choiceB);
        EditText questionch3EditText = findViewById(R.id.quest_choiceC);
        EditText questionch4EditText = findViewById(R.id.quest_choiceD);
        EditText questionprofansEditText = findViewById(R.id.quest_prof_ans);

        create_question_button.setOnClickListener(v -> {
            String level = questionlevelEditText.getText().toString().trim();
            String description = questiondescriptionEditText.getText().toString().trim();
            String choice1 = questionch1EditText.getText().toString().trim();
            String choice2 = questionch2EditText.getText().toString().trim();
            String choice3 = questionch3EditText.getText().toString().trim();
            String choice4 = questionch4EditText.getText().toString().trim();
            String profans = questionprofansEditText.getText().toString().trim();

            // Κλήση του presenter για έλεγχο και δημιουργία ερώτησης
            viewModel.getPresenter().verification(subjectId);
            finish();
        });

        Button back_button = findViewById(R.id.button_back_quest);
        back_button.setOnClickListener(v -> viewModel.getPresenter().goToQuestionManagmentMenu());
    }

    /**
     * starts next screen with all important data
     */
    public void openCreateDeleteQuest(){
        Intent intent = new Intent(CreateQuestionActivity.this, QuestionManagmentActivity.class);
        startActivity(intent);


    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent); // Ενημέρωσε ότι ολοκληρώθηκε επιτυχώς
        super.finish();
    }







}
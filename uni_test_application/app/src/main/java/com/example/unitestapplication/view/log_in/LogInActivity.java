package com.example.unitestapplication.view.log_in;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.unitestapplication.MainActivity;
import com.example.unitestapplication.R;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionActivity;
import com.example.unitestapplication.view.showSubject.showSubjectActivity;
import com.example.unitestapplication.view.sing_up.Professor.SingUpProfessorActivity;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentActivity;

public class LogInActivity extends AppCompatActivity implements LogInView {
    private LogInViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        viewModel = new ViewModelProvider(this).get(LogInViewModel.class);
        viewModel.getPresenter().setView(this);


        Button login_button= (Button) findViewById(R.id.btn_login);
        Button create_student_account_button= (Button) findViewById(R.id.btn_singupAsStudent);
        Button create_professor_account_button=(Button) findViewById(R.id.btn_singupasProff);

         create_professor_account_button.setOnClickListener(v->viewModel.getPresenter().goToSingupProfessor());
        create_student_account_button.setOnClickListener(v->viewModel.getPresenter().goToSingupStudent());
         login_button.setOnClickListener(v->viewModel.getPresenter().verification());




        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
    }

    /**
     * Extracts the Password from the UI
     * @return String
     */
    public String extractPassword(){
        EditText password=  (EditText)findViewById(R.id.passwordText);
        return password.getText().toString();

    }

    /**
     * Extracts the Username from the UI
     * @return String
     */
    public String extractUsername(){
        EditText username=  (EditText)findViewById(R.id.usernameText);
        return username.getText().toString();

    }

    /**
     * Shows an  custom Error Message
     * @param  title
     * @param message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(LogInActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Start  Sign up Professor Activity
     */
    public void openSignupProfessorActivity(){ //goes to sign up page
        Intent intent = new Intent(LogInActivity.this, SingUpProfessorActivity.class);
        startActivity(intent);
    }

    /**
     * Start Sing Up Student Activity
     */
    public void openSignupStudentActivity(){ //goes to sign up page
        Intent intent = new Intent(LogInActivity.this, SingUpStudentActivity.class);
        startActivity(intent);
    }


    /**
     * Starts  show Subject Activity
     * goes to student home page
     *
     * @param stid
     */
    public void openStudentHomePageActivity(int stid){ //goes to student home page
        Intent intent = new Intent(LogInActivity.this, showSubjectActivity.class);
        intent.putExtra("studentid",stid);

        startActivity(intent);
    }

    /**
     *Starts  MainActivity
     * goes to professor home page
     * @param pid
     */

    public void openProfessorHomePageActivity(int pid){ //goes to  professor home page
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        intent.putExtra("professorid",pid);
        startActivity(intent);
    }
}
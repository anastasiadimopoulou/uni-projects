package com.example.unitestapplication.view.sing_up.Professor;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.unitestapplication.R;
import com.example.unitestapplication.view.log_in.LogInActivity;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentActivity;
import com.example.unitestapplication.view.sing_up.Student.SingUpStudentViewModel;

public class SingUpProfessorActivity extends AppCompatActivity  implements  SingUpProfessorView{
    private SingUpProfessorViewModel viewModel;
    private  Button create_account_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sing_up_professor);
        viewModel = new ViewModelProvider(this).get(SingUpProfessorViewModel.class);
        viewModel.getPresenter().setView(this);
        Button back_button= (Button) findViewById(R.id.btnBack);
        Button create_account_button= (Button) findViewById(R.id.btnCreateProfessor);
        back_button.setOnClickListener(v->viewModel.getPresenter().goToLogIn());
        create_account_button.setOnClickListener(v->viewModel.getPresenter().verification());







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Starts  LOG IN ACTIVITY
     */
    public void openLogInActivity(){
        Intent intent = new Intent(SingUpProfessorActivity.this, LogInActivity.class);
        intent.putExtra("professorid",this.extractProfessorId());
        startActivity(intent);


    }

    /**
     * EXTRACTS PROFESSOR PASSWORD FROM UI
     * @return String
     */
    public String extractProfessorPassword(){
        EditText password=  (EditText)findViewById(R.id.PasswordProfessorText);
        return password.getText().toString();

    }

    /**
     * EXTRACTS PROFESSOR USERNAME FROM UI
     * @return String
     */
    public String extractProfessorUsername(){
        EditText username=  (EditText)findViewById(R.id.UsernameprofessorText);
        return username.getText().toString();

    }

    /**
     * EXTRACTS PROFESSOR NAME FROM UI
     * @return String
     */
    public String extractProfessorName(){
        EditText name=  (EditText)findViewById(R.id.nameText);
        return name.getText().toString();

    }

    /**
     * EXTRACTS PROFESSOR SURNAME FROM UI
     * @return String
     */
    public String extractProfessorSurname(){
        EditText surname=  (EditText)findViewById(R.id.surnameText);
        return surname.getText().toString();

    }

    /**
     * EXTRACTS PROFESSOR BIRTH DATE FROM UI
     * @return String
     */
    public String extractProfessorBirthDate(){
        EditText birthdate=  (EditText)findViewById(R.id.BirthDateText);
        return birthdate.getText().toString();

    }

    /**
     * EXTRACTS PROFESSOR ID FROM UI
     * if missing input returns -1
     * @return int
     */
    public int extractProfessorId(){
        EditText professorId=  (EditText)findViewById(R.id.ProfessorIdText);



        if (professorId.getText().toString().trim().isEmpty()) {
            return -1; // Return a default value for missing input
        }
        try {
            return Integer.parseInt(professorId.getText().toString().trim());
        } catch (NumberFormatException e) {
            return -1; // Handle invalid input
        }
    }

    /**
     * SHOW SUCCESS SING UP MESSAGE ,if  professor created an account succussfully
     * @param title
     * @param message
     */
    public void showSuccessSingUpMessage(String title, String message)
    {
        new AlertDialog.Builder(SingUpProfessorActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
        System.out.println("Button clicked. Starting verification process.");

    }
    /**
     * Shows an  custom Error Message
     * @param  title
     * @param message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(SingUpProfessorActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * EXTRACTS PROFESSOR DEPARTMENT FROM UI
     * @return String
     */


    public String extractProfessorDepartment(){
        EditText department=  (EditText)findViewById(R.id.departmentText);
        return department.getText().toString();

    }

    /**
     * EXTRACTS PROFESSOR UNIVERSITY FROM UI
     * @return String
     */
    public String extractProfessorUniversity(){
        EditText university=  (EditText)findViewById(R.id.UniversityText);
        return university.getText().toString();

    }

    /**
     * EXTRACTS PROFESSOR ACADEMIC POSITION FROM UI
     * @return String
     */
    public String extractProfessorAcademicPosition(){
        EditText position=  (EditText)findViewById(R.id.academicPositionText);
        return position.getText().toString();

    }


}
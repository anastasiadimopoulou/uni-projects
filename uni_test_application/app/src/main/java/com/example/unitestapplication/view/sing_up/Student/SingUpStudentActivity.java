package com.example.unitestapplication.view.sing_up.Student;

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
import com.example.unitestapplication.view.log_in.LogInViewModel;
import com.example.unitestapplication.view.sing_up.Professor.SingUpProfessorActivity;

public class SingUpStudentActivity extends AppCompatActivity  implements  SingUpStudentView{
    private SingUpStudentViewModel viewModel;
    private  Button create_account_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_student);
        viewModel = new ViewModelProvider(this).get(SingUpStudentViewModel.class);
        viewModel.getPresenter().setView(this);



        Button back_button= (Button) findViewById(R.id.btnBackStudentRegister);
        Button create_account_button= (Button) findViewById(R.id.btnCreateAccountStudent);
        back_button.setOnClickListener(v->viewModel.getPresenter().goToLogIn());
        create_account_button.setOnClickListener(v->viewModel.getPresenter().verification());







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * EXTRACTS STUDENT PASSWORD FROM UI
     * @return String
     */

    public String extractStudentPassword(){
        EditText password=  (EditText)findViewById(R.id.passwordStudentText);
        return password.getText().toString();

    }

    /**
     * EXTRACTS STUDENT USERNAME FROM UI
     * @return String
     */
    public String extractStudentUsername(){
        EditText username=  (EditText)findViewById(R.id.UserNameStudentText);
        return username.getText().toString();

    }

    /**
     * EXTRACTS STUDENT NAME FROM UI
     * @return String
     */
    public String extractStudentName(){
        EditText name=  (EditText)findViewById(R.id.NameStudentText);
        return name.getText().toString();

    }

    /**
     * EXTRACTS STUDENT SURNAME FROM UI
     * @return String
     */
    public String extractStudentSurname(){
        EditText surname=  (EditText)findViewById(R.id.SurnameStudentText);
        return surname.getText().toString();

    }

    /**
     * EXTRACTS STUDENT BIRTH DATE FROM UI
     * @return String
     */
    public String extractStudentBirthDate(){
        EditText birthdate=  (EditText)findViewById(R.id.BirtdateStudentText);
        return birthdate.getText().toString();

    }

    /**
     * EXTRACTS STUDENT ACADEMIC ID FROM UI
     * @return String
     */
    public int extractStudentAcademicId(){
        EditText academicId=  (EditText)findViewById(R.id.AcademicIdStudentText);
        if (academicId.getText().toString().trim().isEmpty()) {
            return -1; // Return a default value for missing input
        }
        try {
            return Integer.parseInt(academicId.getText().toString().trim());
        } catch (NumberFormatException e) {
            return -1; // Handle invalid input
        }


    }

    /**
     * Shows an  custom Error Message
     * @param title
     * @param message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(SingUpStudentActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * SHOW SUCCESS SING UP MESSAGE ,if  student  created an account succussfully
     * @param title
     * @param message
     */
    public void showSuccessSingUpMessage(String title, String message)
    {
        new AlertDialog.Builder(SingUpStudentActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create()
                .show();


    }

    /**
     * STARTS LOG IN ACTIVITY
     */
    public void openLogInActivity(){
        Intent intent = new Intent(SingUpStudentActivity.this, LogInActivity.class);
        intent.putExtra("studentid",this.extractStudentAcademicId());
        startActivity(intent);


    }

    /**
     * EXTRACTS STUDENT DEPARTMENT FROM UI
     * @return String
     */
    public String extractStudentDepartment(){
        EditText department=  (EditText)findViewById(R.id.DepartmentStudentText);
        return department.getText().toString();

    }

    /**
     * EXTRACT STUDENT UNIVERSITY FROM UI
     * @return String
     */
    public String extractStudentUniversity(){
        EditText university=  (EditText)findViewById(R.id.UniversityStudentText);
        return university.getText().toString();

    }
}
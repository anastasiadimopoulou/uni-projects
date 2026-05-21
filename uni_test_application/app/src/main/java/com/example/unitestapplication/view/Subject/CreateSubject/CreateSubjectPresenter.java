package com.example.unitestapplication.view.Subject.CreateSubject;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectActivity;



public class CreateSubjectPresenter {
    private CreateSubjectView view;
    private SubjectDAO subjectDAO = null;
    private UserDAO userDAO = null;
    private QuestionDAO questionDAO;

    public CreateSubjectPresenter(UserDAO userDAO, QuestionDAO questionDAO, SubjectDAOMemory subjectDAO) {
        this.subjectDAO = subjectDAO;
        this.userDAO = userDAO;
        this.questionDAO = questionDAO;
    }

    public void setView(CreateSubjectView view)
    {
        this.view = view;
    }

    public CreateSubjectView getView()
    {
        return view;
    }

    /**
     * creates Subject with all important data
     */

    public void CreateSubject()
    {
        String name = view.getname();
        int id = view.getid();
        int semester = view.getsemester();
        int profid=view.getprofid();
        Subject newsubject = new Subject(name, id, semester,profid);
        subjectDAO.save(newsubject);
        view.showsuccessCreateSubject("Subject's Creation", "Your subject has been successfully created.");
    }

    /**
     * checks if all data ar right (if not shows error message) and calls CreateSubject
     */

    public void verification(){
        String name= view.getname();
        int id= view.getid();
        int semester=view.getsemester();
        int profid = view.getprofid();

        if (name.isEmpty()&& id==0 && name.isEmpty()&&semester==0 ){
            view.showErrorMessage("Error!","Missing Fields");

        }

        else if( name.isEmpty()){
            view.showErrorMessage("Missing Fields.","Complete name");

        }else if (id==0){
            view.showErrorMessage("Missing Fields.","Complete academic ID");
        } else if (semester==0) {
                view.showErrorMessage("Missing Fields.","Complete academic semester");
        }else if (subjectDAO.existsbyid(id,profid)) {
                view.showErrorMessage("Invalid id", "Id already exists.");

        }else{

            CreateSubject();
        }

    }


public void goToBasic_professor_menu()
{

    view.openbasicprofessormenu();
}


}

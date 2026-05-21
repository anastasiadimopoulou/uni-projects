package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion;
import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.memoryDAO.QuestionDAOmemory;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory;
import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectActivity;
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectView;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.CreateQuestion.CreateQuestionView;
import com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.QuestionManagmentActivity;

public class CreateQuestionPresenter {
    private int id=0;
    private CreateQuestionView view;
    private SubjectDAO subjectDAO=null;
    private UserDAO userDAO = null;
    private QuestionDAO questionDAO;
    public QuestionDAO getQuestionDAO(){return questionDAO;}
    public SubjectDAO getSubjectDAO(){return subjectDAO;}
    public UserDAO getUserDAO(){return userDAO;}

    public CreateQuestionPresenter(UserDAO userDAO, QuestionDAO questionDAO, SubjectDAOMemory subjectDAO) {
        this.subjectDAO = subjectDAO;
        this.userDAO = userDAO;
        this.questionDAO = questionDAO;

    }
    public void setView(CreateQuestionView view)
    {
        this.view =view;
    }

    public CreateQuestionView getView()
    {
        return this.view;
    }

    /**
     * creates Question with all important data
     * saves question in dao
     * @param subjectid
     */
    public void CreateQuestion(int subjectid) {

        String description = view.getdescription();
        String level = view.getlevel();
        String ch1 = view.getchoice1();
        String ch2 = view.getchoice2();
        String ch3 = view.getchoice3();
        String ch4 = view.getchoice4();
        String profans = view.getprofans();

        id = id + 1;
        Question q = new Question(level, description, ch1, ch2, ch3, ch4,  profans, false,subjectid);
        questionDAO.save(q);
        view.showsuccessCreateQuestion("Successful Creation ", "Your question has been successfully created.");


    }


    /**
     * checks if all data are right (if not shows error messages) and calls createQuestion(int subjectid)
     * @param subjectId
     */
    public void verification(int subjectId) {


        String description = view.getdescription();
        String level = view.getlevel();
        String ch1 = view.getchoice1();
        String ch2 = view.getchoice2();
        String ch3 = view.getchoice3();
        String ch4 = view.getchoice4();
        String profans = view.getprofans();
        if (description.isEmpty() && level.isEmpty() && ch1.isEmpty() && ch2.isEmpty() && ch3.isEmpty() && ch4.isEmpty() && profans.isEmpty()) {
            view.showErrorMessage("Error!", "Missing Fields");
        } else if (description.isEmpty()) {
           view.showErrorMessage("Missing Fields.", "Complete description");
        } else if (level.isEmpty()) {
            view.showErrorMessage("Missing Fields.", "Complete level");
        } else if (!level.equals("easy") && !level.equals("medium") && !level.equals("hard")) {
            // Ελέγχουμε αν το επίπεδο είναι "easy", "medium" ή "hard"
            view.showErrorMessage("Invalid Level", "The level must be easy, medium, or hard.");
        }else if (ch1.isEmpty()) {
            view.showErrorMessage("Missing Fields.", "Complete choice1");
        } else if (ch2.isEmpty()) {
            view.showErrorMessage("Missing Fields.", "Complete choice2");
        } else if (ch3.isEmpty()) {
            view.showErrorMessage("Missing Fields.", "Complete choice3");
        } else if (ch4.isEmpty()) {
            view.showErrorMessage("Missing Fields.", "Complete choice4");
        } else if (profans.isEmpty()) {
            view.showErrorMessage("Missing Fields.", "Complete the right answer");
        } else if (!profans.equals("A") && !profans.equals("B") && !profans.equals("C") && !profans.equals("D")) {
            // Ελέγχουμε αν η σωστή απάντηση είναι A, B, C ή D
            view.showErrorMessage("Invalid Answer", "The correct answer must be A, B, C, or D.");
        }else {
            CreateQuestion(subjectId);
        }
    }

    /**
     * goes to Question Managment Meny
     */
    public void goToQuestionManagmentMenu()
    {

        view.openCreateDeleteQuest();
    }

}





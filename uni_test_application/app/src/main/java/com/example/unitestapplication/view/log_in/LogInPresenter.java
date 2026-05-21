package com.example.unitestapplication.view.log_in;
import com.example.unitestapplication.DAO.ProfessorDAO;
import com.example.unitestapplication.DAO.StudentDAO;
import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.Professor;
import com.example.unitestapplication.domain.Student;
import  com.example.unitestapplication.domain.User;
public class LogInPresenter {


    private  LogInView  view;
    private UserDAO userDAO;
    private ProfessorDAO professorDAO;
    private StudentDAO studentDAO;

    /**
     *  LOG IN PRESENTER CONSTRUCTOR
     * @param userDAO
     * @param professorDAO
     * @param studentDAO
     */

    public LogInPresenter(UserDAO userDAO,ProfessorDAO professorDAO,StudentDAO studentDAO){
        this.userDAO=userDAO;
        this.professorDAO=professorDAO;
        this.studentDAO=studentDAO;
    }

    /**
     * SET VIEW
     * @param view
     */

    public void setView(LogInView view) {
        this.view = view;
    }

    /**
     * GET VIEW
     * 
     * @return LogInView
     */


    public LogInView getView() {
        return view;
    }

    /**
     * checks if user hasn't filled all the fields(username,password) and then verificates if the user exists
     *
     */
    public void verification(){
        String username= view.extractUsername();
        String password= view.extractPassword();
        if (password.isEmpty() ||username.isEmpty()){
            view.showErrorMessage("Error!","Missing Fields");
        }else if(userDAO.findByUsernameAndPassword(username,password)==null){
            view.showErrorMessage("Login Error!","We couldn't find an account with that username and password. Please double-check and try again.");

        }else if (userDAO.findByUsernameAndPassword(username,password)!=null ){
            if(userDAO.findByUsernameAndPassword(username,password) instanceof Professor){
                int pid=userDAO.findByUsernameAndPassword(username,password).getId();
                this.goToHomePageProfessor(pid);
            }else if(userDAO.findByUsernameAndPassword(username ,password) instanceof Student){
                int stid = userDAO.findByUsernameAndPassword(username ,password).getId();
                this.goToHomePageStudent(stid);
            }

        }



     }

    /**
     * Goes to the Student Home Page (opens student home page activity)
     * @param stid
     */
    public void goToHomePageStudent(int stid){
        view.openStudentHomePageActivity(stid);
     }

    /**
     * Goes to the Professor Home Page (opens professor home page activity)
     * @param pid
     */
     public void goToHomePageProfessor(int pid){
        view.openProfessorHomePageActivity(pid);
     }

    /**
     * Goes to Sing up Professor Page
     */
    public void goToSingupProfessor(){
        view.openSignupProfessorActivity();

     }

    /**
     * Goes to sing up Student Page
     */
    public void goToSingupStudent(){
        view.openSignupStudentActivity();

     }

}

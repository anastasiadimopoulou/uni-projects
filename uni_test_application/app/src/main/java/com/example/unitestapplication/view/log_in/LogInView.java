package com.example.unitestapplication.view.log_in;


/**
 * Interface LogInView
 */
public interface LogInView   {
    /**
     * Extracts the Password from the UI
     * @return String
     */
    String extractPassword();
    /**
     * Extracts the Username from the UI
     * @return String
     */
    String extractUsername();
    /**
     * Shows an  custom Error Message
     * @param  title
     * @param message
     */
    void showErrorMessage(String title, String message);

    /**
     * Start  Sign up Professor Activity
     */
    public void openSignupProfessorActivity();
    /**
     * Start Sing Up Student Activity
     */
    public void openSignupStudentActivity();
    /**
     * Starts  show Subject Activity
     * goes to student home page
     *
     * @param stid
     */
    public void openStudentHomePageActivity(int stid);
    /**
     *Starts  MainActivity
     * goes to professor home page
     * @param id
     */
    public  void  openProfessorHomePageActivity(int id);


}

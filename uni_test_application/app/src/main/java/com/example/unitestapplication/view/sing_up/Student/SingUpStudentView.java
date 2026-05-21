package com.example.unitestapplication.view.sing_up.Student;

public interface SingUpStudentView {
    /**
     * EXTRACTS STUDENT PASSWORD FROM UI
     * @return String
     */

    public String extractStudentPassword();
    /**
     * EXTRACTS STUDENT USERNAME FROM UI
     * @return String
     */
    public String extractStudentUsername();
    /**
     * EXTRACTS STUDENT NAME FROM UI
     * @return String
     */
    public String extractStudentName();
    /**
     * EXTRACTS STUDENT SURNAME FROM UI
     * @return String
     */
    public String extractStudentSurname();
    /**
     * EXTRACTS STUDENT BIRTH DATE FROM UI
     * @return String
     */
    public String extractStudentBirthDate();
    /**
     * EXTRACTS STUDENT ACADEMIC ID FROM UI
     * @return String
     */
    public int extractStudentAcademicId();
    /**
     * Shows an  custom Error Message
     * @param title
     * @param message
     */
    public void showErrorMessage(String title, String message);
    /**
     * SHOW SUCCESS SING UP MESSAGE ,if  student  created an account succussfully
     * @param title
     * @param message
     */
    public void showSuccessSingUpMessage(String title, String message);
    /**
     * STARTS LOG IN ACTIVITY
     */
    public void openLogInActivity();
    /**
     * EXTRACTS STUDENT DEPARTMENT FROM UI
     * @return String
     */
    public String extractStudentDepartment();
    /**
     * EXTRACT STUDENT UNIVERSITY FROM UI
     * @return String
     */
    public String extractStudentUniversity();


}

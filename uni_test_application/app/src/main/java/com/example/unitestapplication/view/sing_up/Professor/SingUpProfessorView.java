package com.example.unitestapplication.view.sing_up.Professor;

public interface SingUpProfessorView {
    /**
     * Starts  LOG IN ACTIVITY
     */
    void openLogInActivity();
    /**
     * EXTRACTS PROFESSOR PASSWORD FROM UI
     * @return String
     */
    public String extractProfessorPassword();
    /**
     * EXTRACTS PROFESSOR USERNAME FROM UI
     * @return String
     */
    public String extractProfessorUsername();
    /**
     * EXTRACTS PROFESSOR NAME FROM UI
     * @return String
     */
    public String extractProfessorName();
    /**
     * EXTRACTS PROFESSOR SURNAME FROM UI
     * @return String
     */
    public String extractProfessorSurname();
    /**
     * EXTRACTS PROFESSOR BIRTH DATE FROM UI
     * @return String
     */
    public String extractProfessorBirthDate();
    /**
     * EXTRACTS PROFESSOR ID FROM UI
     * if missing input returns -1
     * @return int
     */
    public int extractProfessorId();
    /**
     * Shows an  custom Error Message
     * @param  title
     * @param message
     */

    public void showErrorMessage(String title, String message);
    /**
     * SHOW SUCCESS SING UP MESSAGE ,if  professor created an account succussfully
     * @param title
     * @param message
     */
    public void showSuccessSingUpMessage(String title, String message);
    /**
     * EXTRACTS PROFESSOR DEPARTMENT FROM UI
     * @return String
     */
    public String extractProfessorDepartment();
    /**
     * EXTRACTS PROFESSOR UNIVERSITY FROM UI
     * @return String
     */
    public String extractProfessorUniversity();
    /**
     * EXTRACTS PROFESSOR ACADEMIC POSITION FROM UI
     * @return String
     */
    public String extractProfessorAcademicPosition();

}

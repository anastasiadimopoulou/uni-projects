package com.example.unitestapplication.view.Subject.CreateSubject;

public interface CreateSubjectView {
    public String getname();
    public int getid();
    public int getsemester();
    public int getprofid();
    public void showErrorMessage(String Title,String Message);

    public void showsuccessCreateSubject(String Title, String Message);

    public void setname(String name);
    public void setid(int id);
    public void setsemester(int semester);

    void setsprofid(int profid);

    public void openbasicprofessormenu();
}

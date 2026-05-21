package com.example.unitestapplication.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject
{
    private List<Question> questions ;
    private String name;
    protected int id;
    private int semester;

    private int profid;

    public Subject (){}

    public Subject (String name,int id,int semester, int profid)
    {
        this.name=name;
        this.id=id;
        this.semester=semester;
        this.questions= new ArrayList <>();
        this.profid=profid;
    }

    public int getProfid(){
        return this.profid;
    }

    public void setname(String name) 
    {

        this.name = name;
    }

    public void setid(int id) 
    {
        if (id <= 0) 
        {
            this.id=0;
        }
        else{
            this.id = id;

        }
    }


    public String getname() {
        return name;
    }

    public  int getid() {
        return id;
    }

    public int  getsemester() {
        return semester;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(String l, String d, String ch1, String ch2, String ch3, String ch4, int id, String prof_ans, boolean s,int subjectid){
        Question q1 = new Question(l, d, ch1, ch2, ch3, ch4,prof_ans,s,subjectid);
        this.questions.add(q1);
    }

    public void deleteQuestion(int id){
        for (int i=0;i< questions.size();i++){
            if (questions.get(i).getId() == id){
                questions.remove(i);
                break;
            }
        }
    }

    public boolean equals(Subject ob) 
    {
        if (ob == null) return false;
        return this.id == ob.id && this.name.equals(ob.name) && this.semester == ob.semester;
    }


    public String toString() 
    {
        return "Subject { " + "Name='" + name + '\'' + ", ID=" + id + ", Semester=" + semester + " }";
    }



}

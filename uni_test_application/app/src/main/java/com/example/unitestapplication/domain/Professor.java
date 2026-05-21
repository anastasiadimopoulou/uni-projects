package com.example.unitestapplication.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.domain.User;




public class Professor extends User{

    private String level;
    private List<Subject> subjects =new ArrayList<Subject>();
    public List<Subject> getSubjects(){
        return subjects;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public  Professor (String f, String l, String usr, String pass, String dep, String uni , String bd, int id, String level){

        super(f,l,usr,pass,dep,uni ,bd,id);
        this.level=level;
    }

    /**
     * Creates new subject and add this to subject list
     * @param name
     * @param id
     * @param semester
     * @param profid
     */

    public void create_subject(String name,int id,int semester,int profid){
             Subject sub=new Subject(name,id,semester,profid);
             subjects.add(sub);

    }

    /**
     * Deletes subject and remove this from subject list
     * @param subid
     */

    public void delete_subject(int subid){
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getid()==subid) {
                subjects.remove(i);
                
            }
        }
    }

    /**
     * Updates subject if choice =1  add questions to the subject if choice=2  deletes a question from subject
     * @param subid
     * @param choice
     * @param l
     * @param d
     * @param ch1
     * @param ch2
     * @param ch3
     * @param ch4
     * @param questid
     * @param prof_ans
     * @param s
     */

    public void update_subject(int subid, int choice, String l,String d,String ch1,String ch2,String ch3,String ch4,int questid,String prof_ans,boolean s){

        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getid()==subid) {
                Subject sub = subjects.get(i);
                if (choice==1){
                    sub.addQuestion(l,d,ch1,ch2,ch3,ch4,questid,prof_ans,true,subid);
                }
                
            }
        }


        
    }



}

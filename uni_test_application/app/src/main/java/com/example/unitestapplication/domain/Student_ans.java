package com.example.unitestapplication.domain;

import java.util.Objects;


public class Student_ans {
    private String number_of_choice;
    //private Question question;
    //private Test test;
    public String getNumber_of_choice() {
        return number_of_choice;
    }
    public Student_ans(){}

    public Student_ans(String number_of_choice) {
        this.number_of_choice = number_of_choice;
    }

    public void setNumber_of_choice(String number_of_choice) {
        this.number_of_choice = number_of_choice;
    }


    @Override
    public String toString() {
        return "Student_ans{" +
                "number_of_choice='" + number_of_choice + '\'' +'}';
    }


}

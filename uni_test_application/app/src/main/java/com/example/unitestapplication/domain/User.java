package com.example.unitestapplication.domain;

import java.util.Objects;

public class User {


    protected String first_name;
    protected String last_name;
    protected String username;
    protected String password;
    protected String department;
    protected String university;
    protected String date_of_birth;
    protected int id;

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public String getUniversity() {
        return university;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }


    public int getId() {
        return id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public User(){}

    public  User(String f,String l,String usr,String pass,String dep,String uni ,String bd, int id){
        this.first_name=f;
        this.last_name=l;
        this.username=usr;
        this.password=pass;
        this.department=dep;
        this.university=uni;
        this.date_of_birth=bd;
        this.id=id;

    }

    /**
     * Takes the input id and checks if equals with user id
     * @param id
     * @return
     */
    public boolean check_if_user_exists( int id){
        if (id==getId()){
            return true;
        }
        return false;
    }

    /**
     * takes the input usename and checks if  equals with user username
     * @param username
     * @return
     */


    

    public boolean unique_username(String username ){
        if (Objects.equals(username, this.getUsername())){
            return false;
        }
        return true;
    }

    /**
     * takes input username and password  and checks if equals with user password and username
     * @param username
     * @param password
     * @return
     */


    public  boolean check_login_fields(String username,String password){
        if(Objects.equals(username, getUsername()) && Objects.equals(password, getPassword())){
            return true;
        }
        return false;
    }

}

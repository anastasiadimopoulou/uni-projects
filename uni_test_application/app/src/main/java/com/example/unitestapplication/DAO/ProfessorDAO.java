package com.example.unitestapplication.DAO;

import com.example.unitestapplication.domain.User;
import  com.example.unitestapplication.domain.Professor;

import java.util.ArrayList;

public interface ProfessorDAO   {
    void deleteAll();
    void save(User entity);
    ArrayList<User> findAll();
}

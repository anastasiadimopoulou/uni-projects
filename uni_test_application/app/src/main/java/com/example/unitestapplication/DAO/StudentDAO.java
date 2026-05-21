package com.example.unitestapplication.DAO;

import com.example.unitestapplication.domain.Professor;
import com.example.unitestapplication.domain.User;
import com.example.unitestapplication.domain.Student;


import java.util.ArrayList;

public interface StudentDAO {
    void deleteAll();
    void save(User entity);
    ArrayList<User> findAll();

}


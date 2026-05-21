package com.example.unitestapplication.memoryDAO;

import com.example.unitestapplication.DAO.StudentDAO;
import com.example.unitestapplication.domain.Professor;
import com.example.unitestapplication.domain.Student;
import com.example.unitestapplication.domain.User;

import java.util.ArrayList;

public class StudentDAOMemory  implements StudentDAO {
    protected static ArrayList<User> entities = new ArrayList<>();
    @Override
    public void deleteAll() {

        entities.clear();

    }

    @Override
    public void save(User entity) {
        entities.add(entity);

    }

    @Override
    public ArrayList<User> findAll() {
        return entities;
    }



}


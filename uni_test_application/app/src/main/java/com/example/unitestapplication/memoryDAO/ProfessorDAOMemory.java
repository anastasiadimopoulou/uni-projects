package com.example.unitestapplication.memoryDAO;

import com.example.unitestapplication.DAO.ProfessorDAO;
import com.example.unitestapplication.domain.User;

import java.util.ArrayList;

public class ProfessorDAOMemory  implements ProfessorDAO  {
    protected static ArrayList<User> entities = new ArrayList<>();

    @Override
    public void deleteAll() {
        entities.clear();

    }

    @Override
    public void save(User entity) {
        System.out.println("Saving user: " + entity.getUsername());
        entities.add(entity);

    }

    @Override
    public ArrayList<User> findAll() {
        return entities;
    }
}

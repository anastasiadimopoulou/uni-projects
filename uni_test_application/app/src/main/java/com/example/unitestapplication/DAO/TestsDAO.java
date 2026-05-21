package com.example.unitestapplication.DAO;

import com.example.unitestapplication.domain.Tests;

import java.util.ArrayList;

public interface TestsDAO {

    void deleteAll();

    void save(Tests entity);

    ArrayList<Tests> findAll();
    public Tests findByid(int id);
}


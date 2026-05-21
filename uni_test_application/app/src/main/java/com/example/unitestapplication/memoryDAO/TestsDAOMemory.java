package com.example.unitestapplication.memoryDAO;

import com.example.unitestapplication.DAO.TestsDAO;
import com.example.unitestapplication.domain.Tests;

import java.util.ArrayList;

public class TestsDAOMemory implements TestsDAO {

    protected static ArrayList<Tests> entities = new ArrayList<>();

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(Tests entity) {
        // Check for existing test with same ID before saving
        for (Tests test : entities) {
            if (test.getId() == entity.getId()) {
                return; // Do not save duplicate test
            }
        }
        entities.add(entity);
    }

    @Override
    public ArrayList<Tests> findAll() {
        return new ArrayList<>(entities);
    }

    /**
     * finds a test using its id returns null when a test with this id doesn't exist
     * @param id
     * @return Tests
     */

    @Override
    public Tests findByid(int id) {
        for (Tests test : entities) {
            if (test.getId() == id) {
                return test;
            }
        }
        return null;  // Return null if not found
    }
}

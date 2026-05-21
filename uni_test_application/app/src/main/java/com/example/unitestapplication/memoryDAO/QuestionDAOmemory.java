package com.example.unitestapplication.memoryDAO;

import android.util.Log;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class QuestionDAOmemory implements QuestionDAO {
    protected static ArrayList<Question> entities = new ArrayList<>();
    @Override
    public void deleteAll() {
        entities.clear();

    }

    @Override
    public void save(Question entity) {
        entities.add(entity);

    }
    public void delete(Question q) {
        entities.remove(q);  // Διαγραφή από τη λίστα
    }

    /**
     * finds all questions from a specific subject
     * @param id
     * @return ArrayList<Question>
     */


    @Override
    public ArrayList<Question> findByid(int id) {
        ArrayList<Question> result = new ArrayList<>();
        for (Question q : entities) {

            if (q.getSubjectid() == id) {
                result.add(q);
            }
        }
        return result;
    }

    /**
     * finds all question from a specific level
     * @param level
     * @param listofquestions
     * @return ArrayList<Question>
     */

    @Override
    public ArrayList<Question> findByLevel(String level, ArrayList <Question> listofquestions) {
        ArrayList<Question> result = new ArrayList<>();
        for (Question q : listofquestions) {

            if (Objects.equals(q.getLevel(), level)) {
                result.add(q);

            }
        }
        return result;
    }

    @Override
    public ArrayList<Question> findAll() {
        return entities;
    }
}


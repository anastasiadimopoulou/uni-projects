
package com.example.unitestapplication.DAO;

import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import  com.example.unitestapplication.domain.Question;

    public interface QuestionDAO {
        void deleteAll();
        void save(Question entity);
        ArrayList<Question> findAll();
        void delete(Question q );
        public ArrayList<Question> findByid(int id);

        public ArrayList<Question> findByLevel(String level,ArrayList<Question> listofquestions);

    }



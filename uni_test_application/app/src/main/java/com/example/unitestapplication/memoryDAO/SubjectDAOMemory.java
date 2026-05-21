package com.example.unitestapplication.memoryDAO;

import com.example.unitestapplication.DAO.SubjectDAO;
import com.example.unitestapplication.domain.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAOMemory implements SubjectDAO {
    private static List<Subject> subjectList = new ArrayList<>();

    @Override
    public void save(Subject subject) {
        subjectList.add(subject);
    }
    @Override
    public void delete(Subject subject) {
        subjectList.remove(subject);  // Διαγραφή από τη λίστα
    }

    @Override
    public List<Subject> findAll() {
        System.out.println("Number of subjects: " + subjectList.size());
        return new ArrayList<>(subjectList);
    }


    /**
     * finds all subjects with the same name
     * @param name
     * @return List<Subject>
     */
    @Override
    public List<Subject> findByname(String name) {
        List<Subject> result = new ArrayList<>();
        for (Subject subject : subjectList) {
            if (subject.getname().contains(name)) {
                result.add(subject);
            }
        }
        return result;
    }

    /**
     * finds all subjects created by the same professor
     * @param id
     * @return List<Subject>
     */

    @Override
    public List<Subject> findByid(int id) {
        List<Subject> result = new ArrayList<>();
        for (Subject subject : subjectList) {
            if (subject.getProfid() == id) {
                result.add(subject);
            }
        }
        return result;
    }

    /**
     * finds if a subject with specific id exits
     * @param id
     * @return boolean
     */
    @Override
    public boolean existsbyid(int id, int profId)
     {

         List <Subject > result=findByid(profId);
         for (Subject subject : result) {
             if (subject.getid() == id) {
                 return true;

             }
         }
         return false;
     }
    @Override
    public void deleteAll() {
        subjectList.clear();

    }
}


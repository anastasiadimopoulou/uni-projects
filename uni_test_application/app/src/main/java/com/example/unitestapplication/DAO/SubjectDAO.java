package com.example.unitestapplication.DAO;


import com.example.unitestapplication.domain.Subject;



import java.util.List;


public interface SubjectDAO {

    boolean existsbyid(int id,int profId);

    void deleteAll();
    void save(Subject entity);

    public List<Subject> findAll() ;
    public List<Subject> findByname(String name);
    public List<Subject> findByid(int id);

    void delete(Subject subject);
}
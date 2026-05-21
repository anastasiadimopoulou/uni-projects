package com.example.unitestapplication.DAO;
import com.example.unitestapplication.domain.User;

import java.util.ArrayList;

public interface UserDAO {
    void deleteAll();
    void save(User entity);
    ArrayList<User> findAll();
    public User findByUsernameAndPassword(String username, String password);
    public User findByUsername(String Username);
    public  User findByID(int Id);
}



package com.example.unitestapplication.memoryDAO;

import com.example.unitestapplication.DAO.UserDAO;
import com.example.unitestapplication.domain.User;

import java.util.ArrayList;

public class UserDAOMemory  implements UserDAO {

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

    /**
     * Search user by ID ,checks in user dao if user exists with this id
     * @param Id
     * @return User
     */
    public  User findByID(int Id) {
        for (User user : entities) {
            boolean flag=user.check_if_user_exists(Id);
            if (flag){
                return user;}
            }
        return null;
        }

    /**
     * Search User by username and password ,checks in user dao if user exists with this username and password
     * @param username
     * @param password
     * @return User
     */
    public User findByUsernameAndPassword(String username, String password) {
        for (User user : entities) {
            boolean flag = user.check_login_fields(username,password);
            if(flag){
                return user;
            }
        }
        return null;}

    /**
     * search user by username ,checks in user dao if user exist with this username
     * @param Username
     * @return Username
     */
    public User findByUsername(String Username){
        for (User user : entities) {
            boolean flag = user.unique_username(Username);
            if(!flag){
                return user;
            }
        }
        return null;

    }




}


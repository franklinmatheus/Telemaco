/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.entity.User;
import java.sql.SQLException;

/**
 *
 * @author franklin
 */
public class FacadeDAO {
    private static FacadeDAO facade = null;
    
    private FacadeDAO() { }
    
    /**
     * TODO
     * @return 
     */
    public static synchronized FacadeDAO getInstance() {
        if(facade == null)
            facade = new FacadeDAO();
        return facade;
    }
    
    /**
     * TODO
     * @param user 
     */
    public void insertUser(User user) {
        try {
            DAO<User> userDAO = UserDAO.getInstance();
            userDAO.insert(user);
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
    
    /**
     * TODO
     * @param id
     * @return 
     */
    public User selectUser(int id) {
        try {
            DAO<User> userDAO = UserDAO.getInstance();
            User user = userDAO.select(id);
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
    
    /**
     * TODO
     * @param email
     * @param password
     * @return 
     */
    public User selectUser(String email, String password) {
        try {
            DAOUserSpecialOperations userDAO = UserDAO.getInstance();
            User user = userDAO.select(email, password);
            return user;
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
    
    /**
     * 
     * @param email
     * @return 
     */
    public User selectUser(String email) {
        try {
            DAOUserSpecialOperations userDAO = UserDAO.getInstance();
            User user = userDAO.select(email);
            return user;
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
    
    /**
     * 
     * @param user 
     */
    public void updateUser(User user) {
        try {
            DAO<User> userDAO = UserDAO.getInstance();
            userDAO.update(user);
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
    
    /**
     * TODO
     * @param user
     * @param newPassword 
     */
    public void updateUser(User user, String newPassword) {
        user.setPassword(newPassword);
        this.updateUser(user);
    }
    
    /**
     * TODO
     * @param user 
     */
    public void removeUser(User user) {
        try {
            DAO<User> userDAO = UserDAO.getInstance();
            userDAO.delete(user);
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
}

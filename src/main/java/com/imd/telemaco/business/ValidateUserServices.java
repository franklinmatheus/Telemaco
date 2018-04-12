/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business;

import com.imd.telemaco.data.DAOUserSpecialOperations;
import com.imd.telemaco.data.UserDAO;
import com.imd.telemaco.entity.User;
import java.sql.SQLException;

/**
 *
 * @author franklin
 */
public class ValidateUserServices {
    
    public ValidateUserServices() { }
    
    /**
     * TODO
     * @param user
     * @param cemail
     * @param cpassword
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean validUserInsert(User user, String cemail, String cpassword) throws SQLException {
        if(this.valid(user))
            if(this.confirmPassword(user.getPassword(), cpassword) && this.confirmEmail(user.getEmail(), cemail))
                if(!this.emailAlreadyExists(user)) {
                    DAOUserSpecialOperations dao = UserDAO.getInstance();
                    dao.insert(user);
                    return true;
                }
        return false;
    }
    
    /**
     * TODO
     * @param email
     * @param password
     * @return 
     * @throws java.sql.SQLException 
     */
    public User validUserLogin(String email, String password) throws SQLException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User user = dao.select(email, password);
        
        return user;
    }
    
    /**
     * TODO
     * @param user
     * @param cOldPassword
     * @param newPassword
     * @param cNewPassword
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean validUserPasswordUpdate(User user, String cOldPassword, String newPassword, String cNewPassword) throws SQLException {
        if(this.confirmPassword(newPassword, cNewPassword))
            if(this.confirmPassword(user.getPassword(), cOldPassword)) {
                DAOUserSpecialOperations dao = UserDAO.getInstance();
                user.setPassword(newPassword);
                dao.update(user);
                return true;
            }
        return false;
    }
    
    /**
     * TODO
     * @param user
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean removeUser(User user) throws SQLException {
        if(this.userExists(user)) {
            DAOUserSpecialOperations dao = UserDAO.getInstance();
            dao.delete(user);
            
            return true;
        }
        return false;
    }
    
    /**
     * TODO
     * @param user
     * @return 
     */
    private boolean valid(User user) {
        if((user.getName() == null || user.getName().isEmpty() || user.getName().trim().equals("")) || 
                (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().trim().equals("")) || 
                (user.getPassword()== null || user.getPassword().isEmpty() || user.getPassword().trim().equals(""))) {
            return false;
        }
        return true;
    }
    
    /**
     * TODO
     * @param password
     * @param cpassword
     * @return 
     */
    private boolean confirmPassword(String password, String cpassword) {
        return password.equals(cpassword);
    }
    
    /**
     * TODO
     * @param email
     * @param cemail
     * @return 
     */
    private boolean confirmEmail(String email, String cemail) {
        return email.equals(cemail);
    }
    
    /**
     * TODO
     * @param user
     * @return 
     */
    private boolean userExists(User user) throws SQLException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User exists = dao.select(user.getId());
        
        return exists != null;
    }
    
    /**
     * TODO
     * @param user
     * @return 
     */
    private boolean emailAlreadyExists(User user) throws SQLException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User exists = dao.select(user.getEmail());
        
        return exists != null;
    }
}

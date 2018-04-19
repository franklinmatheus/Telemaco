/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.data.DAOUserSpecialOperations;
import com.imd.telemaco.data.UserDAO;
import com.imd.telemaco.entity.User;

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
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public boolean validUserInsert(User user, String cemail, String cpassword) throws DatabaseException, CloseConnectionException {
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
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public User validUserLogin(String email, String password) throws DatabaseException, CloseConnectionException {
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
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public boolean validUserPasswordUpdate(User user, String cOldPassword, String newPassword, String cNewPassword) 
            throws DatabaseException, CloseConnectionException {
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
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public boolean removeUser(User user) throws DatabaseException, CloseConnectionException {
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
        return !((user.getName() == null || user.getName().isEmpty() || user.getName().trim().equals("")) || 
                (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().trim().equals("")) ||
                (user.getPassword()== null || user.getPassword().isEmpty() || user.getPassword().trim().equals("")));
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
    private boolean userExists(User user) throws DatabaseException, CloseConnectionException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User exists = dao.select(user.getId());
        
        return exists != null;
    }
    
    /**
     * TODO
     * @param user
     * @return 
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    private boolean emailAlreadyExists(User user) throws DatabaseException, CloseConnectionException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User exists = dao.select(user.getEmail());
        
        return exists != null;
    }
}

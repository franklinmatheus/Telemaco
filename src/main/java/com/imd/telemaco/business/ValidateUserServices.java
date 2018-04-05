/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business;

import com.imd.telemaco.data.FacadeDAO;
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
     */
    public boolean validUserInsert(User user, String cemail, String cpassword) {
        if(this.valid(user))
            if(this.confirmPassword(user.getPassword(), cpassword) && this.confirmEmail(user.getEmail(), cemail))
                if(!this.userExists(user)) {
                    FacadeDAO facade = FacadeDAO.getInstance();
                    facade.insertUser(user);
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
    private boolean userExists(User user) {
        FacadeDAO facade = FacadeDAO.getInstance();
        User exists = facade.selectUser(user.getEmail(), user.getPassword());
        
        if(exists == null)
            return false;
        return true;
    }
    
    /**
     * TODO
     * @param email
     * @param password
     * @return 
     */
    public User validUserLogin(String email, String password) {
        FacadeDAO facade = FacadeDAO.getInstance();
        User user = facade.selectUser(email, password);
        
        return user;
    }
    
    /**
     * 
     * @param user
     * @param cOldPassword
     * @param newPassword
     * @param cNewPassword
     * @return 
     */
    public boolean validUserPasswordUpdate(User user, String cOldPassword, String newPassword, String cNewPassword) {
        if(this.confirmPassword(newPassword, cNewPassword))
            if(this.confirmPassword(user.getPassword(), cOldPassword)) {
                FacadeDAO facade = FacadeDAO.getInstance();
                facade.updatePassword(user, newPassword);
                return true;
            }
        return false;
    }
}

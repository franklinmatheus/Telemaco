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
     * @return 
     */
    public boolean validUserInsert(User user) {
        if(this.valid(user))
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
    
    public User validUserLogin(String email, String password) {
        FacadeDAO facade = FacadeDAO.getInstance();
        User user = facade.selectUser(email, password);
        
        return user;
    }
}

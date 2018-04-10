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
public interface DAOUserSpecialOperations {
    /**
     * Select an user by email and password, special method to login.
     * @param email
     * @param password
     * @return 
     * @throws java.sql.SQLException 
     */
    public User select(String email, String password) throws SQLException;
    
    /**
     * Select an user by email, special method to register.
     * @param email
     * @return
     * @throws SQLException 
     */
    public User select(String email) throws SQLException;
}

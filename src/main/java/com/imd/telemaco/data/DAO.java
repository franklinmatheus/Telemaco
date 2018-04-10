/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import java.sql.SQLException;

/**
 *
 * @author franklin
 * @param <Type>
 */
public interface DAO<Type> {
    
    /**
     * Recieves an object as parameter and inserts into database.
     * @param object 
     * @throws java.sql.SQLException 
     */
    public void insert(Type object) throws SQLException;
    
    /**
     * Run a script to select the query in the database through id.
     * @param id
     * @return result with filled filds.
     * @throws java.sql.SQLException
     */
    public Type select(int id) throws SQLException;
    
    /**
     * Recieves an object and remove it from database.
     * @param object 
     * @throws java.sql.SQLException 
     */
    public void delete(Type object) throws SQLException;
    
    /**
     * Recieves an object with the new values and update then by id in database.
     * @param object 
     * @throws java.sql.SQLException 
     */
    public void update(Type object) throws SQLException;
}

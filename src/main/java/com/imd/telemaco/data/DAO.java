/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;


/**
 *
 * @author franklin
 * @param <Type>
 */
public interface DAO<Type> {
    
    /**
     * Receives an object as parameter and inserts into database.
     * @param object 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public void insert(Type object) throws DatabaseException, CloseConnectionException;
    
    /**
     * Run a script to select the query in the database through id.
     * @param id
     * @return result with filled filds.
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public Type select(int id) throws DatabaseException, CloseConnectionException;
    
    /**
     * Receives an object and remove it from database.
     * @param object 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public void delete(Type object) throws DatabaseException, CloseConnectionException;
    
    /**
     * Receives an object with the new values and update then by id in database.
     * @param object 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public void update(Type object) throws DatabaseException, CloseConnectionException;
}

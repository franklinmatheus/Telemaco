/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Serie;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public interface DAOSerieSpecialOperations extends DAO<Serie> {
    /**
     * Select a serie by name.
     * @param name
     * @return
     * @throws DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public Serie select (String name) throws DatabaseException, CloseConnectionException;
    
    /**
     * Select all series and return an array with them.
     * @return
     * @throws DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public ArrayList<Serie> selectAllSeries () throws DatabaseException, CloseConnectionException;
}

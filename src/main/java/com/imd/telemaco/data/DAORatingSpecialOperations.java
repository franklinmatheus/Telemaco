/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Rating;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public interface DAORatingSpecialOperations extends DAO<Rating> {
    
    /**
     * Select all comments from one serie.
     * @param idSerie
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public ArrayList<Rating> selectBySerie(int idSerie) throws DatabaseException, CloseConnectionException;
}

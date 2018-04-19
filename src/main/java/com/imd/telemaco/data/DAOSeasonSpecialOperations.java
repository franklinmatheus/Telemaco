package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import java.util.ArrayList;

import com.imd.telemaco.entity.Season;

/**
 * Interface to indicate the special operations in the SeasonDAO
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 12 de abr de 2018 | 09:22:25
 */
public interface DAOSeasonSpecialOperations extends DAO<Season> {
	/**
     * Select a season from the database since the number and idSerie values. 
     * @param  number
     * @param  idSerie
     * @return season
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public Season select (int number, int idSerie) throws DatabaseException, CloseConnectionException;
    
    /**
     * Returns all the episodes of the Season
     * @param idSerie
     * @return episodes
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public ArrayList<Season> selectAllSeasons (int idSerie) throws DatabaseException, CloseConnectionException;
}

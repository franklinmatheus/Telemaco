package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import java.util.ArrayList;

import com.imd.telemaco.entity.Episode;

/**
 * Interface to indicate the special operations in the EpsiodeDAO
 *
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 12 de abr de 2018 | 09:25:18
 */
public interface DAOEpisodeSpecialOperations extends DAO<Episode> {

    /**
     * Select a episode from the database since the name and idSeason values.
     *
     * @param name
     * @param idSeason
     * @return	episode
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public Episode select(String name, int idSeason) throws DatabaseException, CloseConnectionException;

    /**
     * Select a episode from the database since the number and idSeason values.
     *
     * @param number
     * @param idSeason
     * @return	episode
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public Episode select(int number, int idSeason) throws DatabaseException, CloseConnectionException;

    /**
     * Select all episodes of a season
     *
     * @param idSeason
     * @return episodes
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public ArrayList<Episode> selectAllEpisodes(int idSeason) throws DatabaseException, CloseConnectionException;
}

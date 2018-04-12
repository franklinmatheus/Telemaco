package com.imd.telemaco.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.imd.telemaco.entity.Episode;

/**
 * Interface to indicate the special operations in the EpsiodeDAO
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 12 de abr de 2018 | 09:25:18
 */
public interface DAOEpisodeSpecialOperations extends DAO <Episode> {
	/**
	 * Select a episode from the database since the name and idSeason values.
	 * @param 	name
	 * @param 	idSeason
	 * @return	episode
	 * @throws 	SQLException
	 */
	public Episode select(String name, int idSeason) throws SQLException;
	
	/**
	 * Select a episode from the database since the number and idSeason values.
	 * @param 	name
	 * @param 	idSeason
	 * @return	episode
	 * @throws 	SQLException
	 */
	public Episode select(int number, int idSeason) throws SQLException;
	
	/**
	 * Select all episodes of a season
	 * @param idSeason
	 * @return episodes
	 * @throws SQLException
	 */
	public ArrayList<Episode> selectAllEpisodes (int idSeason) throws SQLException;
}

package com.imd.telemaco.business;

import java.sql.SQLException;

import com.imd.telemaco.business.exception.SeasonExistsException;
import com.imd.telemaco.business.exception.SeasonIncompleteException;
import com.imd.telemaco.data.SeasonDAO;
import com.imd.telemaco.entity.Season;

/**
 * Class to validate all services offered by Season class
 * 
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 11 de abr de 2018 | 23:28:20
 */
public class ValidateSeasonServices {
	/**
	 * Default constructor
	 */
	public ValidateSeasonServices () {  }
	
	/**
	 * Valid the data of the season
	 * @param season
	 * @throws SeasonIncompleteException
	 */
	public void validSeason (Season season) throws SeasonIncompleteException {
		if (season.getNumber() <= 0 || season.getEpisodes() == null || season.getEpisodes().size() == 0)
			throw new SeasonIncompleteException ("Informações incompletas para série");
	}
	
	/**
	 * Verify if the season already exists in the database
	 * @param season
	 * @throws SeasonExistsException
	 */
	public void validSeasonExistence (Season season) throws SeasonExistsException {
		try {
			SeasonDAO seasonDAO = new SeasonDAO ();
			Season sExisting = seasonDAO.select(season.getNumber(), season.getIdSerie());
		
			if (sExisting != null)
				throw new SeasonExistsException("Esta temporada já existe");
		}  catch (SQLException e) {
			e.printStackTrace(); //FIXME
		}
	}
	
	/**
	 * Verify if the season insertion is valid
	 * @param season
	 * @throws SeasonExistsException
	 * @throws SQLException
	 */
	public void validSeasonInsert (Season season) throws SeasonExistsException, SeasonIncompleteException {
//		validSeason (season); FIXME
		validSeasonExistence (season);
		try {
			SeasonDAO seasonDAO = new SeasonDAO();
			seasonDAO.insert(season);
		} catch (SQLException e) {
			e.printStackTrace(); //FIXME
		}
	}
	
	/**
	 * Verify if it's trying remove a existing season 
	 * @param season
	 * @throws SeasonExistsException
	 * @throws SQLException
	 */
	public void validSeasonRemove (Season season) throws SeasonExistsException {
		try { 
			SeasonDAO seasonDAO = new SeasonDAO ();
			Season sExisting = seasonDAO.select(season.getNumber(), season.getIdSerie());
			
			if (sExisting == null)
				throw new SeasonExistsException("Esta temporada não existe");
		} catch (SQLException e) {
			e.printStackTrace(); //FIXME
		}
	}
	
	/**
	 * Verify if a season can be updated
	 * @param  season
	 * @throws SeasonIncompleteException
	 * @throws SeasonExistsException
	 */
	public void validSeasonUpdate (Season season) throws SeasonIncompleteException, SeasonExistsException {
		validSeason(season);
		try { 
			SeasonDAO seasonDAO = new SeasonDAO ();
			Season sExisting = seasonDAO.select(season.getNumber(), season.getIdSerie());
			
			if (sExisting == null)
				throw new SeasonExistsException("Imposível atualizar, esta temporada não existe!");
		} catch (SQLException e) {
			e.printStackTrace(); //FIXME
		}
	}
}

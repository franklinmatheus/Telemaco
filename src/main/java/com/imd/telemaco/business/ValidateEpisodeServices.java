package com.imd.telemaco.business;

import java.sql.SQLException;

import com.imd.telemaco.business.exception.EpisodeExistsException;
import com.imd.telemaco.business.exception.EpisodeInvalidException;
import com.imd.telemaco.data.EpisodeDAO;
import com.imd.telemaco.entity.Episode;

/**
 * Class to validate all services offered by Episode class
 * 
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 12 de abr de 2018 | 00:46:38
 */
public class ValidateEpisodeServices {
	/**
	 * Default constructor
	 */
	public ValidateEpisodeServices () {	}
	
	/**
	 * Verify if the name input is valid and correct
	 * @param name
	 * @throws EpisodeInvalidException
	 */
	private void validEpisodeName (String name) throws EpisodeInvalidException {
		if (name == null || name.isEmpty() || name.trim().equals(""))
			throw new EpisodeInvalidException ("Nome inválido para episódio");
	}
	
	/**
	 * Verify if the number input is valid and correct
	 * @param number
	 * @throws EpisodeInvalidException
	 */
	private void validEpisodeNumber (int number) throws EpisodeInvalidException {
		if (number <= 0)
			throw new EpisodeInvalidException("Número de episódio inválido");
	}
	
	/**
	 * Verify if the episode input is valid and correct
	 * @param time
	 * @throws EpisodeInvalidException
	 */
	private void validEpisodeTime(int time) throws EpisodeInvalidException {
		if (time <= 0)
			throw new EpisodeInvalidException("Tempo de episódio inválido");
	}
	
	/**
	 * Verify if the synopsis input is valid and correct
	 * @param synopsis
	 * @throws EpisodeInvalidException
	 */
	private void validSeasonSynopsis (String synopsis) throws EpisodeInvalidException {
		if (synopsis == null || synopsis.isEmpty() || synopsis.trim().equals(""))
			throw new EpisodeInvalidException ("Sinopse inválida");
	}
	
	/**
	 * Verify if all inputs are valid and correct
	 * @param episode
	 * @throws EpisodeInvalidException
	 */
	public void validEpisode (Episode episode) throws EpisodeInvalidException {
		validEpisodeName(episode.getName());
		validEpisodeNumber(episode.getNumber());
		validEpisodeTime(episode.getTime());
		validSeasonSynopsis(episode.getSynopsis());
	}
	
	/**
	 * Throw a exception if the episode already exists, be for an episode has the same 
	 * name in the season or the same number
	 * 
	 * @param episode
	 * @throws EpisodeExistsException
	 */
	public void validEpisodeExistence (Episode episode) throws EpisodeExistsException {
		try {
			EpisodeDAO epDAO = new EpisodeDAO ();
			Episode epByName = epDAO.select(episode.getName(), episode.getIdSeason());
			Episode epByNumber = epDAO.select(episode.getNumber(), episode.getIdSeason());
			
			if (epByName != null)
				throw new EpisodeExistsException("O nome deste episódio já existe");
			if (epByNumber != null)
				throw new EpisodeExistsException("O número deste episódio já existe");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verify if the episode can be insert in the database, validating the data and 
	 * verifying if the episode already exists in the database
	 *  
	 * @param episode
	 * @throws EpisodeExistsException
	 * @throws EpisodeInvalidException
	 */
	public void validEpisodeInsert (Episode episode) throws EpisodeExistsException, EpisodeInvalidException {
		validEpisode(episode);
//		validEpisodeExistence(episode);
		try {
			EpisodeDAO episodeDAO = new EpisodeDAO();
			episodeDAO.insert(episode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verify if the episode exists in the database to be deleted
	 * @param 	episode
	 * @throws 	EpisodeExistsException
	 */
	public void validEpisodeRemove (Episode episode) throws EpisodeExistsException {
		try {
			EpisodeDAO epDAO = new EpisodeDAO ();
			Episode epById = epDAO.select(episode.getId());
			
			if (epById == null)
				throw new EpisodeExistsException("Este episódio não existe");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verify if a episode can be updated
	 * @param 	episode
	 * @throws 	EpisodeExistsException
	 * @throws 	EpisodeInvalidException
	 */
	public void validEpisodeUpadate (Episode episode) throws EpisodeExistsException, EpisodeInvalidException {
		validEpisodeInsert(episode);		
		try {
			EpisodeDAO epDAO = new EpisodeDAO ();
			Episode epById = epDAO.select(episode.getId());
			
			if (epById == null)
				throw new EpisodeExistsException("Impossível atualizar, este episódio não existe!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

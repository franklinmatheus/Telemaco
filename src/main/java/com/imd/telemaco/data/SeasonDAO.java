package com.imd.telemaco.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Episode;

/**
 * 
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10.04.2018
 */
public class SeasonDAO implements DAO<Season> {
    private Connection connection;
    private Statement stm;
    private static SeasonDAO seasonDAO = null;
    
    public SeasonDAO () throws SQLException {
    	this.connection = ConnectionFactory.getConnection();
    }
    
    public static synchronized SeasonDAO getInstance () throws SQLException {
    	if (seasonDAO == null) 
    		seasonDAO = new SeasonDAO();
    	return seasonDAO;
    }
    
    /**
     * Register a season in the database
     * @param  season
     * @throws SQLException
     */
    public void insert (Season season) throws SQLException {
    	String sql = "INSERT INTO telemaco.season (number, fkIdSerie, amountEp) VALUES (?, ?, ?)";
    	try {
    		PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, season.getNumber());
			statement.setInt(2, season.getIdSerie());
			statement.setInt(3, season.getEpAmount());
			statement.execute();
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	} finally {
    		connection.close();
    	}
    }
    
    /**
     * Select a season from the database since the name
     * @param  id
     * @return season
     * @throws SQLException 
     */
    public Season select (int id) throws SQLException {
    	String sql = "SELECT * FROM telemaco.season WHERE id = '" + id + "'";
    	Season season = new Season();
    	
    	try {
    		stm = connection.createStatement();
    		ResultSet result = stm.executeQuery(sql);
    		
    		boolean isThere = result.next();
    		
    		if (isThere) {
    			int number	  = result.getInt("number");
    			EpisodeDAO epDAO = new EpisodeDAO();
    			ArrayList <Episode> episodes = epDAO.selectAllEpisodes(id);
    			int fkIdSerie = result.getInt("fkIdSerie");
    			
    			season = new Season (id, number, episodes, fkIdSerie);
    		} else season = null;
    		
    		return season;
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	} finally {
    		stm.close();
    		connection.close();
    	}
    }
    
    /**
     * Returns all the episodes of the Season
     * @param  id
     * @return episodes
     * @throws SQLException
     */
    public ArrayList<Season> selectAllSeasons (int idSerie) throws SQLException {
    	ArrayList <Season> seasons = new ArrayList <Season>();
    	String sql = "SELECT * FROM telemaco.season WHERE fkIdSerie = '" + idSerie + "'";
    	
    	try {
    		stm = connection.createStatement();
    		ResultSet result = stm.executeQuery(sql);
    		
    		while (result.next()) {
    			int idSeason = result.getInt("id");
    			Season s = select(idSeason);
    			seasons.add(s);
    		}
			
    		return seasons;
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	} finally {
    		stm.close();
    		connection.close();
    	}
    }

	@Override
	public void delete(Season season) throws SQLException {
		String sql = "REMOVE FROM telemaco.season WHERE id ='" + season.getId() + "'";
		
		try {
			stm = connection.createStatement();
			stm.execute(sql);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			stm.close();
			connection.close();
		}
	}

	@Override
	public void update(Season season) throws SQLException {
		String sql = "UPDATE FROM telemaco.season "
				+ "number=?, "
				+ "amountEp=?, "
				+ "fkIdSeason=? "
				+ "WHERE id=?"; 
		
		try {
			PreparedStatement pStm = connection.prepareStatement(sql);//
			pStm.setInt(1, season.getNumber());
			pStm.setInt(2, season.getEpAmount());
			pStm.setInt(3, season.getIdSerie());
			pStm.setInt(4, season.getId());
			pStm.execute();
			
			System.out.println("SUCESS");
		} catch (SQLException e) {
			System.out.println("EXCEPTION");
		} finally {
			connection.close();
		}
	}
}
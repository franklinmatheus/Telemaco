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
 * Class that represent the table season of the database telemaco
 * 
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 11 de abr de 2018 | 23:29:36
 */
public class SeasonDAO implements DAO<Season>, DAOSeasonSpecialOperations {
	private Connection connection;
    private Statement stm;
    private static SeasonDAO seasonDAO = null;
    
    /**
     * Default constructor
     * @throws SQLException
     */
    public SeasonDAO () throws SQLException {
    	this.connection = ConnectionFactory.getConnection();
    }
    
    /**
     * @return seasonDAO
     * @throws SQLException
     */
    public static synchronized SeasonDAO getInstance () throws SQLException {
    	if (seasonDAO == null) 
    		seasonDAO = new SeasonDAO();
    	return seasonDAO;
    }
    
    @Override
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
    
    @Override
    public Season select (int id) throws SQLException {
    	String sql = "SELECT * FROM telemaco.season WHERE id = '" + id + "'";
    	Season season = new Season();
    	
    	try {
    		stm = connection.createStatement();
    		ResultSet result = stm.executeQuery(sql);
    		
    		boolean isThere = result.next();
    		
    		if (isThere) {
    			int number	    			 = result.getInt("number");
    			EpisodeDAO epDAO 			 = new EpisodeDAO();
    			ArrayList <Episode> episodes = epDAO.selectAllEpisodes(id);
    			int fkIdSerie 				 = result.getInt("fkIdSerie");
    			
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
    
    @Override
    public Season select (int number, int idSerie) throws SQLException {
    	String sql = "SELECT * FROM telemaco.season WHERE fkIdSerie='" + idSerie + "' AND number='" + number + "'";
    	Season season = new Season();
    	
    	try {
    		stm = connection.createStatement();
    		ResultSet result = stm.executeQuery(sql);
    		
    		if (result.next()) {
    			int id = result.getInt("id");
    			season = select (id);
    		} else season = null;
    		
    		return season;
    	} catch (SQLException e) {
    		throw new RuntimeException (e);
    	} finally {
    		connection.close();
    	}
    }
    
    @Override
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
		String sql = "DELETE FROM telemaco.season WHERE id ='" + season.getId() + "'";
		
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
package com.imd.telemaco.data;

import com.imd.telemaco.entity.Episode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EpisodeDAO implements DAO<Episode> {
	private Connection connection;
	private Statement stm;
	private static EpisodeDAO epDAO = null;	
	
	public EpisodeDAO () throws SQLException{
		this.connection = ConnectionFactory.getConnection();
	}
	
	public static synchronized EpisodeDAO getInstance () throws SQLException {
		if (epDAO == null)
			epDAO = new EpisodeDAO();
		return epDAO;
	}
	
	public void insert (Episode epsiode) {
		String sql = "INSERT INTO epsiode (id) VALUES (?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			// TODO
			
		} catch (SQLException e) {
			throw new RuntimeException (e);
		}
	}

	@Override
	public Episode select(int id) throws SQLException {
		String sql = "SELECT * FROM telemaco.episode WHERE id='" + id + "'";
		Episode episode = null;
		
		try {
			stm = connection.createStatement();
			ResultSet result = stm.executeQuery(sql);
			
			if (result.next()) {
				String name 	= result.getString("name");
				int number  	= result.getInt("number");
				int time 		= result.getInt("time");
				String synopsis = result.getString("synopsis");
				
				episode.setId(id);
				episode.setName(name);
				episode.setNumber(number);
				episode.setTime(time);
				episode.setSynopsis(synopsis);
			}
			
			return episode;
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			stm.close();
			connection.close();
		}
	}

	@Override
	public void delete(Episode object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Episode object) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
package com.imd.telemaco.data;

import com.imd.telemaco.entity.Episode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public void insert (Episode episode) throws SQLException {
		String sql = "INSERT INTO epsiode (id, name, number, time, synopsis, fkIdSeason) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, episode.getId());
			statement.setString(2, episode.getName());
			statement.setInt(3, episode.getNumber());
			statement.setInt(4, episode.getTime());
			statement.setString(5, episode.getSynopsis());
			statement.setInt(6, episode.getIdSeason());

			statement.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			connection.close();
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
				int fkIdSeason  = result.getInt("fkIdSeason");
				
				episode = new Episode (id, name, number, time, synopsis, fkIdSeason);
			}
			
			return episode;
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			stm.close();
			connection.close();
		}
	}
	
	public ArrayList<Episode> selectAllEpisodes (int idSeason) throws SQLException {
		String sql = "SELECT * FROM telemaco.episode WHERE fkIdSeason='" + idSeason + "'";
		
		try {
			stm = connection.createStatement();
			ResultSet result = stm.executeQuery(sql);
			
			ArrayList <Episode> episodes = new ArrayList<Episode>();
			
			while (result.next()) {
				int idEp = result.getInt("id");
				Episode e = select(idEp);
				episodes.add(e);
			}

			return episodes;
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			connection.close();
		}
	}

	@Override
	public void delete(Episode episode) throws SQLException {
		String sql = "REMOVE * FROM telemaco.episode WHERE id='" + episode.getId() + "'";
		
		try {
			stm = connection.createStatement();
			stm.execute(sql);
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			connection.close();
		}
	}

	@Override
	public void update(Episode episode) throws SQLException {
		String sql = "UPDATE telemaco.episode SET "
				+ "name=?, "
				+ "number=?, "
				+ "time=?, "
				+ "synopsis=?, "
				+ "fkIdSeason=? "
				+ "WHERE id=?";
		
		try {
			PreparedStatement pStm = connection.prepareStatement(sql);
			pStm.setString(1, episode.getName());
			pStm.setInt(2, episode.getNumber());
			pStm.setInt(3, episode.getTime());
			pStm.setString(4, episode.getSynopsis());
			pStm.setInt(5, episode.getIdSeason());
			pStm.setInt(6, episode.getId());
			
			pStm.execute();
			
			System.out.println("SUCESS");
		} catch (SQLException e) {
			System.out.println("EXCEPTION");
		} finally {
			connection.close();
		}
	}
}
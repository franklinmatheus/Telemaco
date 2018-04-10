package com.imd.telemaco.data;

import com.imd.telemaco.entity.Episode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EpisodeDAO {
	public final Connection connection;
	public static final Episode episodeDAO = null;
	
	private EpisodeDAO () throws SQLException{
		this.connection = ConnectionFactory.getConnection();
	}
	
	public static synchronized EpisodeDAO getInstance () {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public void insert (Episode epsiode) {
		String sql = "INSERT INTO epsiode (id) VALUES (?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);

		} catch (SQLException e) {
			throw new RuntimeException (e);
		}
	}
}
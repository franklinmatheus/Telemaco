/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.imd.telemaco.entity.Season;

/**
 * 
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10.04.2018
 */
public class SeasonDAO /*implements DAO*/{
    private final Connection connection; 			/*< connection - it do the connection with the database. */
    private static final Season seasonDAO = null;   /*< seasonDAO   */
    
    private SeasonDAO () throws SQLException {
    	this.connection = ConnectionFactory.getConnection();
    }
    
    public static synchronized SeasonDAO getInstance () {
    	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Register a season in the database
     * @param  season
     * @throws SQLException
     */
    public void insert (Season season) throws SQLException {
    	String sql = "INSERT INTO season (id) VALUES (?)";
    	try {
    		PreparedStatement statement = connection.prepareStatement(sql);
    		statement.setInt(1, season.getNumber());
    		statement.setInt(2, season.getEpAmount());
    		statement.execute();
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	} finally {
    		connection.close();
    	}
    }
    
    /**
     * Select a season from the database since the name
     * @param  number
     * @return season
     */
    public Season select (int number) {
    	String sql = "SELECT * FROM season WHERE number = '" + number + "'";
    	Season season = new Season();
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet result = statement.executeQuery(sql);
    		
    		connection.close();
    		
    		boolean isThere = result.next();
    		
    		if (isThere) season.setNumber(number);
    		else season = null;
    		
    		return season;
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.enums.Classification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public class SerieDAO implements DAOSerieSpecialOperations {
    private Connection connection;
    private static SerieDAO serieDAO = null;
    
    public SerieDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    /**
     * 
     * @return 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     */
    public static synchronized SerieDAO getInstance() throws DatabaseException {
        if(serieDAO == null)
            serieDAO = new SerieDAO();
        return serieDAO;
    }
    
    private void startsConnection() throws DatabaseException {
        try {
            if(this.connection.isClosed())
                this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new DatabaseException();
        }   
    }
    
    @Override
    public void insert(Serie serie) throws DatabaseException, CloseConnectionException {
        String sql = "INSERT INTO telemaco.serie (name, year, status, creator, classification, genre, synopsis, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            this.startsConnection();
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, serie.getName());
            stm.setInt(2, serie.getYear());
            stm.setString(3, serie.getStatus());
            stm.setString(4,  serie.getCreator());
            stm.setString(5, serie.classifToString());
            stm.setString(6, serie.getGenre());
            stm.setString(7, serie.getSynopsis());
            stm.setString(8, serie.getImage());
            
            stm.execute();
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }
    
    @Override
    public Serie select(int id) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.serie WHERE id='" + id + "'";
        Serie serie = new Serie();
        
        try {
            this.startsConnection();
            
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if(result.next()) {
                String name     = result.getString("name");
                int    year     = result.getInt("year");
                String status   = result.getString("status");
                String creator  = result.getString("creator");
                String classif  = result.getString("classification");
                String genre    = result.getString("genre");
                String synopsis = result.getString("synopsis");
                String image    = result.getString("image");
                
                SeasonDAO seasonDAO = new SeasonDAO();
                ArrayList<Season> seasons = seasonDAO.selectAllSeasons(id);
                Classification classification = serie.stringToClassif(classif);
                
                serie = new Serie(id, name, year, status, creator, classification, genre, synopsis, image, seasons);
            } else
                serie = null;
            
            return serie;
        } catch(SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch(SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }
    

    @Override
    public Serie select (String name) throws DatabaseException, CloseConnectionException {
    	String sql = "SELECT * FROM telemaco.serie WHERE name='" + name + "'";
    	Serie serie = null;    	

    	try {
            this.startsConnection();
            
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
    		
            if (result.next()) {
    		int id = result.getInt("id");
                serie = select(id);
            }
            
            return serie;
    	} catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }
    
    @Override
    public ArrayList<Serie> selectAllSeries () throws DatabaseException, CloseConnectionException {
    	ArrayList <Serie> series = new ArrayList<Serie>();
    	String sql = "SELECT * FROM telemaco.serie";
    	
    	try {
            this.startsConnection();
            
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            while (result.next()) {
            	int id = result.getInt("id");
                Serie serie = select(id);
                series.add(serie);
            }

            return series;
    	} catch (SQLException e) {
            throw new DatabaseException();
    	} finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public void delete(Serie serie) throws DatabaseException, CloseConnectionException {
        String sql = "DELETE FROM telemaco.user WHERE id='" + serie.getId() + "'";
        try {
            this.startsConnection();
            
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            throw new RuntimeException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }
   
    @Override
    public void update(Serie serie) throws DatabaseException, CloseConnectionException {
        String sql = "UPDATE telemaco.user SET "
                + "name=?, "
                + "year=?, "
                + "status=?, "
                + "creator=?, "
                + "classification=?, "
                + "genre=?, "
                + "synopsis=? ,"
                + "image=? "
                + "WHERE id=?";
        try {
            this.startsConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, serie.getName());
            stm.setInt(2, serie.getYear());
            stm.setString(3, serie.getStatus());
            stm.setString(4,  serie.getCreator());
            stm.setString(5, serie.classifToString());
            stm.setString(6, serie.getGenre());
            stm.setString(7, serie.getSynopsis());
            stm.setString(8, serie.getImage());
            stm.setInt(9, serie.getId());
            
            stm.execute();
        } catch(SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }
}

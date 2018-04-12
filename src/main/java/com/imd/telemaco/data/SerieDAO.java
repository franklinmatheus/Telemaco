/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.entity.Serie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author franklin
 */
public class SerieDAO implements DAO<Serie> {
    
    private Connection connection;
    private static SerieDAO serieDAO = null;
    
    private SerieDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public static synchronized SerieDAO getInstance() throws SQLException {
        if(serieDAO == null)
            serieDAO = new SerieDAO();
        return serieDAO;
    }
    
    private void statsConnection() throws SQLException {
        if(this.connection.isClosed())
            this.connection = ConnectionFactory.getConnection();
    }
    
    @Override
    public void insert(Serie serie) throws SQLException {
        String sql = "INSERT INTO telemaco.serie (name, id_creator) VALUES (?, ?)";
        try {
            this.statsConnection();
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, serie.getName());
            statement.setInt(2, serie.getId_creator());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) { /* empty */ }
        }
    }
    
    @Override
    public Serie select(int id) throws SQLException {
        String sql = "SELEC * FROM telemaco.serie WHERE id='" + id + "'";
        Serie serie = new Serie();
        try {
            this.statsConnection();
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            boolean exists = resultSet.next();
            
            if(exists) {
                String name = resultSet.getString("name");
                int id_creator = resultSet.getInt("id_creator");
                
                serie.setId(id);
                serie.setName(name);
                serie.setId_creator(id_creator);
            } else
                serie = null;
            
            return serie;
        } catch(SQLException e) {
            throw new RuntimeException();
        } finally {
            try {
                connection.close();
            } catch(SQLException e) { /* emtpy */ }
        }
    }

    @Override
    public void delete(Serie serie) throws SQLException {
        String sql = "DELETE FROM telemaco.user WHERE id='" + serie.getId() + "'";
        try {
            this.statsConnection();
            
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            throw new RuntimeException();
        } finally {
            try {
                connection.close();
            } catch(SQLException e) { /* emtpy */ }
        }
    }
   
    @Override
    public void update(Serie serie) throws SQLException {
        String sql = "UPDATE telemaco.user SET "
                + "name=? "
                + "id_creator=? "
                + "WHERE id=?";
        try {
            this.statsConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, serie.getName());
            statement.setInt(2, serie.getId_creator());
            statement.setInt(3, serie.getId());
            
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException();
        } finally {
            try {
                connection.close();
            } catch(SQLException e) { /* empty */ }
        }
    }
}

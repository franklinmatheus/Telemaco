/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author franklin
 */
public class SerieDAO {
    private final Connection connection;
    private ResultSet resultSet;
    
    public SerieDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public void cadastrarSerie(Serie serie) throws SQLException {
        String sql = "INSERT INTO serie (nome) VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, serie.getNome());
            
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}

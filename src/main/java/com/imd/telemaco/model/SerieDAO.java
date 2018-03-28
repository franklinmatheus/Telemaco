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
import java.sql.Statement;

/**
 *
 * @author franklin
 */
public class SerieDAO {
    
    private final Connection connection;
    private static final SerieDAO serieDAO = null;
    
    private SerieDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public static synchronized SerieDAO getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void cadastrarSerie(Serie serie) throws SQLException {
        String sql = "INSERT INTO serie (nome) VALUES (?)";
        try {//, ano, Temporadas, avaliacao
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, serie.getName());
//            statement.setString(2, serie.getYear());
//            statement.setArray(3, (Array) serie.getSeasons());
//            statement.setInt(4, serie.stars());
            
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        /**
         * Testando com nome!!!!
         * @param nome
         * @return
         * @throws SQLException 
         */
        public Serie select(String nome) throws SQLException {
        String sql;
        sql = "SELECT * FROM serie WHERE nome='" + nome  + "'";
        Serie serie = new Serie();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            boolean existe = result.next();
            
            if(existe) {
                serie.setName(nome);
            } else {
                serie = null;
            }
            return serie;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void insert(Usuario usuario) throws SQLException {
//        String sql = "INSERT INTO serie (nome) VALUES (?)";
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, usuario.getNome());
//            statement.setString(2, usuario.getEmail());
//            statement.setString(3, usuario.getSenha());
//            
//            statement.execute();
//            connection.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    
}

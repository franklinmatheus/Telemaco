/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author franklin
 */
public class UserDAO {
    private Connection connection;
    private static UserDAO userDAO = null;
    
    private UserDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    /**
     * TODO
     * @return
     * @throws SQLException 
     */
    public static synchronized UserDAO getInstance() throws SQLException {
        if(userDAO == null)
            userDAO = new UserDAO();
        return userDAO;
    }
    
    /**
     * TODO
     * @param user
     * @throws SQLException 
     */
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * TODO
     * @param email
     * @param senha
     * @return
     * @throws SQLException 
     */
    public User select(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email='" + email + "' AND senha='" + senha + "'";
        User usuario = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            boolean existe = resultSet.next();
            
            if(existe) {
                String nome = resultSet.getString("nome");
                usuario.setEmail(email);
                usuario.setName(nome);
                usuario.setPassword(senha);
            } else {
                usuario = null;
            }
            return usuario;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

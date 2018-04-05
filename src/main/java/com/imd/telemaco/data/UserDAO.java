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
import java.util.Date;

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
     * 
     * @throws SQLException 
     */
    private void startsConnection() throws SQLException {
        if(connection.isClosed())
               connection = ConnectionFactory.getConnection();
    }
    
    /**
     * TODO
     * @param user
     * @throws SQLException 
     */
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO telemaco.user (name, email, password, lastname, birth, gender) VALUES (?,?,?,?,?,?)";
        try {
            this.startsConnection();
            
            java.sql.Date date = new java.sql.Date(user.getBirth().getTime());
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getLastName());
            statement.setDate(5, date);
            statement.setString(6, user.getGender());
            
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) { /* empty */ }
        }
    }
    
    /**
     * TODO
     * @param email
     * @param password
     * @return
     * @throws SQLException 
     */
    public User select(String email, String password) throws SQLException {
        String sql = "SELECT * FROM telemaco.user WHERE email='" + email + "' AND password='" + password + "'";
        User user = new User();
        try {
            this.startsConnection();
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            boolean existe = resultSet.next();
            
            if(existe) {
                String nome = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String gender = resultSet.getString("gender");
                Date birth = resultSet.getDate("birth");
                int id = resultSet.getInt("id");
                
                user.setEmail(email);
                user.setName(nome);
                user.setPassword(password);
                user.setId(id);
                user.setGender(gender);
                user.setLastName(lastname);
                user.setBirth(birth);
            } else
                user = null;
            
            return user;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch(SQLException e) { /* empty */ }
        }
    }
    
    /**
     * TODO
     * @param user
     * @param newPassword
     */
    public void updatePassword(User user, String newPassword) {
        String sql = "UPDATE telemaco.user SET password=" + newPassword + " WHERE id=" + user.getId();
        
        try {
            this.startsConnection();
            Statement statement = connection.createStatement();
            user.setPassword(newPassword);
            statement.execute(sql);
        } catch(SQLException e) {
            
        } finally {
            try{
                connection.close();
            } catch(SQLException e) { /* empty */ }
        }
    }
}

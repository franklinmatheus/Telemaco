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
public class UserDAO implements DAO<User>{
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
    @Override
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
     * @param id
     * @return 
     */
    @Override
    public User select(int id) throws SQLException {
        String sql = "SELECT * FROM telemaco.user WHERE id='" + id + "'";
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
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                
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
     */
    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE telemaco.user SET "
                + "name=?, "
                + "email=?, "
                + "password=?, "
                + "lastname=?, "
                + "birth=?, "
                + "gender=? "
                + "WHERE id=?";
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
            statement.setInt(7, user.getId());
            
            statement.execute();
            System.out.println("SUCESS");
        } catch (SQLException e) {
            System.out.println("EXCEPTION");
            throw new RuntimeException(e);
        } finally {
            try {
                System.out.println("FINALLY");
                connection.close();
            } catch (SQLException e) { /* empty */ }
        }
    }
    
    /**
     * TODO
     * @param user 
     */
    @Override
    public void delete(User user) {
        String sql = "REMOVE FROM telemaco.user WHERE id='" + user.getId() + "'";
        
        try {
            this.startsConnection();
            
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            throw new RuntimeException();
        } finally {
            try { 
                connection.close();
            } catch(SQLException e) { /* empty */ }
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
}

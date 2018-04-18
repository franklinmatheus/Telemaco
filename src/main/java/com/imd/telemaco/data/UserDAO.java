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
public class UserDAO implements DAOUserSpecialOperations {
    private Connection connection;
    private static UserDAO userDAO = null;
    
    public UserDAO() throws SQLException {
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
     * @throws SQLException 
     */
    private void startsConnection() throws SQLException {
        if(this.connection.isClosed())
               this.connection = ConnectionFactory.getConnection();
    }
    
    @Override
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO telemaco.user (name, email, password, lastname, birthday, gender) VALUES (?,?,?,?,?,?)";
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
    
    @Override
    public User select(int id) throws SQLException {
        String sql = "SELECT * FROM telemaco.user WHERE id='" + id + "'";
        User user = new User();
        try {
            this.startsConnection();
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            boolean exists = resultSet.next();
            
            if(exists) {
                String nome = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String gender = resultSet.getString("gender");
                Date birth = resultSet.getDate("birthday");
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
    
    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE telemaco.user SET "
                + "name=?, "
                + "email=?, "
                + "password=?, "
                + "lastname=?, "
                + "birthday=?, "
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
        } catch (SQLException e) {
             new RuntimeException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) { /* empty */ }
        }
    }
    
    @Override
    public void delete(User user) {
        String sql = "DELETE FROM telemaco.user WHERE id='" + user.getId() + "'";
        
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
    
    @Override
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
                Date birth = resultSet.getDate("birthday");
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

    @Override
    public User select(String email) throws SQLException {
        String sql = "SELECT * FROM telemaco.user WHERE email='" + email + "'";
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
                String password = resultSet.getString("password");
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

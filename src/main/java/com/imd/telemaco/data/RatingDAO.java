/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author franklin
 */
public class RatingDAO implements DAORatingSpecialOperations {

    private Connection connection;
    private static RatingDAO ratingDAO = null;

    private RatingDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }

    /**
     *
     * @return @throws DatabaseException
     */
    public static synchronized RatingDAO getInstance() throws DatabaseException {
        if (ratingDAO == null) {
            ratingDAO = new RatingDAO();
        }
        return ratingDAO;
    }

    private void startsConnection() throws DatabaseException {
        try {
            if (this.connection.isClosed()) {
                this.connection = ConnectionFactory.getConnection();
            }
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public void insert(Rating rating) throws DatabaseException, CloseConnectionException {
        String sql = "INSERT INTO telemaco.rating (date, stars, rating, idfkuser, idfkserie) VALUES (?, ?, ?, ?, ?)";

        try {
            this.startsConnection();

            java.sql.Date date = new java.sql.Date(rating.getDate().getTime());

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, rating.getStars());
            stm.setString(3, rating.getComment());
            stm.setInt(4, rating.getUser().getId());
            stm.setInt(5, rating.getIdSerie());

            stm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
    public Rating select(int id) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.rating WHERE id='" + id + "'";
        Rating rating = new Rating();

        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                String content   = result.getString("rating");
                Date date = result.getDate("date");
                int stars = result.getInt("stars");
                int idUser = result.getInt("idfkuser");
                int idSerie = result.getInt("idfkserie");
                
                UserDAO userDAO = UserDAO.getInstance();
                User user = userDAO.select(idUser);
                
                rating.setComment(content);
                rating.setDate(date);
                rating.setStars(stars);
                rating.setIdSerie(idSerie);
                rating.setUser(user);
            } else {
                rating = null;
            }

            return rating;
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
    public void delete(Rating rating) throws DatabaseException, CloseConnectionException {
        String sql = "DELETE FROM telemaco.rating WHERE id='" + rating.getId() + "'";
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
    public void update(Rating rating) throws DatabaseException, CloseConnectionException {
        String sql = "UPDATE telemaco.rating SET "
                + "date=?, "
                + "rating=?, "
                + "stars=?, "
                + "idfkuser=?, "
                + "idfkserie=?, "
                + "WHERE id=?";
        try {
            this.startsConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            
            java.sql.Date date = new java.sql.Date(rating.getDate().getTime());
            
            stm.setDate(1, date);
            stm.setString(2, rating.getComment());
            stm.setInt(3, rating.getStars());
            stm.setInt(4, rating.getUser().getId());
            stm.setInt(5,  rating.getIdSerie());
            stm.setInt(6,  rating.getId());
            
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

    @Override
    public ArrayList<Rating> selectBySerie(int idSerie) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.rating WHERE idfkserie='" + idSerie + "'";
        ArrayList<Rating> ratings = new ArrayList<Rating>();

        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            UserDAO userDAO = UserDAO.getInstance();
            
            while (set.next()) {
                int id = set.getInt("id");
                String content = set.getString("rating");
                int stars = set.getInt("stars");
                Date date = set.getDate("date");
                int idUser = set.getInt("idfkuser");
                
                User user = userDAO.select(idUser);
                
                Rating rating = new Rating();
                rating.setComment(content);
                rating.setStars(stars);
                rating.setDate(date);
                rating.setIdSerie(idSerie);
                rating.setUser(user);
                rating.setId(id);
                
                ratings.add(rating);
            }

            return ratings;
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
}

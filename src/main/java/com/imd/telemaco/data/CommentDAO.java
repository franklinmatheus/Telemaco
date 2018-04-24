/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Comment;
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
public class CommentDAO implements DAOCommentSpecialOperations {

    private Connection connection;
    private static CommentDAO commentDAO = null;

    private CommentDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }

    /**
     *
     * @return @throws DatabaseException
     */
    public static synchronized CommentDAO getInstance() throws DatabaseException {
        if (commentDAO == null) {
            commentDAO = new CommentDAO();
        }
        return commentDAO;
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
    public void insert(Comment comment) throws DatabaseException, CloseConnectionException {
        String sql = "INSERT INTO telemaco.comment (date, content, idfkuser, idfkserie) VALUES (?, ?, ?, ?)";

        try {
            this.startsConnection();

            java.sql.Date date = new java.sql.Date(comment.getDate().getTime());

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setString(2, comment.getContent());
            stm.setInt(3, comment.getUser().getId());
            stm.setInt(4, comment.getSerieId());

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
    public Comment select(int id) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.comment WHERE id='" + id + "'";
        Comment comment = new Comment();

        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                String content   = result.getString("content");
                Date date = result.getDate("date");
                int idUser = result.getInt("idfkuser");
                int idSerie = result.getInt("idfkserie");
                
                UserDAO userDAO = UserDAO.getInstance();
                User user = userDAO.select(idUser);
                
                comment.setContent(content);
                comment.setDate(date);
                comment.setSerieId(idSerie);
                comment.setUser(user);
            } else {
                comment = null;
            }

            return comment;
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
    public void delete(Comment comment) throws DatabaseException, CloseConnectionException {
        String sql = "DELETE FROM telemaco.comment WHERE id='" + comment.getId() + "'";
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
    public void update(Comment comment) throws DatabaseException, CloseConnectionException {
        String sql = "UPDATE telemaco.user SET "
                + "date=?, "
                + "content=?, "
                + "idfkuser=?, "
                + "idfkserie=?, "
                + "WHERE id=?";
        try {
            this.startsConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            
            java.sql.Date date = new java.sql.Date(comment.getDate().getTime());
            
            stm.setDate(1, date);
            stm.setString(2, comment.getContent());
            stm.setInt(3, comment.getUser().getId());
            stm.setInt(4,  comment.getSerieId());
            stm.setInt(4,  comment.getId());
            
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
    public ArrayList<Comment> selectBySerie(int idSerie) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.comment WHERE idfkserie='" + idSerie + "'";
        ArrayList<Comment> comments = new ArrayList<Comment>();

        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            UserDAO userDAO = UserDAO.getInstance();
            
            while (set.next()) {
                int id = set.getInt("id");
                String content = set.getString("content");
                Date date = set.getDate("date");
                int idUser = set.getInt("idfkuser");
                
                User user = userDAO.select(idUser);
                
                Comment comment = new Comment();
                comment.setContent(content);
                comment.setDate(date);
                comment.setSerieId(idSerie);
                comment.setUser(user);
                
                comments.add(comment);
            }

            return comments;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.entity.User;
import com.imd.telemaco.entity.Serie;
import java.sql.SQLException;

/**
 *
 * @author franklin
 */
public class FacadeDAO {
    private static FacadeDAO facade = null;
    
    private FacadeDAO() { }
    
    /**
     * TODO
     * @return 
     */
    public static synchronized FacadeDAO getInstance() {
        if(facade == null)
            facade = new FacadeDAO();
        return facade;
    }
    
    /**
     * TODO
     * @param user 
     */
    public void insertUser(User user) {
        try {
            UserDAO usuarioDAO = UserDAO.getInstance();
            usuarioDAO.insert(user);
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
    
    /**
     * TODO
     * @param email
     * @param password
     * @return 
     */
    public User selectUser(String email, String password) {
        try {
            UserDAO userDAO = UserDAO.getInstance();
            User user = userDAO.select(email, password);
            return user;
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public void cadastrarSerie(Serie serie) {
        
        try {
            SerieDAO serieDAO;
            serieDAO = SerieDAO.getInstance();
            serieDAO.cadastrarSerie(serie);
        } catch(SQLException e) {
            
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

import java.sql.SQLException;

/**
 *
 * @author franklin
 */
public class FacadeDAO {
    private static FacadeDAO facade = null;
    
    private FacadeDAO() { }
    
    public static synchronized FacadeDAO getInstance() {
        if(facade == null)
            facade = new FacadeDAO();
        return facade;
    }
    
    public void cadastrarUsuario(Usuario usuario) {
        try {
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            usuarioDAO.insert(usuario);
        } catch(SQLException e) {
            
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

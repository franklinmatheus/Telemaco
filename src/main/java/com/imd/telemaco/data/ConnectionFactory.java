/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author franklin
 */
public class ConnectionFactory {
    private static final String bd = "telemaco";
    //*  mudar se estiver com host e porta diferente
    private static final String url = "jdbc:postgresql://localhost:5432/" + bd;
    private static final String usuario = "postgres";
    private static final String senha = "admin";
    private static final String driver = "org.postgresql.Driver";
    
    /**
     * TODO
     * @return
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     */
    public static Connection getConnection() throws DatabaseException {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseException();
        }
    }
}

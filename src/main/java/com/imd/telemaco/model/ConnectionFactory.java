/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

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
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new SQLException(e.getMessage());
        }
    }
}

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
    private static final String bd = "Telemaco";
    private static final String url = "jdbc:mysql://localhost/" + bd;
    private static final String usuario = "root";
    private static final String senha = "";
    private static final String driver = "com.mysql.jdbc.Driver";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
    }
}

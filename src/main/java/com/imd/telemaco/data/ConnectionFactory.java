/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

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
    private static final String url = "jdbc:mysql://localhost:3306/" + bd;
    private static final String usuario = "root";
    private static final String senha = "telemaco98";
    private static final String driver = "com.mysql.jdbc.Driver";
    
    /**
     * TODO
     * @return
     * @throws SQLException 
     */
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
    
    /*public static void main(String[] args) throws SQLException {
		System.out.println(ConnectionFactory.getConnection());
		UserDAO u = new UserDAO();
		System.out.println(u.select(1));
	}*/
}

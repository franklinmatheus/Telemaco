/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author franklin
 */
public class UsuarioDAO {
    private Connection connection;
    private static UsuarioDAO usuarioDAO = null;
    
    private UsuarioDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public static synchronized UsuarioDAO getInstance() throws SQLException {
        if(usuarioDAO == null)
            usuarioDAO = new UsuarioDAO();
        return usuarioDAO;
    }
    
    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Usuario select(String nome, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nome='" + nome + "' AND senha='" + senha + "'";
        Usuario usuario = new Usuario();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            boolean existe = resultSet.next();
            
            if(existe) {
                String email = resultSet.getString("email");
                usuario.setEmail(email);
                usuario.setNome(nome);
                usuario.setSenha(senha);
            } else {
                usuario = null;
            }
            return usuario;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

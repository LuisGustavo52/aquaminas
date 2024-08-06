/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.dao.ConnectionFactory;
import com.linguagemII.aquaminas.modelo.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {
    public Usuario getUserByUsername(String username) {
        String sql = "SELECT username, password_hash FROM usuarios WHERE username = ?";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getString("username"), rs.getString("password_hash"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void registrarUsuario(String username, String plainPassword, String email) {
    // Gerar hash da senha
    String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

    // Inserir usuário no banco de dados
    String sql = "INSERT INTO usuarios (username, password_hash,email) VALUES (?, ?,?)";
    try (Connection con = ConnectionFactory.getInstance().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, username);
        stmt.setString(2, hashedPassword);
        stmt.setString(3, email);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
}
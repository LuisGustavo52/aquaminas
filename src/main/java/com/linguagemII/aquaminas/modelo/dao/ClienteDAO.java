/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 17933118623
 */
    public class ClienteDAO extends GenericoDAO<Cliente>{
    public void salvar(Cliente c){
        String insert = "INSERT INTO Cliente(nome,cpf,telefone) VALUES (?,?,?)";
        save(insert,c.getNome(),c.getCpf() ,c.getTelefone());
    }
    public void alterar(Cliente c){
        String update = "UPDATE Cliente SET nome=? ,cpf = ?, telefone = ? WHERE idcliente=?";
        save(update, c.getNome(),c.getCpf() ,c.getTelefone(), c.getIdCliente());
    }
    
    public void excluir(Cliente c){
        String delete = "DELETE FROM Cliente WHERE idCliente = ?";
        save(delete, c.getIdCliente());
    }
    
    public Cliente buscarPorId(int id){
        String select = "SELECT * FROM Cliente WHERE idCliente=?";
        return buscarPorId(select, new ClienteRowMapper(), id);
    }
    
    public List<Cliente> buscarTodas(){
        String select = "SELECT * FROM Cliente";
        return buscarTodos(select, new ClienteRowMapper());
    }
    

    public static class ClienteRowMapper implements RowMapper<Cliente>{
        
        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException{
            Cliente Cliente = new Cliente();
            Cliente.setIdCliente(rs.getInt("idCliente"));
            Cliente.setNome(rs.getString("nome"));
            Cliente.setCpf(rs.getString("cpf"));
            Cliente.setTelefone(rs.getString("telefone"));
            
            return Cliente;
        }
        
    }
}
    



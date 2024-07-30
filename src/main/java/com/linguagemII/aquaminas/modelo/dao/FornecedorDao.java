/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 17933118623
 */
public class FornecedorDao {
    public class FornecedorDAO extends GenericoDAO<Fornecedor>{
    public void salvar(Fornecedor f){
        String insert = "INSERT INTO Fornecedor(idFornecedor,nome,cpf,telefone) VALUES (?,?,?,?)";
        save(insert, f.getIdFornecedor(),f.getNome(),f.getCpf() ,f.getTelefone());
    }
    
    public void alterar(Fornecedor f){
        String update = "UPDATE Fornecedor SET idFornecedor = ?,nome=? , cpf=?, telefone=? WHERE idFornecedor=?";
        save(update,  f.getIdFornecedor(),f.getNome(),f.getCpf() ,f.getTelefone());
    }
    
    public void excluir(Fornecedor f){
        String delete = "DELETE FROM Fornecedor WHERE idFornecedor = ?";
        save(delete, f.getIdFornecedor());
    }
    
    public Fornecedor buscarPorId(int id){
        String select = "SELECT * FROM Fornecedor WHERE idFornecedor=?";
        return buscarPorId(select, new FornecedorRowMapper(), id);
    }
    
    public List<Fornecedor> buscarTodas(){
        String select = "SELECT FROM Fornecedor";
        return buscarTodos(select, new FornecedorRowMapper());
    }
    
}
    public static class FornecedorRowMapper implements RowMapper<Fornecedor>{
        
        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException{
            Fornecedor Fornecedor = new Fornecedor();
            Fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
            Fornecedor.setNome(rs.getString("nome"));
            Fornecedor.setCpf(rs.getInt("cpf"));
            Fornecedor.setTelefone(rs.getString("telefone"));
            
            return Fornecedor;
        }
        
    }
    
}


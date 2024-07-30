/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Funcao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 17933118623
 */
    public class FuncaoDAO extends GenericoDAO<Funcao>{
    public void salvar(Funcao f){
        String insert = "INSERT INTO Funcao(funcao) VALUES (?)";
        save(insert, f.getFuncao());
    }
    
    public void alterar(Funcao f){
        String update = "UPDATE Funcao SET funcao=? WHERE idFuncao=?";
        save(update, f.getFuncao(), f.getIdFuncao());
    }
    
    public void excluir(Funcao f){
        String delete = "DELETE FROM Funcao WHERE idFuncao = ?";
        save(delete, f.getIdFuncao());
    }
    
    public Funcao buscarPorId(int id){
        String select = "SELECT * FROM Funcao WHERE idFuncao=?";
        return buscarPorId(select, new FuncaoRowMapper(), id);
    }
    
    public List<Funcao> buscarTodas(){
        String select = "SELECT FROM Funcao";
        return buscarTodos(select, new FuncaoRowMapper());
    }
    public static class FuncaoRowMapper implements RowMapper<Funcao>{
        
        @Override
        public Funcao mapRow(ResultSet rs) throws SQLException{
            Funcao Funcao = new Funcao();
            Funcao.setIdFuncao(rs.getInt("idFuncao"));
            Funcao.setFuncao(rs.getString("funcao"));
            
            return Funcao;
        }
        
    }
}



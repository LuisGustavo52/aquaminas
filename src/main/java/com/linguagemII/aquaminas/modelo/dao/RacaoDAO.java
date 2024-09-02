/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Racao;
import com.linguagemII.aquaminas.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RacaoDAO extends GenericoDAO<Racao> {
    
     
    public void salvar(Racao c){
        String insert = "INSERT INTO RACAO(NOME,PESO,VALOR,FORNECEDOR) VALUES (?,?,?,?)";
        save(insert, c.getNome(), c.getPeso(), c.getValor(),c.getFornecedor().getIdFornecedor());
    }
    
    public void alterar(Racao c){
        String update = "UPDATE RACAO SET NOME=?,PESO=?,VALOR=?,FORNECEDOR=? WHERE IDRACAO=?";
        save(update,c.getNome(),c.getPeso(), c.getValor(), c.getFornecedor(), c.getFornecedor().getIdFornecedor());
    }
    
    public void excluir(Racao c){
        String delete="DELETE FROM RACAO WHERE IDRACAO=?";
        save(delete, c.getIdRacao());
    }
    
    public Racao buscarPorId(int id){
        String select = "SELECT * FROM RACAO WHERE IDRACAO=?";
        return buscarPorId(select, new RacaoRowMapper(), id);
    }
    
    public List<Racao> buscarTodas(){
         String select = "SELECT * FROM RACAO"; 
        return buscarTodos(select, new RacaoRowMapper());
    }
    
    public static class RacaoRowMapper implements RowMapper<Racao>{
       FornecedorDAO fornecedorDAO = new FornecedorDAO();
        @Override
        public Racao mapRow(ResultSet rs) throws SQLException {
            Racao racao = new Racao();
            racao.setIdRacao(rs.getInt("idRacao"));
            racao.setNome(rs.getString("nome"));
            racao.setValor(rs.getDouble("valor"));
            racao.setPeso(rs.getDouble("peso"));
            racao.setFornecedor(fornecedorDAO.buscarPorId(rs.getInt("Fornecedor_idFornecedor")));
            return racao;
        }
        
    }
    
}

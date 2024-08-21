/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Funcionario;
import com.linguagemII.aquaminas.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao extends GenericoDAO<Funcionario> {
    
     
    public void salvar(Funcionario c){
        String insert = "INSERT INTO FUNCIONARIO(NOME,CPF,TELEFONE,FUNCAO) VALUES (?,?,?,?)";
        save(insert, c.getNome(), c.getCpf(), c.getTelefone(),c.getFuncao().getIdFuncao());
    }
    
    public void alterar(Funcionario c){
        String update = "UPDATE FUNCIONARIO SET NOME=?, CPF=?, TELEFONE=?, FUNCAO=? WHERE IDFUNCIONARIO=?";
        save(update, c.getNome(), c.getCpf(), c.getTelefone(), c.getFuncao().getIdFuncao(), c.getIdFuncionario());

    }
    
    public void excluir(Funcionario c){
        String delete="DELETE FROM FUNCIONARIO WHERE IDFUNCIONARIO=?";
        save(delete, c.getIdFuncionario());
    }
    
    public Funcionario buscarPorId(int id){
        String select = "SELECT * FROM FUNCIONARIO WHERE IDFUNCIONARIO=?";
        return buscarPorId(select, new FuncionarioRowMapper(), id);
    }
    
    public List<Funcionario> buscarTodas(){
         String select = "SELECT * FROM FUNCIONARIO"; 
        return buscarTodos(select, new FuncionarioRowMapper());
    }
    
    public static class FuncionarioRowMapper implements RowMapper<Funcionario>{
       ConverteData converte = new ConverteData();
       
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setTelefone(rs.getString("Telefone"));
            funcionario.getFuncao().setIdFuncao(rs.getInt("Funcao_idFuncao"));
            return funcionario;
        }
        
    }
    
}

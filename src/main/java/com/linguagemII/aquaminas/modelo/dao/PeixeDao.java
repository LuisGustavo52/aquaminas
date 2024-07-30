/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Peixe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 17933118623
 */
    
/**
 *
 * @author 10414032675
 */
public class PeixeDAO extends GenericoDAO<Peixe>{
    public void salvar(Peixe p){
        String insert = "INSERT INTO Peixe(nome_cientifico, valor_Unid, especie) VALUES (?,?,?)";
        save(insert, p.getIdPeixe(),p.getValor_Unid(),p.getEspecie() ,p.getNome_cientifico());
    }
    
    public void alterar(Peixe p){
        String update = "UPDATE Peixe SET nome = ?, valor_Unid = ? ,especie = ? WHERE idPeixe = ?";
        save(update, p.getIdPeixe(),p.getValor_Unid(),p.getEspecie() ,p.getNome_cientifico());
    }
    
    public void excluir(Peixe p){
        String delete = "DELETE FROM Peixe WHERE idPeixe = ?";
        save(delete, p.getIdPeixe());
    }
    
    public Peixe buscarPorId(int id){
        String select = "SELECT * FROM Peixe WHERE idPeixe = ?";
        return buscarPorId(select, new PeixeRowMapper(), id);
    }
    
    public List<Peixe> buscarTodas(){
        String select = "SELECT FROM Peixe";
        return buscarTodos(select, new PeixeRowMapper());
    }
    

    public static class PeixeRowMapper implements RowMapper<Peixe>{
        
        @Override
        public Peixe mapRow(ResultSet rs) throws SQLException{
            Peixe Peixe = new Peixe();
            Peixe.setIdPeixe(rs.getInt("idPeixe"));
            Peixe.setNome_cientifico(rs.getString("nome_cientifico"));
            Peixe.setValor_Unid(rs.getDouble("valor_Unid"));
            Peixe.setEspecie(rs.getString("especie"));
            
            return Peixe;
        }
        
    }
}
    



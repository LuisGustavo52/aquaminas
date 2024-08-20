
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDAO extends GenericoDAO<Endereco>{
    public void salvar(Endereco c){
        String insert = "INSERT INTO endereco(numero,cidade,complemento,endereco, cliente_idcliente) VALUES (?,?,?,?,?)";
        save(insert, c.getNumero(), c.getCliente(), c.getComplemento(),c.getEndereco(), c.getCliente().getIdCliente());
    }
    
    public void alterar(Endereco c){
        String update = "UPDATE endereco SET numero=?,cidade=?,complemento=?,endereco=?,cliente_idcliente=? WHERE idendereco=?";
        save(update,c.getNumero(), c.getCliente(), c.getComplemento(),c.getEndereco(), c.getCliente().getIdCliente(),c.getIdEndereco());
    }
    
    public void excluir(Endereco c){
        String delete="DELETE FROM endereco WHERE idendereco=?";
        save(delete, c.getIdEndereco());
    }
    
    public Endereco buscarPorId(int id){
        String select = "SELECT * FROM endereco WHERE idendereco=?";
        return buscarPorId(select, new EnderecoRowMapper(), id);
    }
    
    public List<Endereco> buscarTodas(){
         String select = "SELECT * FROM endereco";
        return buscarTodos(select, new EnderecoRowMapper());
    }
    
    public static class EnderecoRowMapper implements RowMapper<Endereco>{
       ClienteDAO clienteDao = new ClienteDAO();
        @Override
        public Endereco mapRow(ResultSet rs) throws SQLException {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(rs.getInt("idendereco"));
            endereco.setNumero(rs.getString("numero"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setEndereco(rs.getString("endereco"));
            endereco.setCliente(clienteDao.buscarPorId(rs.getInt("cliente_idcliente")));
            return endereco;
        }
        
    }
}

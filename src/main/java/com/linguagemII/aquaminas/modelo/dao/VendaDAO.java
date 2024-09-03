
package com.linguagemII.aquaminas.modelo.dao;

import com.linguagemII.aquaminas.modelo.entidade.Racao;
import com.linguagemII.aquaminas.modelo.entidade.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendaDAO extends GenericoDAO<Venda>{
    
    public void salvar(Venda c, Integer idRacao, Integer idPeixe, Integer quantidade){
        String insert = "INSERT INTO Venda(Funcionario_idFuncionario,venda_Total,cliente_idcliente) VALUES (?,?,?)";
        save(insert, c.getFuncionario().getIdFuncionario(),c.getVenda_Total(),c.getCliente().getIdCliente());
        System.out.println("\n\n\n\nidPeixe= "+idPeixe+"\n\n\n\n");
        if (idPeixe == -1) {
            insert = "INSERT INTO `Venda_racao` (`Venda_idVenda`, `racao_idracao`, `quantidade`) VALUES ((SELECT MAX(`idVenda`) FROM `Venda`), ?, ?)";
            save(insert, idRacao, quantidade);
        }else{
            insert = "INSERT INTO `Venda_peixe` (`Venda_idVenda`, `Peixe_idPeixe`, `quantidade_Peixe`) VALUES ((SELECT MAX(`idVenda`) FROM `Venda`), ?, ?)";
            save(insert, idPeixe, quantidade);
        }
        
    }
  
    /*public List<Venda> buscarTodas(){
         String select = "SELECT v.idVenda, f.nome AS nomeFuncionario, c.nome AS nomeCliente, p.especie AS itemVendido, vp.quantidade_Peixe AS quantidade, 'Peixe' AS tipoItem FROM Venda v JOIN Func"; 
        return buscarTodos(select, new VendaRowMapper());
    }
    
    public static class VendaRowMapper implements RowMapper<Venda>{
       FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
       ClienteDAO clienteDAO = new ClienteDAO();
        @Override
        public Venda mapRow(ResultSet rs) throws SQLException {
            Venda venda = new Venda();
            venda.setIdVenda(rs.getInt("idVenda"));
            venda.setItemVenda(rs.getDouble("valor"));
            venda.setVenda_Total(rs.getDouble("venda_Total"));
            venda.setFuncionario(funcionarioDAO.buscarPorId(rs.getInt("Fornecedor_idFornecedor")));
            venda.setCliente(clienteDAO.buscarPorId(rs.getInt("cliente_idcliente")));
            return venda;
        }
        
    }*/
}

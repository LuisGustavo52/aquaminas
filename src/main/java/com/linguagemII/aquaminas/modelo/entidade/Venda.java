/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.entidade;

/**
 *
 * @author 17933118623
 */
public class Venda {
    private Integer idVenda;
    private Funcionario funcionario;
    private Integer venda_Total;
    private Cliente cliente;

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getVenda_Total() {
        return venda_Total;
    }

    public void setVenda_Total(Integer venda_Total) {
        this.venda_Total = venda_Total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}

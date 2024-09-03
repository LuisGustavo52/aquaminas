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
    private Funcionario funcionario = new Funcionario();
    private Double venda_Total;
    private Cliente cliente = new Cliente();
    private ItemVenda itemVenda = new ItemVenda(); 

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }
    
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

    public Double getVenda_Total() {
        return venda_Total;
    }

    public void setVenda_Total(Double venda_Total) {
        this.venda_Total = venda_Total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}

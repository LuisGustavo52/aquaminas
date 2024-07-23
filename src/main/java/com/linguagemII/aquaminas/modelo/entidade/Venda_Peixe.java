/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.entidade;

/**
 *
 * @author 17933118623
 */
public class Venda_Peixe {
    private Integer quantidade_Peixe;
    private Venda venda;
    private Peixe peixe;

    public Integer getQuantidade_Peixe() {
        return quantidade_Peixe;
    }

    public void setQuantidade_Peixe(Integer quantidade_Peixe) {
        this.quantidade_Peixe = quantidade_Peixe;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Peixe getPeixe() {
        return peixe;
    }

    public void setPeixe(Peixe peixe) {
        this.peixe = peixe;
    }
    
}
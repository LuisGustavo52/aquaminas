/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.modelo.entidade;

/**
 *
 * @author 13306819670
 */
public class Peixe {
    private Integer idPeixe;
    private Integer valor_Unid;
    private String especie;
    private String nome_cientifico;

    public int getIdPeixe() {
        return idPeixe;
    }

    public void setIdPeixe(int idPeixe) {
        this.idPeixe = idPeixe;
    }

    public int getValor_Unid() {
        return valor_Unid;
    }

    public void setValor_Unid(int valor_Unid) {
        this.valor_Unid = valor_Unid;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNome_cientifico() {
        return nome_cientifico;
    }

    public void setNome_cientifico(String nome_cientifico) {
        this.nome_cientifico = nome_cientifico;
    }
    
}

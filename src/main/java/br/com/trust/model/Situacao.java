/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.model;

/**
 *
 * @author Thiago
 */
public enum Situacao {
    EM_DIA("Em dia"), ATRASADA("Atrasada"),INADIMPLÊNTE("Inadimplênte"),COM_CREDITO("Com Crédito");
    private String descricao;
    
    Situacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}

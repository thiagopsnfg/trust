/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.model;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author Thiago
 */
@Named
public enum Situacao {
    EM_DIA("Em dia"), ATRASADA("Atrasada"),INADIMPLÊNTE("Inadimplênte"),COM_CREDITO("Com Crédito");
    
    private String descricao;
    
    Situacao(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public static Situacao getCOM_CREDITO() {
        return COM_CREDITO;
    }

    public static Situacao getEM_DIA() {
        return EM_DIA;
    }

    public static Situacao getINADIMPLÊNTE() {
        return INADIMPLÊNTE;
    }
     

    public static Situacao getATRASADA() {
        return ATRASADA;
    }
}

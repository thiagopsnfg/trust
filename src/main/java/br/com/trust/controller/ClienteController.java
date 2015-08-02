/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.controller;

import br.com.trust.service.ClienteService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Thiago
 */
@Named
@SessionScoped
public class ClienteController extends BasicController {
    
    @EJB
    private ClienteService clienteService;
    
    @PostConstruct
    @PostActivate
    public void init(){
    }
}

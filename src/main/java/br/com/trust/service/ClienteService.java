/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.service;

import br.com.trust.model.Cliente;
import br.com.trust.model.Situacao;
import br.com.trust.repository.ClienteRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thiago
 */
@Stateless
@LocalBean 
@TransactionAttribute(TransactionAttributeType.REQUIRED) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteService extends BasicService{
    
    @PersistenceContext(unitName = "Trust_PU")
    private EntityManager em;
    private ClienteRepository clienteRepository;

    public ClienteService() {
    }
    
    @PostConstruct
    @PostActivate
    private void init(){
        clienteRepository = new ClienteRepository(em);
    }
    
    public Cliente addCliente(Cliente cliente){
       return clienteRepository.addCliente(cliente);
        
    }
    public Cliente getCliente(int id){
      return clienteRepository.getCliente(id);
    }
    public Cliente setCliente(Cliente cliente){
       return clienteRepository.setCliente(cliente);
    }
    public void removeCliente(Cliente cliente){
      clienteRepository.removeCliente(cliente);
    }
    public List<Cliente> getClientes(){
       return clienteRepository.getClientes();
    }
    public Long getClientesCadastrados(){
        return clienteRepository.getClientesCadastrados();
    }    
    public List<Cliente> getClienteByName(String name){
        return clienteRepository.getClienteByName(name);
       
    } 
     public List<Cliente> getClienteBySituacao(Situacao situacao){
        return clienteRepository.getClienteBySituacao(situacao);
    } 
     public List<Cliente> getClientesAtivosInativos(Boolean status){
         return clienteRepository.getClientesAtivosInativos(status);
     }
     public List<Cliente> getClientesEmAtraso(){
         return clienteRepository.getClientesEmAtraso();
     }
    
}

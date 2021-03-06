/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.controller;

import br.com.trust.model.Cliente;
import br.com.trust.model.Situacao;
import br.com.trust.model.Venda;
import br.com.trust.service.ClienteService;
import br.com.trust.service.VendaService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Thiago
 */
@Named
@SessionScoped
public class ClienteController extends BasicController implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private ClienteService clienteService;
    @EJB
    private VendaService vendaService;

    private BigDecimal valorDaParcela;
    private Date primeiroVencimento;

    private int clientesCadastrados;
    private int clientesAtivos;
    private int clientesEmAtraso;
    private int clientesInadimplentes;
    private int option;
   
    private String filter;
    private List<Cliente> filtrados;
    private Cliente clienteSelecionado;
    private Venda venda;

    @PostConstruct
    @PostActivate
    public void init() {
    }

    public String doStartAddCliente() {
        setClienteSelecionado(new Cliente());
        return "addcliente.xhtml?faces-redirect=true";
    }

    public String doFinishAddCliente() {
        clienteService.addCliente(clienteSelecionado);
        setClienteSelecionado(null);
        return "cliente.xhtml?faces-redirect=true";
    }

    public String doStartAlterar() {
        return "editcliente.xhtml?faces-redirect=true";
    }

    public String doFinishAlterar() {
        clienteSelecionado = clienteService.setCliente(clienteSelecionado);
        return "cliente.xhtml?faces-redirect=true";
    }

    public void doFinishExcluir() {
        if (vendaService.getVendasOfClienteEmAberto(clienteSelecionado.getIdCliente()).size() > 0) {
            messages(FacesMessage.SEVERITY_ERROR, "Este cliente possui compras em aberto!!");
            return;
        }
        clienteService.removeCliente(clienteSelecionado);
        listarClientes();
        setOption(1);
        doFilter();
    }

    public void listarClientes() {
        System.out.println("Entrou no listarClientes()");
        clientesCadastrados = clienteService.getClientesCadastrados().intValue();
        clientesAtivos = clienteService.getClientesAtivosInativos(Boolean.FALSE).size();
        clientesEmAtraso = clienteService.getClientesEmAtraso().size();
        clientesInadimplentes = clienteService.getClienteBySituacao(Situacao.INADIMPLÊNTE).size();
    }

    public void doFilter() {
        switch (option) {
            case 1:
                System.out.println("Entrou no case 1!!");
                filtrados = clienteService.getClientes();
                System.out.println("Tamanho:" + filtrados.size());
                option = 4;
                break;
            case 2:
                System.out.println("Entrou no case 2!!");
                filtrados = clienteService.getClientesAtivosInativos(Boolean.FALSE);
                System.out.println("Tamanho:" + filtrados.size());
                option = 4;
                break;
            case 3:
                System.out.println("Entrou no case 3!!");
                filtrados = clienteService.getClienteBySituacao(Situacao.ATRASADA);
                option = 4;
                break;
            case 4:
                System.out.println("Entrou no case 4!!");
                System.out.println("Filter: " + filter);
                filtrados = clienteService.getClienteByName(filter);
                System.out.println("Length: " + filtrados.size());
                option = 4;
                break;
        }
    }

    public Situacao[] getSituacoes() {
        return Situacao.values();
    }

    //Ajax
    public void onCellEdit(CellEditEvent event) {
        System.out.println("Entrou no onCellEdit");
        Situacao situacao = (Situacao) event.getNewValue();
        Situacao oldValue = (Situacao) event.getOldValue();

        System.out.println("getNewValue: " + situacao);
        System.out.println("getOldValue: " + oldValue);

        if (situacao != null && !situacao.equals(oldValue)) {
            Cliente cliente = filtrados.get(event.getRowIndex());

            System.out.println("cliente: " + cliente.getNome());

            cliente = clienteService.setCliente(cliente);

            filtrados.set(event.getRowIndex(), cliente);
        }
    }

    
    
    
    //Getters and setters
    public int getClientesCadastrados() {
        return clientesCadastrados;
    }

    public void setClientesCadastrados(int clientesCadastrados) {
        this.clientesCadastrados = clientesCadastrados;
    }

    public int getClientesAtivos() {
        return clientesAtivos;
    }

    public void setClientesAtivos(int clientesAtivos) {
        this.clientesAtivos = clientesAtivos;
    }

    public int getClientesEmAtraso() {
        return clientesEmAtraso;
    }

    public void setClientesEmAtraso(int clientesEmAtraso) {
        this.clientesEmAtraso = clientesEmAtraso;
    }

    public int getClientesInadimplentes() {
        return clientesInadimplentes;
    }

    public void setClientesInadimplentes(int clientesInadimplentes) {
        this.clientesInadimplentes = clientesInadimplentes;
    }

    public List<Cliente> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Cliente> filtrados) {
        this.filtrados = filtrados;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getValorDaParcela() {
        return valorDaParcela;
    }

    public void setValorDaParcela(BigDecimal valorDaParcela) {
        this.valorDaParcela = valorDaParcela;
    }

    public Date getPrimeiroVencimento() {
        return primeiroVencimento;
    }

    public void setPrimeiroVencimento(Date primeiroVencimento) {
        this.primeiroVencimento = primeiroVencimento;
    }

    public VendaService getVendaService() {
        return vendaService;
    }

    public void setVendaService(VendaService vendaService) {
        this.vendaService = vendaService;
    }

}

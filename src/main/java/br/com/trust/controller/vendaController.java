/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.controller;

import br.com.trust.model.Cliente;
import br.com.trust.model.Parcela;
import br.com.trust.model.Venda;
import br.com.trust.service.ClienteService;
import br.com.trust.service.VendaService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author Thiago
 */
@Named
@SessionScoped
public class vendaController extends BasicController implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private VendaService vendaService;
    @EJB
    private ClienteService cltService;

    private Venda venda;
    private Venda vendaSelected;
    private BigDecimal valorDaParcela;
    private Date primeiroVencimento;
    private Cliente cliente;
    
    private String totaldasVendas;
    private String totalRecebido;
    private String totalSaldo;
    

    @PostConstruct
    @PostActivate
    public void init() {
        
    }
    
    public void clear(){
        venda = null;
        valorDaParcela = null;
        primeiroVencimento = null;
        cliente = null;
        totaldasVendas = null;
        totalRecebido = null;
        totalSaldo = null;
    }

    public String doStartAddVenda() {
        venda = new Venda();
        return "venda";
    }

    public String doFinishAddVenda() {
        if(venda.getTotal().compareTo(BigDecimal.TEN) < 0){
            messages(FacesMessage.SEVERITY_INFO, "O valor da venda deve ser informada!!");
            return null;
        }
        calculaParcela();
        vendaService.atualizaRecebido(venda);
        cliente.addVenda(venda);
        vendaService.addVenda(venda);
        cltService.setCliente(cliente);
        clear();
        return "cliente";
    }

    public void calculaParcela() {
        System.out.println("entrou no calculaParcela");
        if (venda.getTotal() == null || (venda.getTotal().compareTo(BigDecimal.ZERO) < 0)) {
            messages(FacesMessage.SEVERITY_ERROR, "Informe o Total da Venda!!!");
            return;
        }
        valorDaParcela = venda.getTotal()
                .subtract(venda.getEntrada())
                .divide(new BigDecimal(venda.getParcelas()), 2, RoundingMode.UP);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        primeiroVencimento = cal.getTime();

        for (int x = 0; x < venda.getParcelas(); x++) {
            Parcela parcela = new Parcela();
            parcela.setRecebido(BigDecimal.ZERO);
            parcela.setValor(valorDaParcela);
            parcela.setVencimento(cal.getTime());
            parcela.setNumeroDaParcela(x + 1);
            venda.addParcela(parcela);
            cal.add(Calendar.MONTH, 1);
        }
    }
    
    public String doStartListVendas(){
        return "listVendas";
    }
    
    public void preencherTotaisAndSetaVendaNull(){
        BigDecimal ttlVendas = new BigDecimal("0.00");
        BigDecimal ttllRecebido = new BigDecimal("0.00");
        BigDecimal ttlSaldo = new BigDecimal("0.00");
        
        List<Venda> vendas = cliente.getVendaList();
        for (Venda vnd : vendas) {
            ttlVendas = ttlVendas.add(vnd.getTotal());
            ttllRecebido = ttllRecebido.add(vnd.getRecebido());
            ttlSaldo = ttlSaldo.add(vnd.getTotal().subtract(vnd.getRecebido()));
        }
        totaldasVendas = ttlVendas.toString();
        totalRecebido = ttllRecebido.toString();
        totalSaldo = ttlSaldo.toString(); 
        
        vendaSelected = null;
    }
    
    public void switchPago(Parcela parcela){
        parcela.setPago(!parcela.getPago());
    }
   
    
    //Getter and Setters
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTotaldasVendas() {
        return totaldasVendas;
    }

    public void setTotaldasVendas(String totaldasVendas) {
        this.totaldasVendas = totaldasVendas;
    }

    public String getTotalRecebido() {
        return totalRecebido;
    }

    public void setTotalRecebido(String totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    public String getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(String totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    public Venda getVendaSelected() {
        return vendaSelected;
    }

    public void setVendaSelected(Venda vendaSelected) {
        this.vendaSelected = vendaSelected;
    }
}

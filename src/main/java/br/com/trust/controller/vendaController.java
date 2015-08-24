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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
    private BigDecimal valorDaParcela;
    private Date primeiroVencimento;
    private Cliente cliente;

    @PostConstruct
    @PostActivate
    public void init() {

    }
    
    public void clear(){
        venda = null;
        valorDaParcela = null;
        primeiroVencimento = null;
        cliente = null;
    }

    public String doStartAddVenda() {
        System.out.println("Entrou no doStartAddVenda");
        System.out.println("ID do Cliente: " + cliente.getIdCliente());
        venda = new Venda();
        return "venda";
    }

    public String doFinishAddVenda() {
        vendaService.atualizaRecebido(venda);
        cliente.addVenda(venda);
        vendaService.addVenda(venda);
        cltService.setCliente(cliente);
        clear();
        return "cliente";
    }

    public void calculaParcela() {
        if (venda.getTotal() == null || (venda.getTotal().compareTo(BigDecimal.ZERO) < 0)) {
            System.out.println("Entrou no if do calculaParcela..");
            System.out.println("Total: " + venda.getTotal());
            messages(FacesMessage.SEVERITY_ERROR, "Informe o Total da Venda!!!");
            return;
        }
        valorDaParcela = venda.getTotal()
                .subtract(venda.getEntrada())
                .divide(new BigDecimal(venda.getParcelas()), 2, RoundingMode.UP);

        System.out.println("Valor da parcela: " + valorDaParcela);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        primeiroVencimento = cal.getTime();
        System.out.println("Vencimento da primeira Parcela: " + cal.getTime());

        for (int x = 0; x < venda.getParcelas(); x++) {
            Parcela parcela = new Parcela();
            parcela.setRecebido(BigDecimal.ZERO);
            parcela.setValor(valorDaParcela);
            parcela.setVencimento(cal.getTime());
            parcela.setNumeroDaParcela(x + 1);
            venda.addParcela(parcela);
            cal.add(Calendar.MONTH, 1);
            System.out.println("proxima parcela: " + cal.getTime());
        }
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
}

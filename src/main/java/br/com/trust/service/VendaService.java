/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.service;

import br.com.trust.model.Parcela;
import br.com.trust.model.Venda;
import br.com.trust.repository.VendaRepository;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class VendaService extends BasicService {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "Trust_PU")
    private EntityManager em;
    private VendaRepository vndRepository;

    public VendaService() {
    }

    @PostConstruct
    @PostActivate
    private void init() {
        vndRepository = new VendaRepository(em);
    }

    public Venda addVenda(Venda vnd) {
        return vndRepository.addVenda(vnd);
    }

    public Venda getVenda(int id) {
        return vndRepository.getVenda(id);
    }

    public Venda setVenda(Venda vnd) {
        return vndRepository.setVenda(vnd);
    }

    public void removeVenda(Venda vnd) {
        vndRepository.removeVenda(vnd);
    }

    public List<Venda> getVendasOfCliente(int idOfCliente) {
        return vndRepository.getVendasOfCliente(idOfCliente);
    }

    public List<Venda> getVendasOfClienteEmAberto(int idOfCliente) {
        return vndRepository.getVendasOfClienteEmAberto(idOfCliente);
    }

    public List<Venda> getVendasOfClienteQuitadas(int idOfCliente) {
        return vndRepository.getVendasOfClienteQuitadas(idOfCliente);
    }

    public List<Venda> getVendasOfMonth(int month, int year) {
        return vndRepository.getVendasOfMonth(month, year);
    }

    public List<Venda> getVendasOfPeriod(Date start, Date end) {

        if (start.after(end) || start.equals(end)) {
            return null;
        }
        return vndRepository.getVendasOfPeriod(start, end);
    }

    public List<Venda> getVendasEmAberto() {
        return vndRepository.getVendasEmAberto();
    }

    public Venda setVendaQuitada(int idOfVenda) {
        return vndRepository.setVendaQuitada(idOfVenda);
    }

    public void atualizaRecebido(Venda vnd) {
        List<Parcela> parcelas = vnd.getParcelaList();
        BigDecimal recebido = BigDecimal.ZERO;
        for (Parcela parcela : parcelas) {
            recebido.add(parcela.getRecebido());
        }
        recebido.add(vnd.getEntrada());
        vnd.setRecebido(recebido);
    }

    public BigDecimal getSaldo(Venda vnd) {
        atualizaRecebido(vnd);
        return vnd.getRecebido().subtract(vnd.getTotal());
    }

}

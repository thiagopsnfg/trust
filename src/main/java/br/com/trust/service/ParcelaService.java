/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.service;

import br.com.trust.model.Parcela;
import br.com.trust.repository.ParcelaRepository;
import java.math.BigDecimal;
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
public class ParcelaService extends BasicService {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "Trust_PU")
    private EntityManager em;
    private ParcelaRepository parcelaRepository;

    public ParcelaService() {
    }

    @PostConstruct
    @PostActivate
    private void init() {
        parcelaRepository = new ParcelaRepository(em);
    }

    public Parcela addParcela(Parcela parcela) {
        return parcelaRepository.addParcela(parcela);

    }

    public Parcela getParcela(int id) {
        return parcelaRepository.getParcela(id);
    }

    public Parcela setParcela(Parcela parcela) {
        return parcelaRepository.setParcela(parcela);
    }

    public void removeParcela(Parcela parcela) {
        parcelaRepository.removeParcela(parcela);
    }

    public Parcela getParcelaOfVendaByNumeroParcela(int idOfVenda, int numeroOfParcela) {
        return parcelaRepository.getParcelaOfVendaByNumeroParcela(idOfVenda, numeroOfParcela);
    }

    public List<Parcela> getParcelasOfVenda(int idOfVenda) {
        return parcelaRepository.getParcelasOfVenda(idOfVenda);
    }

    public List<Parcela> getParcelasEmAbertoOfVenda(int idOfVenda) {
        return parcelaRepository.getParcelasEmAbertoOfVenda(idOfVenda);

    }

    public List<Parcela> getParcelasPagasOfVendas(int idOfVenda) {
        return parcelaRepository.getParcelasPagasOfVenda(idOfVenda);

    }

    public List<Parcela> getParcelaOfCliente(int idOfCliente) {
        return parcelaRepository.getParcelaOfCliente(idOfCliente);
    }

    public List<Parcela> getParcelasOfClientePago(int idOfCliente) {
        return parcelaRepository.getParcelasOfClientePago(idOfCliente);
    }

    public List<Parcela> getParcelasOfClienteEmAberto(int idOfCliente) {
        return parcelaRepository.getParcelasOfClienteEmAberto(idOfCliente);
    }

    public List<Parcela> getParcelasOfMonth(int month, int year) {
        return parcelaRepository.getParcelasOfMonth(month, year);

    }

    public List<Parcela> getParcelasOfMonthEmAberto(int month, int year) {
        return parcelaRepository.getParcelasOfMonthEmAberto(month, year);
    }

    public List<Parcela> getParcelasOfMonthPagas(int month, int year) {
        return parcelaRepository.getParcelasOfMonthPagas(month, year);
    }

    public Parcela setPagamentoParcela(int idOfParcela) {
        return parcelaRepository.setPagamentoParcela(idOfParcela);
    }

    public BigDecimal getSaldo(Parcela parcela) {
        return parcela.getValor().subtract(parcela.getRecebido());
    }

    public List<Parcela> getParcelasAtrasadas() {
        return parcelaRepository.getParcelasAtrasadas();
    }

}

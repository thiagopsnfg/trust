/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.repository;

import br.com.trust.model.Venda;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Thiago
 */
public class VendaRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;

    public VendaRepository(EntityManager em) {
        super(em);
    }

    public Venda addVenda(Venda vnd) {
        return addEntity(Venda.class, vnd);

    }

    public Venda getVenda(int id) {
        return getEntity(Venda.class, id);
    }

    public Venda setVenda(Venda vnd) {
        return setEntity(Venda.class, vnd);
    }

    public void removeVenda(Venda vnd) {
        removeEntity(vnd);
    }

    public List<Venda> getVendasOfCliente(int idOfCliente) {
        return getPureList(Venda.class, "SELECT vnd FROM Venda vnd WHERE vnd.idCliente.id = ?1", idOfCliente);
    }
    public List<Venda> getVendasOfClienteEmAberto(int idOfCliente) {
        return getPureList(Venda.class, "SELECT vnd FROM Venda vnd WHERE vnd.idCliente.id = ?1 AND vnd.quitada = ?2", idOfCliente, Boolean.FALSE);
    }
    public List<Venda> getVendasOfClienteQuitadas(int idOfCliente) {
        return getPureList(Venda.class, "SELECT vnd FROM Venda vnd WHERE vnd.idCliente.id = ?1 AND vnd.quitada = ?2", idOfCliente, Boolean.TRUE);
    }

    public List<Venda> getVendasOfMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);//Acerta o ano atual;
        cal.set(Calendar.MONTH, (month - 1));//Acerta Mês atual;Janeiro começa como 0; 0 a 11;
        cal.set(Calendar.DAY_OF_MONTH, 1);//Seta o primeiro dia do mês;

        Date dataInicial = cal.getTime();

        cal.add(Calendar.MONTH, 1);//Pula um mês a frente, tipo Jan 1 para Fev 1;
        cal.add(Calendar.DAY_OF_YEAR, -1);//Retorna um dia, ficando no ultimo dia do mês atual, fechando o mês;

        Date dataFinal = cal.getTime();

        return getPureList(Venda.class, "SELECT vnd FROM Venda vnd WHERE vnd.dataVenda BETWEEN ?1 and ?2", dataInicial, dataFinal);
    }

    public List<Venda> getVendasOfPeriod(Date start, Date end) {
        return getPureList(Venda.class, "SELECT vnd FROM Venda vnd WHERE vnd.dataVenda BETWEEN ?1 AND ?2", start, end);
    }

    public List<Venda> getVendasEmAberto() {
        return getPureList(Venda.class, "SELECT vnd FROM Venda vnd WHERE vnd.quitada = ?1", Boolean.FALSE);
    }

    public Venda setVendaQuitada(int idOfVenda) {
        Venda vnd = getVenda(idOfVenda);//Recupera
        vnd.setQuitada(!vnd.getQuitada());//Altera
        vnd = setVenda(vnd);//Atualiza
        return vnd;
    }

}

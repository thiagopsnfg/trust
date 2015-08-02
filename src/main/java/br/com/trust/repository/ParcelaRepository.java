/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.repository;

import br.com.trust.model.Parcela;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Thiago
 */
public class ParcelaRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;

    public ParcelaRepository(EntityManager em) {
        super(em);
    }

    public Parcela addParcela(Parcela parcela) {
        return addEntity(Parcela.class, parcela);

    }

    public Parcela getParcela(int id) {
        return getEntity(Parcela.class, id);
    }

    public Parcela setParcela(Parcela parcela) {
        return setEntity(Parcela.class, parcela);
    }

    public void removeParcela(Parcela parcela) {
        removeEntity(parcela);
    }
    public Parcela getParcelaOfVendaByNumeroParcela(int idOfVenda,int numeroOfParcela){
        return getPurePojo(Parcela.class,"SELECT par FROM Parcela par WHERE par.idVenda.id = ?1 AND par.numeroDaParcela = ?2",idOfVenda ,numeroOfParcela);
    }
    public List<Parcela> getParcelasOfVenda(int idOfVenda) {
        return getPureList(Parcela.class, "select par from Parcela par where par.idVenda.id = ?1", idOfVenda);
    }
    public List<Parcela> getParcelasEmAbertoOfVenda(int idOfVenda) {
        return getPureList(Parcela.class, "select par from Parcela par where par.idVenda.id = ?1 and par.pago = ?2", idOfVenda, Boolean.FALSE);

    }
     public List<Parcela> getParcelasPagasOfVenda(int idOfVenda) {
        return getPureList(Parcela.class, "select par from Parcela par where par.idVenda.id = ?1 and par.pago = ?2", idOfVenda, Boolean.TRUE);

    }
     public List<Parcela> getParcelaOfCliente(int idOfCliente) {
        return getPureList(Parcela.class, "select par from Parcela par where par.idVenda.idCliente.id = ?1", idOfCliente);
    }
     public List<Parcela> getParcelasOfClientePago(int idOfCliente) {
        return getPureList(Parcela.class, "select par from Parcela par where par.idVenda.idCliente.id = ?1 and par.pago = ?2", idOfCliente, Boolean.TRUE);
    }

    public List<Parcela> getParcelasOfClienteEmAberto(int idOfCliente) {
        return getPureList(Parcela.class, "select par from Parcela par where par.idVenda.idCliente.id = ?1 and par.pago = ?2", idOfCliente, Boolean.FALSE);
    }
     public List<Parcela> getParcelasOfMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);//Acerta o ano atual;
        cal.set(Calendar.MONTH, (month - 1));//Acerta Mês atual;Janeiro começa como 0; 0 a 11;
        cal.set(Calendar.DAY_OF_MONTH, 1);//Seta o primeiro dia do mês;

        Date dataInicial = cal.getTime();

        cal.add(Calendar.MONTH, 1);//Pula um mês a frente, tipo Jan 1 para Fev 1;
        cal.add(Calendar.DAY_OF_YEAR, -1);//Retorna um dia, ficando no ultimo dia do mês atual, fechando o mês;

        Date dataFinal = cal.getTime();

        return getPureList(Parcela.class, "SELECT parcela FROM Parcela parcela WHERE parcela.vencimento BETWEEN ?1 and ?2", dataInicial, dataFinal);
    }
     public List<Parcela> getParcelasOfMonthEmAberto(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);//Acerta o ano atual;
        cal.set(Calendar.MONTH, (month - 1));//Acerta Mês atual;Janeiro começa como 0; 0 a 11;
        cal.set(Calendar.DAY_OF_MONTH, 1);//Seta o primeiro dia do mês;

        Date dataInicial = cal.getTime();

        cal.add(Calendar.MONTH, 1);//Pula um mês a frente, tipo Jan 1 para Fev 1;
        cal.add(Calendar.DAY_OF_YEAR, -1);//Retorna um dia, ficando no ultimo dia do mês atual, fechando o mês;

        Date dataFinal = cal.getTime();

        return getPureList(Parcela.class, "SELECT parcela FROM Parcela parcela WHERE parcela.pago = ?1 AND parcela.vencimento BETWEEN ?2 AND ?3",Boolean.FALSE ,dataInicial, dataFinal);
    }
     public List<Parcela> getParcelasOfMonthPagas(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);//Acerta o ano atual;
        cal.set(Calendar.MONTH, (month - 1));//Acerta Mês atual;Janeiro começa como 0; 0 a 11;
        cal.set(Calendar.DAY_OF_MONTH, 1);//Seta o primeiro dia do mês;

        Date dataInicial = cal.getTime();

        cal.add(Calendar.MONTH, 1);//Pula um mês a frente, tipo Jan 1 para Fev 1;
        cal.add(Calendar.DAY_OF_YEAR, -1);//Retorna um dia, ficando no ultimo dia do mês atual, fechando o mês;

        Date dataFinal = cal.getTime();

        return getPureList(Parcela.class, "SELECT parcela FROM Parcela parcela WHERE parcela.pago = ?1 AND parcela.vencimento BETWEEN ?2 AND ?3",Boolean.TRUE ,dataInicial, dataFinal);
    }
     public List<Parcela> getParcelasAtrasadas() {
        Calendar cal = Calendar.getInstance();       

        Date dataAtual = cal.getTime();
       
        return getPureList(Parcela.class, "SELECT parcela FROM Parcela parcela WHERE parcela.pago = ?1 AND parcela.vencimento <= ?2" ,Boolean.FALSE ,dataAtual);
    }
     public Parcela setPagamentoParcela(int idOfParcela) {
        Parcela par = getParcela(idOfParcela);//Recupera
        par.setPago(!par.getPago());//Altera
        par = setParcela(par);//Atualiza
        return par;
    }     

}

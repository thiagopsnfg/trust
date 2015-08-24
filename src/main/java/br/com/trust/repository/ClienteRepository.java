/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.repository;

import br.com.trust.model.Cliente;
import br.com.trust.model.Situacao;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Thiago
 */
public class ClienteRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;

    public ClienteRepository(EntityManager em) {
        super(em);
    }

    public Cliente addCliente(Cliente cliente) {
        return addEntity(Cliente.class, cliente);

    }

    public Cliente getCliente(int id) {
        return getEntity(Cliente.class, id);
    }

    public Cliente setCliente(Cliente cliente) {
        return setEntity(Cliente.class, cliente);
    }

    public void removeCliente(Cliente cliente) {
        removeEntity(cliente);
    }

    public List<Cliente> getClientes() {
        return getPureList(Cliente.class, "SELECT cliente FROM Cliente cliente");
    }

    public Long getClientesCadastrados() {
        return getPurePojo(Long.class, "SELECT COUNT(cliente) FROM Cliente cliente");
    }

    public List<Cliente> getClienteByName(String name) {
        return getPureList(Cliente.class, "SELECT cliente FROM Cliente cliente WHERE cliente.nome LIKE ?1", name + "%");
    }

    public List<Cliente> getClienteBySituacao(Situacao situacao) {
        return getPureList(Cliente.class, "SELECT cliente FROM Cliente cliente WHERE cliente.situacao = ?1", situacao);
    }

    public List<Cliente> getClientesAtivosInativos(Boolean status) {
        return getPureList(Cliente.class, "SELECT DISTINCT c FROM Cliente c JOIN FETCH c.vendaList v where v.quitada = ?1", status);
    }

    public List<Cliente> getClientesEmAtraso() {
        Calendar cal = Calendar.getInstance();
        Date dataAtual = cal.getTime();
        return getPureList(Cliente.class, "SELECT DISTINCT c FROM Cliente c JOIN c.vendaList v JOIN v.parcelaList parc WHERE parc.pago = ?1 AND parc.vencimento <= ?2", Boolean.FALSE, dataAtual);
        //return getPureList(Cliente.class, "SELECT DISTINCT c FROM Cliente c JOIN FETCH c.vendaList v WHERE v = SOME (SELECT vnd FROM Venda vnd JOIN vnd.parcelaList parc WHERE parc.pago = ?1 AND parc.vencimento <= ?2)", Boolean.FALSE, dataAtual);
    }
}

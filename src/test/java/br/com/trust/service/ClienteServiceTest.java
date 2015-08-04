/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.service;

import br.com.trust.model.Cliente;
import br.com.trust.model.Situacao;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thiago
 */
public class ClienteServiceTest {

    private EJBContainer container;
    private ClienteService clienteServiceinstance;
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;

    public ClienteServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws NamingException {
        //Inicia o container.
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();       
        //Instancia o Serviço.
        clienteServiceinstance = (ClienteService) container.getContext().lookup("java:global/classes/ClienteService");
        //Mock 
        cliente1 = new Cliente();
        cliente1.setNome("cliente1_Nome: " + new Random().nextInt());
        cliente1.setBairro("cliente1_Bairro: " + new Random().nextInt());
        cliente1.setCep("CEP:");
        cliente1.setCidade("cliente1_Cidade: " + new Random().nextInt());
        cliente1.setDataRecebimento("cliente1_DataRecebimento: " + new Random().nextInt());
        cliente1.setEmail("cliente1_Email" + new Random().nextInt());
        cliente1.setEndTrabalho("cliente1_End_Trabalho" + new Random().nextInt());
        cliente1.setEndereco("cliente1_Endereco: " + new Random().nextInt());
        cliente1.setEstado("cliente1_Estado" + new Random().nextInt());
        cliente1.setReferencia("cliente1_refe:" + new Random().nextInt());
        cliente1.setSituacao(Situacao.EM_DIA);
        cliente1.setTelCelular("cliente1_Cel:" + new Random().nextInt());
        cliente1.setTelFixo("cliente1_Fixo:" + new Random().nextInt());
        cliente1.setUf("RJ");
        //Mock 
        cliente2 = new Cliente();
        cliente2.setNome("cliente2_Nome: " + new Random().nextInt());
        cliente2.setBairro("cliente2_Bairro: " + new Random().nextInt());
        cliente2.setCep("CEP:");
        cliente2.setCidade("cliente2_Cidade: " + new Random().nextInt());
        cliente2.setDataRecebimento("cliente2_DataRecebimento: " + new Random().nextInt());
        cliente2.setEmail("cliente2_Email" + new Random().nextInt());
        cliente2.setEndTrabalho("cliente2_End_Trabalho" + new Random().nextInt());
        cliente2.setEndereco("cliente2_Endereco: " + new Random().nextInt());
        cliente2.setEstado("cliente2_Estado" + new Random().nextInt());
        cliente2.setReferencia("cliente2_refe:" + new Random().nextInt());
        cliente2.setSituacao(Situacao.COM_CREDITO);
        cliente2.setTelCelular("cliente2_Cel:" + new Random().nextInt());
        cliente2.setTelFixo("cliente2_Fixo:" + new Random().nextInt());
        cliente2.setUf("SP");
        //Mock 
        cliente3 = new Cliente();
        cliente3.setNome("cliente3_Nome: " + new Random().nextInt());
        cliente3.setBairro("cliente3_Bairro: " + new Random().nextInt());
        cliente3.setCep("CEP:");
        cliente3.setCidade("cliente3_Cidade: " + new Random().nextInt());
        cliente3.setDataRecebimento("cliente3_DataRecebimento: " + new Random().nextInt());
        cliente3.setEmail("cliente3_Email" + new Random().nextInt());
        cliente3.setEndTrabalho("cliente3_End_Trabalho" + new Random().nextInt());
        cliente3.setEndereco("cliente3_Endereco: " + new Random().nextInt());
        cliente3.setEstado("cliente3_Estado" + new Random().nextInt());
        cliente3.setReferencia("cliente3_refe:" + new Random().nextInt());
        cliente3.setSituacao(Situacao.INADIMPLÊNTE);
        cliente3.setTelCelular("cliente3_Cel:" + new Random().nextInt());
        cliente3.setTelFixo("cliente3_Fixo:" + new Random().nextInt());
        cliente3.setUf("MG");
    }

    @After
    public void tearDown() {
        //Finaliza o container.
        container.close();
        cliente1 = null;
        cliente2 = null;
        cliente3 = null;
    }

    /**
     * Test of addCliente method, of class ClienteService.
     */
    //@Test
    public void testAddCliente() throws Exception {
        System.out.println("addCliente");

        Cliente expResult = cliente1;
        Cliente result = clienteServiceinstance.addCliente(cliente1);
        assertEquals(expResult, result);

        clienteServiceinstance.removeCliente(cliente1);

    }

    /**
     * Test of getCliente method, of class ClienteService.
     */
    //@Test
    public void testGetCliente() throws Exception {
        System.out.println("getCliente");

        clienteServiceinstance.addCliente(cliente2);
        int id = cliente2.getIdCliente();
        Cliente result = clienteServiceinstance.getCliente(id);
        assertEquals(cliente2, result);

        clienteServiceinstance.removeCliente(cliente2);
    }

    /**
     * Test of setCliente method, of class ClienteService.
     */
    //@Test
    public void testSetCliente() throws Exception {
        System.out.println("setCliente");

        clienteServiceinstance.addCliente(cliente3);
        cliente3.setNome("AlterandoNome" + new Random().nextInt());
        Cliente result = clienteServiceinstance.setCliente(cliente3);
        assertEquals(cliente3, result);
        clienteServiceinstance.removeCliente(cliente3);
    }

    /**
     * Test of removeCliente method, of class ClienteService.
     */
    //@Test
    public void testRemoveCliente() throws Exception {
        System.out.println("removeCliente");

        Cliente cliente = clienteServiceinstance.addCliente(cliente1);
        clienteServiceinstance.removeCliente(cliente);
        Cliente retorno = clienteServiceinstance.getCliente(cliente.getIdCliente());
        assertNull(retorno);
    }

    /**
     * Test of getClientes method, of class ClienteService.
     */
    //@Test
    public void testGetClientes() throws Exception {
        clienteServiceinstance.addCliente(cliente1);
        clienteServiceinstance.addCliente(cliente2);
        clienteServiceinstance.addCliente(cliente3);

        List<Cliente> expClientes = new LinkedList<>();
        expClientes.add(cliente1);
        expClientes.add(cliente2);
        expClientes.add(cliente3);

        List<Cliente> result = clienteServiceinstance.getClientes();
        assertTrue(result.containsAll(expClientes));
        assertEquals(result.size(), expClientes.size());

        clienteServiceinstance.removeCliente(cliente1);
        clienteServiceinstance.removeCliente(cliente2);
        clienteServiceinstance.removeCliente(cliente3);

    }

    /**
     * Test of getClienteByName method, of class ClienteService.
     */
    //@Test
    public void testGetClienteByName() throws Exception {
        clienteServiceinstance.addCliente(cliente1);
        String name = cliente1.getNome();
        List<Cliente> expResult = new LinkedList<>();
        expResult.add(cliente1);
        List<Cliente> result = clienteServiceinstance.getClienteByName(name);
        assertTrue(result.containsAll(expResult));
        assertEquals(expResult.get(0).getNome(), result.get(0).getNome());
        clienteServiceinstance.removeCliente(cliente1);
    }

    /**
     * Test of getClienteBySituacao method, of class ClienteService.
     */
   //@Test
    public void testGetClienteBySituacao() throws Exception {
        cliente1.setSituacao(cliente2.getSituacao());        
        
        clienteServiceinstance.addCliente(cliente1);
        clienteServiceinstance.addCliente(cliente2);
        clienteServiceinstance.addCliente(cliente3);
        
        Situacao situacao = cliente2.getSituacao();
        List<Cliente> expResult = new LinkedList<>();
        expResult.add(cliente1);
        expResult.add(cliente2);
         List<Cliente> result = clienteServiceinstance.getClienteBySituacao(situacao);
         assertTrue(result.containsAll(expResult));
         assertEquals(result.size(), expResult.size());
         
         clienteServiceinstance.removeCliente(cliente1);
         clienteServiceinstance.removeCliente(cliente2);
         clienteServiceinstance.removeCliente(cliente3);
         
    }

}

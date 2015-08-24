/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.service;

import br.com.trust.model.Cliente;
import br.com.trust.model.Parcela;
import br.com.trust.model.Situacao;
import br.com.trust.model.Venda;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
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
    private VendaService vndServiceInstance;
    private ParcelaService parcServiceinstance;
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;

    private Venda vndOne;
    private Venda vndTwo;
    private Venda vndThree;

    private Parcela prcOne;
    private Parcela prcTwo;
    private Parcela prcThree;
    private Parcela prcFour;
    private Parcela prcFive;

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
        vndServiceInstance = (VendaService) container.getContext().lookup("java:global/classes/VendaService");
        parcServiceinstance = (ParcelaService) container.getContext().lookup("java:global/classes/ParcelaService");

        //Mock Cliente
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
        clienteServiceinstance.addCliente(cliente1);

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
        clienteServiceinstance.addCliente(cliente2);

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
        clienteServiceinstance.addCliente(cliente3);

        //Mocks Vendas
        vndOne = new Venda();
        vndOne.setDataVenda(new Date());
        vndOne.setEntrada(BigDecimal.ZERO);
        vndOne.setParcelas(5);
        vndOne.setQutPeças(new Random().nextInt(11));
        vndOne.setTotal(new BigDecimal("199.90").setScale(2, RoundingMode.UP));

        vndTwo = new Venda();
        vndTwo.setDataVenda(new Date());
        vndTwo.setEntrada(BigDecimal.ZERO);
        vndTwo.setParcelas(5);
        vndTwo.setQutPeças(new Random().nextInt(11));
        vndTwo.setTotal(new BigDecimal("279.90").setScale(2, RoundingMode.UP));

        vndThree = new Venda();
        vndThree.setDataVenda(new Date());
        vndThree.setEntrada(BigDecimal.ZERO);
        vndThree.setParcelas(5);
        vndThree.setQutPeças(new Random().nextInt(11));
        vndThree.setTotal(new BigDecimal("59.90").setScale(2, RoundingMode.UP));
    }

    @After
    public void tearDown() {
        //Finaliza o container.
        container.close();
        cliente1 = null;
        cliente2 = null;
        cliente3 = null;
        vndOne = null;
        vndTwo = null;
        vndThree = null;
        prcOne = null;
        prcTwo = null;
        prcThree = null;
        prcFour = null;
        prcFive = null;
    }

    private void startParcelas(Venda vnd, boolean state) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        System.out.println("Data Vencimento da Primeira Parcela: " + cal.getTime());

        prcOne = new Parcela();
        prcOne.setPago(state);
        prcOne.setRecebido(BigDecimal.ZERO);
        prcOne.setValor(vnd.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcOne.setVencimento(cal.getTime());
        prcOne.setIdVenda(vnd);
        prcOne.setNumeroDaParcela(1);
        vnd.addParcela(prcOne);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Segunda Parcela: " + cal.getTime());
        prcTwo = new Parcela();
        prcTwo.setPago(state);
        prcTwo.setRecebido(BigDecimal.ZERO);
        prcTwo.setValor(vnd.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcTwo.setVencimento(cal.getTime());
        prcTwo.setIdVenda(vnd);
        prcTwo.setNumeroDaParcela(2);
        vnd.addParcela(prcTwo);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Terceira Parcela: " + cal.getTime());
        prcThree = new Parcela();
        prcThree.setPago(state);
        prcThree.setRecebido(BigDecimal.ZERO);
        prcThree.setValor(vnd.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcThree.setVencimento(cal.getTime());
        prcThree.setIdVenda(vnd);
        prcThree.setNumeroDaParcela(3);
        vnd.addParcela(prcThree);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Quarta Parcela: " + cal.getTime());
        prcFour = new Parcela();
        prcFour.setPago(state);
        prcFour.setRecebido(BigDecimal.ZERO);
        prcFour.setValor(vnd.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcFour.setVencimento(cal.getTime());
        prcFour.setIdVenda(vnd);
        prcFour.setNumeroDaParcela(4);
        vnd.addParcela(prcFour);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Quinta Parcela: " + cal.getTime());
        prcFive = new Parcela();
        prcFive.setPago(state);
        prcFive.setRecebido(BigDecimal.ZERO);
        prcFive.setValor(vnd.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcFive.setVencimento(cal.getTime());
        prcFive.setIdVenda(vnd);
        prcFive.setNumeroDaParcela(5);
        vnd.addParcela(prcFive);

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
     * Test of addCliente method, of class ClienteService.
     */
    //@Test
    public void testClientesCadastrados() throws Exception {
        System.out.println("ClientesCadastrados");

        cliente1 = clienteServiceinstance.addCliente(cliente1);
        cliente2 = clienteServiceinstance.addCliente(cliente2);
        cliente3 = clienteServiceinstance.addCliente(cliente3);
        Long retorno = clienteServiceinstance.getClientesCadastrados();
        Long expResult = new Long(3);
        System.out.println("expResult:" + expResult);
        System.out.println("retorno:" + retorno);
        assertEquals(retorno, expResult);

        clienteServiceinstance.removeCliente(cliente1);
        clienteServiceinstance.removeCliente(cliente2);
        clienteServiceinstance.removeCliente(cliente3);

    }

    /**
     * Test of addCliente method, of class ClienteService.
     */
    //@Test
    public void testGetClientesAtivosInativos() throws Exception {
        System.out.println("GetClientesAtivosInativos");

        vndOne.setIdCliente(cliente1);
        vndTwo.setIdCliente(cliente2);
        vndThree.setIdCliente(cliente3);

        cliente1.addVenda(vndOne);
        cliente2.addVenda(vndTwo);
        cliente3.addVenda(vndThree);

        vndServiceInstance.addVenda(vndOne);
        vndServiceInstance.addVenda(vndTwo);
        vndServiceInstance.addVenda(vndThree);

        clienteServiceinstance.setCliente(cliente1);
        clienteServiceinstance.setCliente(cliente2);
        clienteServiceinstance.setCliente(cliente3);

        List<Cliente> expClientes = new LinkedList<>();
        expClientes.add(cliente3);

        List<Cliente> result = clienteServiceinstance.getClientesAtivosInativos(Boolean.FALSE);

        System.out.println("result Size:" + result.size());

        assertTrue(expClientes.containsAll(result));
        assertEquals(expClientes, result);

        vndServiceInstance.removeVenda(vndOne);
        vndServiceInstance.removeVenda(vndTwo);
        vndServiceInstance.removeVenda(vndThree);

        clienteServiceinstance.removeCliente(cliente1);
        clienteServiceinstance.removeCliente(cliente2);
        clienteServiceinstance.removeCliente(cliente3);
    }

    /**
     * Test of getClientesEmAtraso method, of class ClienteService.
     */
    //@Test
    public void testGetClientesEmAtraso() throws Exception {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        
        System.out.println("Data atrasada:"+ c.getTime().toString());
        
        //Inicia as parcelas para as vendas
        startParcelas(vndOne, true);
        startParcelas(vndTwo, false);
        startParcelas(vndThree, false);
        
        //Adiciona o cliente a venda
        vndOne.setIdCliente(cliente1);
        vndTwo.setIdCliente(cliente2);
        vndThree.setIdCliente(cliente3);
        
        //Atrasa o vencimento da primeira parcela
        vndOne.getParcelaList().get(0).setVencimento(c.getTime());
        vndTwo.getParcelaList().get(0).setVencimento(c.getTime());
        vndThree.getParcelaList().get(0).setVencimento(c.getTime());

        //Adiciona a venda ao cliente
        cliente1.addVenda(vndOne);
        cliente2.addVenda(vndTwo);
        cliente3.addVenda(vndThree);

        //Persiste a venda e, em cascata as parcelas
        vndOne = vndServiceInstance.addVenda(vndOne);
        vndTwo = vndServiceInstance.addVenda(vndTwo);
        vndThree = vndServiceInstance.addVenda(vndThree);

        //Atualiza os clientes
        cliente1 = clienteServiceinstance.setCliente(cliente1);
        cliente2 = clienteServiceinstance.setCliente(cliente2);
        cliente3 = clienteServiceinstance.setCliente(cliente3);
        
        List<Cliente> expResult = new LinkedList<>();
       // expResult.add(cliente1);
        expResult.add(cliente2);
        expResult.add(cliente3);
        
        List<Cliente> result = clienteServiceinstance.getClientesEmAtraso();
        
        System.out.println("Result Size(): " + result.size());
        
        assertTrue(result.containsAll(expResult));
        assertEquals(expResult.size(), result.size());
        
        vndServiceInstance.removeVenda(vndOne);
        vndServiceInstance.removeVenda(vndTwo);
        vndServiceInstance.removeVenda(vndThree);
        
        clienteServiceinstance.removeCliente(cliente1);
        clienteServiceinstance.removeCliente(cliente2);
        clienteServiceinstance.removeCliente(cliente3);
        

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

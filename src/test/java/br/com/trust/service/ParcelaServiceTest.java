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
public class ParcelaServiceTest {

    private EJBContainer container;
    private VendaService vendaService;
    private ParcelaService instance;
    private ClienteService clienteService;

    private Venda vndOne;
    private Venda vndTwo;
    private Venda vndThree;

    private Cliente cliente;

    private Parcela prcOne;
    private Parcela prcTwo;
    private Parcela prcThree;
    private Parcela prcFour;
    private Parcela prcFive;

    public ParcelaServiceTest() {
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
        vendaService = (VendaService) container.getContext().lookup("java:global/classes/VendaService");
        clienteService = (ClienteService) container.getContext().lookup("java:global/classes/ClienteService");
        instance = (ParcelaService) container.getContext().lookup("java:global/classes/ParcelaService");

        //Mocks Clientes
        cliente = new Cliente();
        cliente.setNome("cliente_Nome: " + new Random().nextInt());
        cliente.setBairro("cliente_Bairro: " + new Random().nextInt());
        cliente.setCep("CEP:");
        cliente.setCidade("cliente_Cidade: " + new Random().nextInt());
        cliente.setDataRecebimento("cliente_DataRecebimento: " + new Random().nextInt());
        cliente.setEmail("cliente_Email" + new Random().nextInt());
        cliente.setEndTrabalho("cliente_End_Trabalho" + new Random().nextInt());
        cliente.setEndereco("cliente_Endereco: " + new Random().nextInt());
        cliente.setEstado("cliente_Estado" + new Random().nextInt());
        cliente.setReferencia("cliente_refe:" + new Random().nextInt());
        cliente.setSituacao(Situacao.EM_DIA);
        cliente.setTelCelular("cliente_Cel:" + new Random().nextInt());
        cliente.setTelFixo("cliente_Fixo:" + new Random().nextInt());
        cliente.setUf("RJ");
        clienteService.addCliente(cliente);

        //Mocks Vendas
        vndOne = new Venda();
        vndOne.setDataVenda(new Date());
        vndOne.setEntrada(BigDecimal.ZERO);
        vndOne.setIdCliente(cliente);
        vndOne.setParcelas(5);
        vndOne.setQutPeças(new Random().nextInt(11));
        vndOne.setTotal(new BigDecimal("199.90").setScale(2, RoundingMode.UP));

        vndTwo = new Venda();
        vndTwo.setDataVenda(new Date());
        vndTwo.setEntrada(BigDecimal.ZERO);
        vndTwo.setIdCliente(cliente);
        vndTwo.setParcelas(5);
        vndTwo.setQutPeças(new Random().nextInt(11));
        vndTwo.setTotal(new BigDecimal("279.90").setScale(2, RoundingMode.UP));

        vndThree = new Venda();
        vndThree.setDataVenda(new Date());
        vndThree.setEntrada(BigDecimal.ZERO);
        vndThree.setIdCliente(cliente);
        vndThree.setParcelas(5);
        vndThree.setQutPeças(new Random().nextInt(11));
        vndThree.setTotal(new BigDecimal("59.90").setScale(2, RoundingMode.UP));

        //vendaService.addVenda(vndOne);
        //vendaService.addVenda(vndTwo);
        //vendaService.addVenda(vndThree);
    }

    @After
    public void tearDown() {
        vendaService.removeVenda(vndOne);
        vendaService.removeVenda(vndTwo);
        vendaService.removeVenda(vndThree);
        clienteService.removeCliente(cliente);
        //Finaliza o container.        
        container.close();
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
        prcOne.setValor(vndOne.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcOne.setVencimento(cal.getTime());
        prcOne.setIdVenda(vnd);
        prcOne.setNumeroDaParcela(1);
        vnd.addParcela(prcOne);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Segunda Parcela: " + cal.getTime());
        prcTwo = new Parcela();
        prcTwo.setPago(state);
        prcTwo.setRecebido(BigDecimal.ZERO);
        prcTwo.setValor(vndOne.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcTwo.setVencimento(cal.getTime());
        prcTwo.setIdVenda(vnd);
        prcTwo.setNumeroDaParcela(2);
        vnd.addParcela(prcTwo);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Terceira Parcela: " + cal.getTime());
        prcThree = new Parcela();
        prcThree.setPago(state);
        prcThree.setRecebido(BigDecimal.ZERO);
        prcThree.setValor(vndOne.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcThree.setVencimento(cal.getTime());
        prcThree.setIdVenda(vnd);
        prcThree.setNumeroDaParcela(3);
        vnd.addParcela(prcThree);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Quarta Parcela: " + cal.getTime());
        prcFour = new Parcela();
        prcFour.setPago(state);
        prcFour.setRecebido(BigDecimal.ZERO);
        prcFour.setValor(vndOne.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcFour.setVencimento(cal.getTime());
        prcFour.setIdVenda(vnd);
        prcFour.setNumeroDaParcela(4);
        vnd.addParcela(prcFour);

        cal.add(Calendar.MONTH, 1);
        System.out.println("Data Vencimento da Quinta Parcela: " + cal.getTime());
        prcFive = new Parcela();
        prcFive.setPago(state);
        prcFive.setRecebido(BigDecimal.ZERO);
        prcFive.setValor(vndOne.getTotal().divide(new BigDecimal(vnd.getParcelas()), 4, RoundingMode.UP));
        prcFive.setVencimento(cal.getTime());
        prcFive.setIdVenda(vnd);
        prcFive.setNumeroDaParcela(5);
        vnd.addParcela(prcFive);

    }

    /**
     * Test of addParcela method, of class ParcelaService.
     */
    @Test
    public void testAddParcela() throws Exception {
        System.out.println("addParcela");
        startParcelas(vndOne, Boolean.TRUE);

        vndOne = vendaService.addVenda(vndOne);

        Parcela result1 = instance.getParcela(prcOne.getId());
        Parcela result2 = instance.getParcela(prcTwo.getId());
        Parcela result3 = instance.getParcela(prcThree.getId());
        Parcela result4 = instance.getParcela(prcFour.getId());
        Parcela result5 = instance.getParcela(prcFive.getId());

        assertNotNull(result1);
        assertEquals(result2.getVencimento(), prcTwo.getVencimento());
        assertNotNull(result2);
        assertNotNull(result3);
        assertNotNull(result4);
        assertNotNull(result5);
    }

    /**
     * Test of getParcela method, of class ParcelaService.
     */
    @Test
    public void testGetParcela() throws Exception {
        System.out.println("getParcela");
        startParcelas(vndTwo, Boolean.TRUE);
        vndTwo = vendaService.addVenda(vndTwo);

        Parcela expResult = prcTwo;
        Parcela result = instance.getParcela(prcTwo.getId());

        assertNotNull(result);
        assertEquals(expResult.getVencimento(), result.getVencimento());
    }

    /**
     * Test of setParcela method, of class ParcelaService.
     */
    // @Test
    public void testSetParcela() throws Exception {
        System.out.println("setParcela");

        startParcelas(vndThree, Boolean.TRUE);
        vndThree = vendaService.addVenda(vndThree);

        prcThree.setPago(Boolean.TRUE);
        Parcela result = instance.setParcela(prcThree);
        assertNotNull(prcThree);
        assertEquals(prcThree.getPago(), result.getPago());

    }

    /**
     * Test of removeParcela method, of class ParcelaService.
     */
    @Test
    public void testRemoveParcela() throws Exception {
        System.out.println("removeParcela");
        startParcelas(vndOne, Boolean.TRUE);
        vndOne = vendaService.addVenda(vndOne);

        instance.removeParcela(prcOne);

        Parcela result = instance.getParcela(prcOne.getId());

        assertNull(result);
    }

    /**
     * Test of getParcelaOfVendaByNumeroParcela method, of class ParcelaService.
     */
    @Test
    public void testGetParcelaOfVendaByNumeroParcela() throws Exception {
        System.out.println("getParcelaOfVendaByNumeroParcela");
        startParcelas(vndOne, Boolean.TRUE);
        vndOne = vendaService.addVenda(vndOne);

        Parcela result = instance.getParcelaOfVendaByNumeroParcela(vndOne.getIdVenda(), 3);

        assertNotNull(result);
        assertEquals(prcThree.getVencimento(), result.getVencimento());
    }

    /**
     * Test of getParcelasOfVenda method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasOfVenda() throws Exception {
        System.out.println("getParcelasOfVenda");
        startParcelas(vndTwo, Boolean.TRUE);
        vndTwo = vendaService.addVenda(vndTwo);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        expResult.add(prcTwo);
        expResult.add(prcThree);
        expResult.add(prcFour);
        expResult.add(prcFive);

        List<Parcela> result = instance.getParcelasOfVenda(vndTwo.getIdVenda());

        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getParcelasEmAbertoOfVenda method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasEmAbertoOfVenda() throws Exception {
        System.out.println("getParcelasEmAbertoOfVenda");
        startParcelas(vndThree, Boolean.FALSE);
        vndThree = vendaService.addVenda(vndThree);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        expResult.add(prcTwo);
        expResult.add(prcThree);
        expResult.add(prcFour);
        expResult.add(prcFive);

        List<Parcela> result = instance.getParcelasEmAbertoOfVenda(vndThree.getIdVenda());

        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getParcelasPagasOfVendas method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasPagasOfVendas() throws Exception {
        System.out.println("getParcelasPagasOfVendas");
        startParcelas(vndThree, Boolean.TRUE);
        vndThree = vendaService.addVenda(vndThree);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        expResult.add(prcTwo);
        expResult.add(prcThree);
        expResult.add(prcFour);
        expResult.add(prcFive);

        List<Parcela> result = instance.getParcelasPagasOfVendas(vndThree.getIdVenda());

        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getParcelaOfCliente method, of class ParcelaService.
     */
    @Test
    public void testGetParcelaOfCliente() throws Exception {
        System.out.println("getParcelaOfCliente");
        startParcelas(vndOne, Boolean.TRUE);
        vndOne = vendaService.addVenda(vndOne);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        expResult.add(prcTwo);
        expResult.add(prcThree);
        expResult.add(prcFour);
        expResult.add(prcFive);

        List<Parcela> result = instance.getParcelaOfCliente(cliente.getIdCliente());

        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getParcelasOfClientePago method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasOfClientePago() throws Exception {
        System.out.println("getParcelasOfClientePago");
        startParcelas(vndOne, Boolean.TRUE);
        vndOne = vendaService.addVenda(vndOne);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        expResult.add(prcTwo);
        expResult.add(prcThree);
        expResult.add(prcFour);
        expResult.add(prcFive);

        List<Parcela> result = instance.getParcelasOfClientePago(cliente.getIdCliente());

        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getParcelasOfClienteEmAberto method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasOfClienteEmAberto() throws Exception {
        System.out.println("getParcelasOfClienteEmAberto");
        startParcelas(vndOne, Boolean.FALSE);
        vndOne = vendaService.addVenda(vndOne);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        expResult.add(prcTwo);
        expResult.add(prcThree);
        expResult.add(prcFour);
        expResult.add(prcFive);

        List<Parcela> result = instance.getParcelasOfClienteEmAberto(cliente.getIdCliente());

        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getParcelasOfMonth method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasOfMonth() throws Exception {
        System.out.println("getParcelasOfMonth");
        startParcelas(vndOne, Boolean.FALSE);
        vndOne = vendaService.addVenda(vndOne);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        List<Parcela> result = instance.getParcelasOfMonth(9, 2015);
        assertNotNull(result);
        assertEquals(expResult.size(), result.size());

    }

    /**
     * Test of getParcelasOfMonthEmAberto method, of class ParcelaService.
     */
    // @Test
    public void testGetParcelasOfMonthEmAberto() throws Exception {
        System.out.println("getParcelasOfMonthEmAberto");
        startParcelas(vndOne, Boolean.FALSE);
        vndOne = vendaService.addVenda(vndOne);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        List<Parcela> result = instance.getParcelasOfMonthEmAberto(9, 2015);
        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getParcelasOfMonthPagas method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasOfMonthPagas() throws Exception {
        System.out.println("getParcelasOfMonthPagas");
        startParcelas(vndOne, Boolean.TRUE);
        vndOne = vendaService.addVenda(vndOne);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);
        List<Parcela> result = instance.getParcelasOfMonthPagas(9, 2015);
        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of setPagamentoParcela method, of class ParcelaService.
     */
    @Test
    public void testSetPagamentoParcela() throws Exception {
        System.out.println("setPagamentoParcela");
        startParcelas(vndOne, Boolean.FALSE);
        vndOne = vendaService.addVenda(vndOne);

        Parcela result = instance.setPagamentoParcela(prcOne.getId());

        assertNotNull(result);
        assertTrue(result.getPago());

        result = instance.setPagamentoParcela(prcOne.getId());

        assertFalse(result.getPago());
    }

    /**
     * Test of getSaldo method, of class ParcelaService.
     */
    @Test
    public void testGetSaldo() throws Exception {
        System.out.println("getSaldo");
        startParcelas(vndOne, Boolean.FALSE);
        vndOne = vendaService.addVenda(vndOne);

        prcOne.setRecebido(new BigDecimal("20.00"));

        BigDecimal expResult = prcOne.getValor().subtract(prcOne.getRecebido());

        BigDecimal result = instance.getSaldo(prcOne);
        System.out.println("expResult:" + expResult);
        System.out.println("result:" + result);

        assertNotNull(result);
        assertTrue((expResult.compareTo(result) == 0));

    }

    /**
     * Test of getParcelasAtrasadas method, of class ParcelaService.
     */
    @Test
    public void testGetParcelasAtrasadas() throws Exception {
        System.out.println("getParcelasAtrasadas");
        startParcelas(vndOne, Boolean.FALSE);
        vndOne = vendaService.addVenda(vndOne);
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.DAY_OF_MONTH, 1);
        calendario.add(Calendar.MONTH, -1);
        prcOne.setVencimento(calendario.getTime());
        prcOne = instance.setParcela(prcOne);
        List<Parcela> expResult = new LinkedList<>();
        expResult.add(prcOne);

        List<Parcela> result = instance.getParcelasAtrasadas();
        assertNotNull(result);
        assertEquals(expResult.size(), result.size());
    }

}

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
public class VendaServiceTest {

    private EJBContainer container;
    private VendaService instance;
    private ClienteService clienteService;
    private Venda vndOne;
    private Venda vndTwo;
    private Venda vndThree;
    private Cliente cliente1;

    public VendaServiceTest() {
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
        instance = (VendaService) container.getContext().lookup("java:global/classes/VendaService");
        clienteService = (ClienteService) container.getContext().lookup("java:global/classes/ClienteService");

        //Mocks Clientes
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
        clienteService.addCliente(cliente1);

        //Mocks Vendas
        vndOne = new Venda();
        vndOne.setDataVenda(new Date());
        vndOne.setEntrada(new BigDecimal("0.00"));
        vndOne.setIdCliente(cliente1);
        int parcelas = new Random().nextInt(11);
        vndOne.setParcelas((parcelas == 0 ? 1 : parcelas));
        vndOne.setQutPeças(new Random().nextInt(11));
        vndOne.setTotal(new BigDecimal("199.90").setScale(2, RoundingMode.UP));

        vndTwo = new Venda();
        vndTwo.setDataVenda(new Date());
        vndTwo.setEntrada(new BigDecimal("0.00"));
        vndTwo.setIdCliente(cliente1);
        parcelas = new Random().nextInt(11);
        vndTwo.setParcelas((parcelas == 0 ? 1 : parcelas));
        vndTwo.setQutPeças(new Random().nextInt(11));
        vndTwo.setTotal(new BigDecimal("279.90").setScale(2, RoundingMode.UP));

        vndThree = new Venda();
        vndThree.setDataVenda(new Date());
        vndThree.setEntrada(new BigDecimal("0.00"));
        vndThree.setIdCliente(cliente1);
        parcelas = new Random().nextInt(11);
        vndThree.setParcelas((parcelas == 0 ? 1 : parcelas));
        vndThree.setQutPeças(new Random().nextInt(11));
        vndThree.setTotal(new BigDecimal("59.90").setScale(2, RoundingMode.UP));
    }

    @After
    public void tearDown() {
        clienteService.removeCliente(cliente1);
        //Finaliza o container.
        container.close();
        vndOne = null;
        vndTwo = null;
        vndThree = null;
    }

    /**
     * Test of addVenda method, of class VendaService.
     */
    //@Test
    public void testAddVenda() throws Exception {
        System.out.println("addVenda");
        Venda result = instance.addVenda(vndOne);
        assertEquals(vndOne, result);
        instance.removeVenda(vndOne);
    }

    /**
     * Test of getVenda method, of class VendaService.
     */
    //@Test
    public void testGetVenda() throws Exception {
        System.out.println("getVenda");
        vndOne = instance.addVenda(vndOne);
        Venda result = instance.getVenda(vndOne.getIdVenda());
        assertEquals(result, vndOne);
        instance.removeVenda(vndOne);
    }

    /**
     * Test of setVenda method, of class VendaService.
     */
    //@Test
    public void testSetVenda() throws Exception {
        System.out.println("setVenda");
        vndTwo = instance.addVenda(vndTwo);
        vndTwo.setQutPeças(new Random().nextInt(11));
        Venda expResult = vndTwo;
        Venda result = instance.setVenda(vndTwo);
        assertEquals(expResult.getQutPeças(), result.getQutPeças());
        instance.removeVenda(vndTwo);
    }

    /**
     * Test of removeVenda method, of class VendaService.
     */
    //  @Test
    public void testRemoveVenda() throws Exception {
        System.out.println("removeVenda");
        vndThree = instance.addVenda(vndThree);
        int id = vndThree.getIdVenda();
        instance.removeVenda(vndThree);
        Venda result = instance.getVenda(id);
        assertNull(result);
    }

    /**
     * Test of getVendasOfCliente method, of class VendaService.
     */
    // @Test
    public void testGetVendasOfCliente() throws Exception {
        vndOne = instance.addVenda(vndOne);
        vndTwo = instance.addVenda(vndTwo);
        vndThree = instance.addVenda(vndThree);
        int idOfCliente = cliente1.getIdCliente();
        List<Venda> expResult = new LinkedList<>();
        expResult.add(vndOne);
        expResult.add(vndTwo);
        expResult.add(vndThree);
        List<Venda> result = instance.getVendasOfCliente(idOfCliente);

        assertTrue(result.containsAll(expResult));
        assertEquals(result.size(), expResult.size());

        instance.removeVenda(vndOne);
        instance.removeVenda(vndTwo);
        instance.removeVenda(vndThree);
    }

    /**
     * Test of getVendasOfMonth method, of class VendaService.
     */
    // @Test
    public void testGetVendasOfMonth() throws Exception {
        vndOne = instance.addVenda(vndOne);
        vndTwo = instance.addVenda(vndTwo);
        vndThree = instance.addVenda(vndThree);

        int month = 07;
        int year = 2015;

        List<Venda> expResult = new LinkedList<>();
        expResult.add(vndOne);
        expResult.add(vndTwo);
        expResult.add(vndThree);
        List<Venda> result = instance.getVendasOfMonth(month, year);

        assertTrue(result.containsAll(expResult));
        assertEquals(result.size(), expResult.size());

        instance.removeVenda(vndOne);
        instance.removeVenda(vndTwo);
        instance.removeVenda(vndThree);
    }

    /**
     * Test of getVendasOfPeriod method, of class VendaService.
     */
    //@Test
    public void testGetVendasOfPeriod() throws Exception {
        vndOne = instance.addVenda(vndOne);
        vndTwo = instance.addVenda(vndTwo);
        vndThree = instance.addVenda(vndThree);

        List<Venda> expResult = new LinkedList<>();
        expResult.add(vndOne);
        expResult.add(vndTwo);
        expResult.add(vndThree);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        Date dataInicial = calendar.getTime();
        //calendar.add(Calendar.MONTH, 1);
        Date dataFinal = calendar.getTime();

        List<Venda> result = instance.getVendasOfPeriod(dataInicial, dataFinal);

        if (result != null) {
            assertTrue(result.containsAll(expResult));
            assertEquals(result.size(), expResult.size());
        } else {
            System.out.println("Data Inicial deve ser diferente da final");
        }

        instance.removeVenda(vndOne);
        instance.removeVenda(vndTwo);
        instance.removeVenda(vndThree);

    }

    /**
     * Test of getVendasEmAberto method, of class VendaService.
     */
    //@Test
    public void testGetVendasEmAberto() throws Exception {
        vndOne = instance.addVenda(vndOne);
        vndTwo = instance.addVenda(vndTwo);
        vndThree = instance.addVenda(vndThree);

        List<Venda> expResult = new LinkedList<>();
        expResult.add(vndOne);
        expResult.add(vndTwo);
        expResult.add(vndThree);
        List<Venda> result = instance.getVendasEmAberto();

        assertTrue(result.containsAll(expResult));
        assertEquals(result.size(), expResult.size());

        instance.removeVenda(vndOne);
        instance.removeVenda(vndTwo);
        instance.removeVenda(vndThree);
    }

    /**
     * Test of setVendaQuitada method, of class VendaService.
     */
    //@Test
    public void testSetVendaQuitada() throws Exception {
        vndOne = instance.addVenda(vndOne);

        Venda result = instance.setVendaQuitada(vndOne.getIdVenda());

        assertNotEquals(result.getQuitada(), vndOne.getQuitada());

        instance.removeVenda(vndOne);
    }

    /**
     * Test of getSaldo method, of class VendaService.
     */
    @Test
    public void testGetSaldo() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONDAY, 6);
        cal.set(Calendar.DAY_OF_MONTH, 23);

        for (int y = 0; y < vndOne.getParcelas(); y++) {
            Parcela parcela = new Parcela();
            parcela.setIdVenda(vndOne);
            parcela.setNumeroDaParcela(y + 1);

            BigDecimal qutParcelas = new BigDecimal(vndOne.getParcelas());
            
            BigDecimal valorParcela = vndOne.getTotal().divide(qutParcelas,4,RoundingMode.UP);

            parcela.setValor(valorParcela);
            parcela.setVencimento(cal.getTime());
            cal.add(Calendar.MONTH, 1);
            vndOne.addParcela(parcela);
        }
        vndOne = instance.addVenda(vndOne);

        BigDecimal result = instance.getSaldo(vndOne);

        //objetc1.compareTO(objetc1)
        // -1 objetc1 é menor / 0 objetc1 é igual / - objetc1 é maior.
        assertTrue(result.compareTo(vndOne.getTotal()) <= 0);
        instance.removeVenda(vndOne);

    }
}

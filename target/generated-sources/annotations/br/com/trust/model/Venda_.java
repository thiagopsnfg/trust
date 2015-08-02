package br.com.trust.model;

import br.com.trust.model.Cliente;
import br.com.trust.model.Parcela;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-02T10:49:14")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Integer> parcelas;
    public static volatile SingularAttribute<Venda, BigDecimal> total;
    public static volatile SingularAttribute<Venda, Date> dataVenda;
    public static volatile SingularAttribute<Venda, Cliente> idCliente;
    public static volatile SingularAttribute<Venda, BigDecimal> entrada;
    public static volatile SingularAttribute<Venda, Integer> id;
    public static volatile SingularAttribute<Venda, Boolean> quitada;
    public static volatile SingularAttribute<Venda, Integer> qutPeças;
    public static volatile ListAttribute<Venda, Parcela> parcelaList;
    public static volatile SingularAttribute<Venda, BigDecimal> recebido;

}
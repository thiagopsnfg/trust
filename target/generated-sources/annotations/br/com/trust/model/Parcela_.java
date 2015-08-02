package br.com.trust.model;

import br.com.trust.model.Venda;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-02T10:49:14")
@StaticMetamodel(Parcela.class)
public class Parcela_ { 

    public static volatile SingularAttribute<Parcela, BigDecimal> valor;
    public static volatile SingularAttribute<Parcela, Integer> id;
    public static volatile SingularAttribute<Parcela, Date> vencimento;
    public static volatile SingularAttribute<Parcela, Boolean> pago;
    public static volatile SingularAttribute<Parcela, Integer> numeroDaParcela;
    public static volatile SingularAttribute<Parcela, BigDecimal> recebido;
    public static volatile SingularAttribute<Parcela, Venda> idVenda;

}
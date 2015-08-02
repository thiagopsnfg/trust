package br.com.trust.model;

import br.com.trust.model.Situacao;
import br.com.trust.model.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-07-23T16:50:48")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> estado;
    public static volatile SingularAttribute<Cliente, String> cidade;
    public static volatile SingularAttribute<Cliente, Situacao> situacao;
    public static volatile SingularAttribute<Cliente, String> endereco;
    public static volatile SingularAttribute<Cliente, String> bairro;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile SingularAttribute<Cliente, String> telFixo;
    public static volatile SingularAttribute<Cliente, String> endTrabalho;
    public static volatile SingularAttribute<Cliente, String> cep;
    public static volatile SingularAttribute<Cliente, String> uf;
    public static volatile ListAttribute<Cliente, Venda> vendaList;
    public static volatile SingularAttribute<Cliente, String> telContato;
    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile SingularAttribute<Cliente, String> telCelular;
    public static volatile SingularAttribute<Cliente, String> email;
    public static volatile SingularAttribute<Cliente, String> referencia;
    public static volatile SingularAttribute<Cliente, String> dataRecebimento;

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Thiago
 */

@Entity
@Table(name = "cliente", catalog = "trust", schema = "")
@XmlRootElement
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCliente", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "O Nome não pode ser vazio!")
    @Size(min = 3, max = 255, message = "O nome esta muito pequeno.")
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    @Size(max = 45)
    @Column(name = "telCelular", length = 45)
    private String telCelular;
    @Size(max = 45)
    @Column(name = "telFixo", length = 45)
    private String telFixo;
    @Size(max = 45)
    @Column(name = "telContato", length = 45)
    private String telContato;
    //@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;
    @Size(max = 255)
    @Column(name = "referencia", length = 255)
    private String referencia;
    @Size(max = 2)
    @Column(name = "UF", length = 2)
    private String uf;
    @Size(max = 45)
    @Column(name = "estado", length = 45)
    private String estado;
    @Size(max = 45)
    @Column(name = "cidade", length = 45)
    private String cidade;
    @Size(max = 45)
    @Column(name = "bairro", length = 45)
    private String bairro;
    @Size(max = 45)
    @Column(name = "endereco", length = 45)
    private String endereco;
    @Size(max = 9)
    @Column(name = "cep", length = 9)
    private String cep;
    @Size(max = 255)
    @Column(name = "endTrabalho", length = 255)
    private String endTrabalho;
    @Size(max = 255)
    @Column(name = "dataRecebimento", length = 255)
    private String dataRecebimento;
    @Size(max = 255)
    @Column(name = "rua", length = 255)
    private String rua;

    @Enumerated(EnumType.STRING)
    private Situacao situacao = Situacao.EM_DIA;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente",fetch = FetchType.EAGER)
    private List<Venda> vendaList = new LinkedList<>();

    public void addVenda(Venda vnd) {
        vnd.setIdCliente(this);
        this.vendaList.add(vnd);
    }

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String nome, Situacao situacao) {
        this.id = id;
        this.nome = nome;
        this.situacao = situacao;
    }

    public Integer getIdCliente() {
        return id;
    }

    public void setIdCliente(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getTelFixo() {
        return telFixo;
    }

    public void setTelFixo(String telFixo) {
        this.telFixo = telFixo;
    }

    public String getTelContato() {
        return telContato;
    }

    public void setTelContato(String telContato) {
        this.telContato = telContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndTrabalho() {
        return endTrabalho;
    }

    public void setEndTrabalho(String endTrabalho) {
        this.endTrabalho = endTrabalho;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.trust.model.Cliente[ id=" + id + " ]";
    }

}

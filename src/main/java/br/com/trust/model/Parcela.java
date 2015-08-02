/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thiago
 */
@Entity
@Table(name = "parcela", catalog = "trust", schema = "")
@XmlRootElement
public class Parcela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idParcela", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroDaParcela", nullable = false)
    private Integer numeroDaParcela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor", nullable = false, precision = 9,scale = 2)
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date vencimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recebido", nullable = false, precision = 9,scale = 2)
    private BigDecimal recebido =  new BigDecimal("0.00");
    @Basic(optional = false)
    @NotNull
    @Column(name = "pago", nullable = false)
    private Boolean pago = Boolean.FALSE;
    @JoinColumn(name = "idVenda", referencedColumnName = "idVenda", nullable = false)
    @ManyToOne(optional = false)
    private Venda idVenda;

    public Parcela() {
    }

    public Parcela(Integer idParcela) {
        this.id = idParcela;
    }

    public Parcela(Integer idParcela, BigDecimal valor, Date vencimento, BigDecimal recebido, boolean pago) {
        this.id = idParcela;
        this.valor = valor;
        this.vencimento = vencimento;
        this.recebido = recebido;
        this.pago = pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idParcela) {
        this.id = idParcela;
    }

    public Integer getNumeroDaParcela() {
        return numeroDaParcela;
    }

    public void setNumeroDaParcela(Integer numeroDaParcela) {
        this.numeroDaParcela = numeroDaParcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public BigDecimal getRecebido() {
        return recebido;
    }

    public void setRecebido(BigDecimal recebido) {
        this.recebido = recebido;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Venda getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Venda idVenda) {
        this.idVenda = idVenda;
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
        if (!(object instanceof Parcela)) {
            return false;
        }
        Parcela other = (Parcela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.trust.model.Parcela[ idParcela=" + id + " ]";
    }

}

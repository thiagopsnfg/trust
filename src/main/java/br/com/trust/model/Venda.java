/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Thiago
 */
@Entity
@Table(name = "venda", catalog = "trust", schema = "")
@XmlRootElement
public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVenda", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quitada", nullable = false)
    private Boolean quitada = Boolean.FALSE;
    @Basic(optional = false)
    @NotNull(message = "O total da venda deve ser informado!")
    @Column(name = "total", nullable = false,precision = 9,scale = 2)
    private BigDecimal total = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "entrada", nullable = false,precision = 9,scale = 2)
    private BigDecimal entrada = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull(message = "A quantidade de parcelas deve ser definida!")
    @Column(name = "parcelas", nullable = false)
    private Integer parcelas = 1;
    @Column(name = "qutPecas")
    private Integer qutPeças = 0;
    @Basic(optional = false)
    @NotNull(message = "A data da venda deve ser definida!!")
    @Column(name = "dataVenda", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "recebido", nullable = false, precision = 9,scale = 2)
    private BigDecimal recebido = new BigDecimal("0.00");
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", nullable = false)
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenda")
    private List<Parcela> parcelaList = new LinkedList<>();

    public void addParcela(Parcela parcela){
        parcela.setIdVenda(this);
        this.parcelaList.add(parcela);
    }
    public Venda() {
    }

    public Venda(Integer id) {
        this.id = id;
    }

    public Venda(Integer id, boolean quitada, BigDecimal total, BigDecimal entrada, int parcelas, Date dataVenda, BigDecimal recebido) {
        this.id = id;
        this.quitada = quitada;
        this.total = total;
        this.entrada = entrada;
        this.parcelas = parcelas;
        this.dataVenda = dataVenda;
        this.recebido = recebido;
    }

    public Integer getIdVenda() {
        return id;
    }

    public void setIdVenda(Integer id) {
        this.id = id;
    }

    public boolean getQuitada() {
        return quitada;
    }

    public void setQuitada(boolean quitada) {
        this.quitada = quitada;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getEntrada() {
        return entrada;
    }

    public void setEntrada(BigDecimal entrada) {
        this.entrada = entrada;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public Integer getQutPeças() {
        return qutPeças;
    }

    public void setQutPeças(Integer qutPeças) {
        this.qutPeças = qutPeças;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getRecebido() {
        return recebido;
    }

    public void setRecebido(BigDecimal recebido) {
        this.recebido = recebido;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public List<Parcela> getParcelaList() {
        return parcelaList;
    }

    public void setParcelaList(List<Parcela> parcelaList) {
        this.parcelaList = parcelaList;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.trust.model.Venda[ id=" + id + " ]";
    }
    
}

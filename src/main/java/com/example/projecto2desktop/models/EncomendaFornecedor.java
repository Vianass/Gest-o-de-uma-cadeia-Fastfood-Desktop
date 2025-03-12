package com.example.projecto2desktop.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "encomenda_fornecedor")
public class EncomendaFornecedor {
    @Id
    @Column(name = "id_encomenda_fornecedor", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_encomenda", nullable = false)
    private Encomenda idEncomenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor idFornecedor;

    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Encomenda getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(Encomenda idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    public Fornecedor getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Fornecedor idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
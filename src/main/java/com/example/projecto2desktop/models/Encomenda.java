package com.example.projecto2desktop.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "encomenda")
public class Encomenda {
    @Id
    @Column(name = "id_encomenda", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor idFornecedor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_armazem", nullable = false)
    private Armazem idArmazem;

    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    @Column(name = "estado", nullable = false, length = Integer.MAX_VALUE)
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fornecedor getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Fornecedor idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Armazem getIdArmazem() {
        return idArmazem;
    }

    public void setIdArmazem(Armazem idArmazem) {
        this.idArmazem = idArmazem;
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